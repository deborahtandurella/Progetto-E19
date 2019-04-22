package states;

import graphics.SpriteDrawer;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class SingleplayerReplayMenu extends BasicGameState implements ComponentListener {
    private static final int ID = 3;

    private GameContainer container;
    private Image gameOver;
    private Image yes;
    private Image no;
    private StateBasedGame stateBasedGame;
    private MouseOverArea yesButton;
    private MouseOverArea noButton;
    private SpriteDrawer drawer;

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container = gameContainer;
        this.stateBasedGame = stateBasedGame;
        drawer = new SpriteDrawer(container.getWidth(),container.getHeight(),container.getWidth()/4);
        yes = new Image("res/play.png").getScaledCopy(150,70);
        yesButton = new MouseOverArea(container, yes, 300, 150, 200, 70, this);
        no = new Image("res/play.png").getScaledCopy(150,70);
        noButton = new MouseOverArea(container, no, 300, 350, 200, 70, this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        yesButton.render(container,graphics);
        noButton.render(container,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == yesButton){
            try {
                stateBasedGame.getState(2).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(2,new FadeOutTransition(),new FadeInTransition());
        }
        if(source == noButton){
            try {
                stateBasedGame.getState(0).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(0,new FadeOutTransition(),new FadeInTransition());
        }
    }
}
