package states;

import graphics.SpriteDrawer;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DifficultyMenu extends BasicGameState implements ComponentListener {
    private static final int ID = 1;
    private GameContainer container;
    private Image background;
    private Image easy;
    private Image medium;
    private Image hard;
    private StateBasedGame stateBasedGame;
    private MouseOverArea easyButton;
    private MouseOverArea mediumButton;
    private MouseOverArea hardButton;
    private static float gameSpeed;



    private SpriteDrawer drawer;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        drawer = new SpriteDrawer(gameContainer.getWidth(),gameContainer.getHeight(),gameContainer.getWidth()/4);
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        easy = new Image("res/startE.png").getScaledCopy(250, 70);
        easyButton = new MouseOverArea(container, easy, 275, 200, 250, 70, this);
        medium = new Image("res/startM.png").getScaledCopy(250, 70);
        mediumButton = new MouseOverArea(container, medium, 275, 350, 250, 70, this);
        hard = new Image("res/startH.png").getScaledCopy(250, 70);
        hardButton = new MouseOverArea(container, hard, 275, 500, 250, 70, this);
        gameSpeed=0.7f;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        easyButton.render(gameContainer, graphics);
        mediumButton.render(gameContainer, graphics);
        hardButton.render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }

    public void componentActivated(AbstractComponent source) {
        if(source == easyButton ){
            gameSpeed = 0.5f;
            try {
                stateBasedGame.getState(2).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(2);
        }
        if(source == mediumButton ){
            try {
                stateBasedGame.getState(2).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(2);
        }
        if(source == hardButton ){
            gameSpeed = 0.9f;
            try {
                stateBasedGame.getState(2).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(2);
        }
    }
}
