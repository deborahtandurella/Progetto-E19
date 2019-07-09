package states.test;

import Main.GiocoAStati;
import game.DifficultySettings;
import game.LocalGame;
import game.ObstacleGeneratorType;
import game.SoundPlayer;
import graphics.Canvas;
import graphics.Screen;
import logic.SinglePlayer.SingleModePlayer;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LocalMultiplayerState extends BasicGameState {
    private LocalGame leftGame;
    private LocalGame rightGame;
    private Canvas gameCanvas;
    private DifficultySettings settings = new DifficultySettings(1, ObstacleGeneratorType.MEDIUM);
    private SoundPlayer soundPlayer;
    private Image leftScreenCopy;
    private Image yPanel;
    private Image xPanel;
    private double angle = 0;
    private boolean entered=false;


    @Override
    public int getID() {
        return 12;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Screen screen = new Screen((int) (gameContainer.getWidth()*0.45), (int) (gameContainer.getHeight()*0.95), 0, 0);
        leftScreenCopy = new Image(screen.getWidth(), screen.getHeight());
        gameCanvas = new Canvas(screen, gameContainer.getGraphics());
        soundPlayer= new SoundPlayer();
        yPanel = new Image("res/sprites/backgrounds/nero.jpg").getScaledCopy((int)(gameContainer.getWidth()*0.1), gameContainer.getHeight());
        xPanel = new Image("res/sprites/backgrounds/nero.jpg").getScaledCopy( gameContainer.getWidth(),(int)(gameContainer.getHeight()*0.08)); //sovradimensionata per compensare imprecisioni date dalle troncature
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        leftGame= new LocalGame(gameCanvas, settings, new SingleModePlayer(((GiocoAStati)game).getPlayerInfo()));
        leftGame.addListener(soundPlayer);
        rightGame= new LocalGame(gameCanvas, settings, new SingleModePlayer(((GiocoAStati)game).getPlayerInfo()));
        rightGame.addListener(soundPlayer);
        System.out.println(container.getWidth() + "   " + container.getHeight());
        entered=true;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if (entered) {
            rightGame.render();


            angle += 0.1;

            graphics.copyArea(leftScreenCopy, 0, 0);

            leftScreenCopy.getFlippedCopy(false, true).draw(gameContainer.getWidth() * 0.55f, 0);
            leftGame.render();

            yPanel.draw(gameContainer.getWidth() * 0.45f, 0);
            xPanel.draw(0, gameCanvas.getScreen().getHeight());
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (entered){
            leftGame.update(i);
            rightGame.update(i);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (entered){
            if (key == Input.KEY_SPACE) {
                rightGame.playerJump();
            }
            if (key == Input.KEY_LCONTROL) {
                leftGame.playerJump();
            }
        }
    }
}
