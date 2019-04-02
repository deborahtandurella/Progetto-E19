package stati;

import elementi.Pipe;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.xml.transform.Source;

public class DifficultyMenu extends BasicGameState implements ComponentListener {
    private static final int ID = 3;
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
    private TrueTypeFont font;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        background= new Image("res/bg.jpeg");
        easy = new Image("res/startE.png").getScaledCopy(250, 70);
        easyButton = new MouseOverArea(container, easy, 100, 200, 250, 70, this);
        medium = new Image("res/startM.png").getScaledCopy(250, 70);
        mediumButton = new MouseOverArea(container, medium, 100, 350, 250, 70, this);
        hard = new Image("res/startH.png").getScaledCopy(250, 70);
        hardButton = new MouseOverArea(container, hard, 100, 500, 250, 70, this);
        gameSpeed=0.7f;
        java.awt.Font font1= new java.awt.Font("Verdana", java.awt.Font.BOLD, 32);
        font= new TrueTypeFont(font1, true);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0, container.getWidth(), container.getHeight());
        easyButton.render(gameContainer, graphics);
        mediumButton.render(gameContainer, graphics);
        hardButton.render(gameContainer, graphics);
        font.drawString(125,70,"MODALITÁ:");
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == easyButton ){
            gameSpeed = 0.4f;
            try {
                stateBasedGame.getState(1).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(1);
        }
        if(source == mediumButton ){
            try {
                stateBasedGame.getState(1).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(1);
        }
        if(source == hardButton ){
            gameSpeed = 0.9f;
            try {
                stateBasedGame.getState(1).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(1);
        }
    }

    public static float getGameSpeed() {
        return gameSpeed;
    }
}
