package states.game;

import Main.FlappyGameState;
import game.DifficultySettings;
import game.gameEvents.GameEventListener;
import game.gameEvents.GameEventType;
import game.itemGeneration.obstacle.ObstacleGeneratorType;
import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import game.multiplayer.powerUps.PowerUpType;
import game.player.MultiModePlayer;
import game.player.PlayerInfo;
import game.player.Result;
import graphics.Canvas;
import graphics.HUD.TimerHud;
import graphics.Screen;
import network.CommandReceiver;
import network.CommandTransmitter;
import network.ConnectionHandle;
import network.ConnectionListener;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import sounds.SoundPlayer;
import states.menu.MultiplayerEndMenu;
import utilities.FontUtility;

import static game.GameConstants.BIRD_WIDTH;


public class MultiplayerState extends states.FlappyGameState implements ConnectionListener, GameEventListener {
    private OnlineLocalGame leftGame;
    private OnlineRemoteGame rightGame;
    private Canvas gameCanvas;
    private DifficultySettings settings = new DifficultySettings(1, ObstacleGeneratorType.MEDIUM);
    private SoundPlayer soundPlayer;
    private Image leftScreenCopy;
    private ConnectionHandle connectionHandle;
    private StateBasedGame stateBasedGame;
    private boolean gameFinished=false;
    private TimerHud timer;
    private Image powerups;
    private UnicodeFont font;
    public void setConnectionHandle(ConnectionHandle connectionHandle) {
        this.connectionHandle = connectionHandle;
        connectionHandle.addConnectionListener(this);
    }

    @Override
    public int getID() {
        return FlappyGameState.MULTIPLAYER;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame= stateBasedGame;
        Screen screen = new Screen((int) (gameContainer.getWidth()*0.45), (int) (gameContainer.getHeight()*0.95), 0, (int) (gameContainer.getHeight()*0.025));
        leftScreenCopy = new Image(screen.getWidth(), screen.getHeight());
        gameCanvas = new Canvas(screen, gameContainer.getGraphics());
        soundPlayer= new SoundPlayer();
        powerups = new Image(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.POWERUPS)).getScaledCopy(gameContainer.getWidth(), gameContainer.getHeight());
        timer= new TimerHud(new Canvas(new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0), gameContainer.getGraphics()));
        font =  FontUtility.makeFont((int) (gameCanvas.getScreen().getWidth()*0.04));
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        gameFinished=false;
        PlayerInfo myPlayer= ((FlappyGameState) game).getPlayerInfo();
        leftGame= new OnlineLocalGame(gameCanvas, settings, new CommandTransmitter(connectionHandle), new MultiModePlayer(myPlayer));
        leftGame.addListener(soundPlayer);
        leftGame.addListener(this);
        rightGame= new OnlineRemoteGame(gameCanvas, settings, new MultiModePlayer(new PlayerInfo(connectionHandle.getOthersInfo())));
        rightGame.addListener(this);
        connectionHandle.setReceiver( new CommandReceiver(leftGame, rightGame));
        connectionHandle.startListening();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        rightGame.render();
        gameCanvas.drawStringCentered(rightGame.getPlayer().getPlayerInfo().getName(),font, (float)(rightGame.getBird().getX()+BIRD_WIDTH/2), (float) rightGame.getBird().getY()-0.02f);
        graphics.copyArea(leftScreenCopy, 0, (int) (gameContainer.getHeight()*0.025));
        leftGame.render();
        gameCanvas.drawStringCentered(leftGame.getPlayer().getPlayerInfo().getName(),font, (float)(leftGame.getBird().getX()+BIRD_WIDTH/2), (float) leftGame.getBird().getY()-0.02f);
        leftScreenCopy.draw(gameContainer.getWidth()*0.55f, gameCanvas.getScreen().getOffsetY());
        powerups.draw(0, 0);
        timer.render((int) leftGame.getTimeLeft()/1000);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        leftGame.update(i);
        rightGame.update(i);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key== Input.KEY_SPACE)
            leftGame.playerJump();
        if (key== Input.KEY_E)
            leftGame.powerUpUsed(PowerUpType.ROCKET);
        if (key== Input.KEY_W)
            leftGame.powerUpUsed(PowerUpType.GRAVITY);
        if (key==Input.KEY_Q)
            leftGame.powerUpUsed(PowerUpType.SPEED);

    }

    @Override
    public void connectionStatus(boolean connected) {
        if (!connected){
            if (isAcceptingInput()&&!gameFinished) {
                stateBasedGame.enterState(FlappyGameState.CONNECTION_ERROR_MENU);
            }
        }
    }

    @Override
    public void gameEvent(GameEventType event) {
        if (event==GameEventType.GAMEOVER){
            if (leftGame.isOver() && rightGame.isOver()){
                gameFinished=true;
                connectionHandle.closeConnection();
                ((MultiplayerEndMenu)stateBasedGame.getState(FlappyGameState.MULTI_END_MENU))
                        .setResults(new Result(leftGame.getPlayer()), new Result(rightGame.getPlayer()));
                stateBasedGame.enterState(FlappyGameState.MULTI_END_MENU);
            }
        }
    }
}
