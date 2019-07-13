package states.test;

import Main.GiocoAStati;
import game.*;
import game.itemGeneration.obstacle.ObstacleGeneratorType;
import game.powerUps.PowerUpType;
import graphics.Canvas;
import graphics.Screen;
import logic.SinglePlayer.Result;
import logic.player.MultiModePlayer;
import logic.player.PlayerInfo;
import network.ConnectionListener;
import network.test.CommandHandler;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.MultiplayerReplayMenu;


public class MultiplayerState extends BasicGameState implements ConnectionListener {
    private OnlineLocalGame leftGame;
    private RemoteGame rightGame;
    private Canvas gameCanvas;
    private DifficultySettings settings = new DifficultySettings(1, ObstacleGeneratorType.MEDIUM);
    private SoundPlayer soundPlayer;
    private Image leftScreenCopy;
    private Image yPanel;
    private Image xPanel;
    private CommandHandler commandHandler;
    private StateBasedGame stateBasedGame;
    private boolean gameFinished=false;

    public void setCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        commandHandler.addConnectionListener(this);
    }

    @Override
    public int getID() {
        return 13;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Screen screen = new Screen((int) (gameContainer.getWidth()*0.45), (int) (gameContainer.getHeight()*0.95), 0, 0);
        leftScreenCopy = new Image(screen.getWidth(), screen.getHeight());
        gameCanvas = new Canvas(screen, gameContainer.getGraphics());
        soundPlayer= new SoundPlayer();
        yPanel = new Image("res/sprites/backgrounds/nero.jpg").getScaledCopy((int)(gameContainer.getWidth()*0.1), gameContainer.getHeight());
        xPanel = new Image("res/sprites/backgrounds/nero.jpg").getScaledCopy( gameContainer.getWidth(),(int)(gameContainer.getHeight()*0.08)); //sovradimensionata per compensare imprecisioni date dalle troncature
        this.stateBasedGame= stateBasedGame;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        gameFinished=false;
        PlayerInfo myPlayer= ((GiocoAStati) game).getPlayerInfo();
        leftGame= new OnlineLocalGame(gameCanvas, settings, commandHandler, new MultiModePlayer(myPlayer));
        leftGame.addListener(soundPlayer);
        rightGame= new RemoteGame(gameCanvas, settings, new MultiModePlayer(commandHandler.getOthersInfo()));
        commandHandler.startListening(rightGame, leftGame);
        rightGame.addListener(soundPlayer);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        rightGame.render();

        graphics.copyArea(leftScreenCopy, 0, 0);

        leftGame.render();
        leftScreenCopy.draw(gameContainer.getWidth()*0.55f, 0);

        yPanel.draw(gameContainer.getWidth()*0.45f, 0);
        xPanel.draw(0,gameCanvas.getScreen().getHeight());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        leftGame.update(i);
        rightGame.update(i);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key== Input.KEY_SPACE){
            leftGame.playerJump();
        }
        if (key== Input.KEY_Z)
            leftGame.powerUpUsed(PowerUpType.ROCKET);
        if (key== Input.KEY_Q)
            leftGame.powerUpUsed(PowerUpType.GRAVITY);
        if (key==Input.KEY_W)
            leftGame.powerUpUsed(PowerUpType.SPEED);

        if (key== Input.KEY_M){
            gameFinished=true;
            commandHandler.closeConnection();
            ((MultiplayerReplayMenu)stateBasedGame.getState(8))
                    .setResults(new Result(leftGame.getPlayer()), new Result(rightGame.getPlayer()));
            stateBasedGame.enterState(8);
        }
    }

    @Override
    public void connectionWorking(boolean connected) {
        if (!connected){
            if (isAcceptingInput()&&!gameFinished) {
                stateBasedGame.enterState(GiocoAStati.CONNECTION_ERROR_MENU);
            }
        }
    }
}
