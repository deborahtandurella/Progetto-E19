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

public class Menu extends BasicGameState implements ComponentListener {
    private static final int ID = 0;

    private GameContainer container;
    private Image play;
    private StateBasedGame stateBasedGame;
    private MouseOverArea playButton;
    private SpriteDrawer drawer;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        drawer = new SpriteDrawer(gameContainer.getWidth(),gameContainer.getHeight());
        play = new Image("res/play.png").getScaledCopy(150, 70);
        playButton = new MouseOverArea(container, play, 300, 250, 200, 70, this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        playButton.render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException { }
    public void componentActivated(AbstractComponent source) {
        if (source == playButton ) {
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }
}
