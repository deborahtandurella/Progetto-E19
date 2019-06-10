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

    @Override
    public int getID() {
        return 12;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Screen screen= new Screen(gameContainer.getWidth()/2, gameContainer.getHeight(), 0, 0);
        leftScreenCopy = new Image(screen.getWidth(), screen.getHeight());
        gameCanvas= new Canvas(screen, gameContainer.getGraphics());
        soundPlayer= new SoundPlayer();
    }
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        leftGame= new LocalGame(gameCanvas, settings);
        leftGame.addListener(soundPlayer);
        rightGame= new LocalGame(gameCanvas, settings);
        rightGame.addListener(soundPlayer);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        rightGame.render();
        graphics.copyArea(leftScreenCopy, 0, 0);
        leftGame.render();
        leftScreenCopy.draw(gameCanvas.getScreen().getWidth() , 0);
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
