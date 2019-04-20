package states;

import gameMusic.MusicPlayer;
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
    private Image single;
    private Image multi;
    private StateBasedGame stateBasedGame;
    private MouseOverArea singleButton;
    private MouseOverArea multiButton;
    private SpriteDrawer drawer;
    private MusicPlayer musicPlayer;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        musicPlayer = new MusicPlayer();
        drawer = new SpriteDrawer(gameContainer.getWidth(),gameContainer.getHeight());
        single = new Image("res/play.png").getScaledCopy(150, 70);
        singleButton = new MouseOverArea(container, single, 300, 150, 200, 70, this);
        multi = new Image("res/play.png").getScaledCopy(150, 70);
        multiButton = new MouseOverArea(container, multi, 300, 350, 200, 70, this);
        musicPlayer.BackgroudMusic();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        singleButton.render(gameContainer, graphics);
        multiButton.render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException { }
    public void componentActivated(AbstractComponent source) {
        if (source == singleButton ) {
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        if (source == multiButton ) {
            stateBasedGame.enterState(4, new FadeOutTransition(), new FadeInTransition());
        }
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }
}
