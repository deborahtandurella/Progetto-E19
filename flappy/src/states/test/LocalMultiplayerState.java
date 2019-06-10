package states.test;

import game.DifficultySettings;
import game.LocalGame;
import game.ObstacleGeneratorType;
import game.SoundPlayer;
import graphics.Canvas;
import graphics.Screen;
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

    @Override
    public int getID() {
        return 12;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Screen screen = new Screen(gameContainer.getWidth()/100*45, gameContainer.getHeight()/100*95, 0, 0);
        leftScreenCopy = new Image(screen.getWidth(), screen.getHeight());
        gameCanvas = new Canvas(screen, gameContainer.getGraphics());
        soundPlayer= new SoundPlayer();
        yPanel = new Image("res/sprites/backgrounds/nero.jpg").getScaledCopy(gameContainer.getWidth()/100*10, gameContainer.getHeight());
        xPanel = new Image("res/sprites/backgrounds/nero.jpg").getScaledCopy( gameContainer.getWidth(),gameContainer.getHeight()/100*5);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        leftGame= new LocalGame(gameCanvas, settings);
        leftGame.addListener(soundPlayer);
        rightGame= new LocalGame(gameCanvas, settings);
        rightGame.addListener(soundPlayer);
        System.out.println(container.getWidth() + "   " + container.getHeight());
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        rightGame.render();
        graphics.copyArea(leftScreenCopy, 0, 0);
        leftGame.render();
        leftScreenCopy.draw(gameContainer.getWidth()*55/100f, 0);
        yPanel.draw(gameContainer.getWidth()/100f*45, 0);
        xPanel.draw(0,gameCanvas.getScreen().getHeight());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        leftGame.update(i);
        rightGame.update(i);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key== Input.KEY_SPACE){
            rightGame.playerJump();
        }
        if (key== Input.KEY_LCONTROL){
            leftGame.playerJump();
        }
    }
}
