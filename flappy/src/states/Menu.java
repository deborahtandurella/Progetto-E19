package states;

import gameMusic.MusicPlayer;
import graphics.GUI.MenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState{
    private static final int ID = 0;

    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private SpriteDrawer drawer;
    private MusicPlayer musicPlayer;
    private MenuGUI gui;
    private Screen screen;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        musicPlayer = new MusicPlayer();
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        drawer = new SpriteDrawer(screen);
        gui = new MenuGUI(gameContainer, screen, this);

        musicPlayer.backgroundMusic();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        gui.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException { }

    public void single(){
        try {
            stateBasedGame.getState(1).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
    }

    public void multi(){
        try {
            stateBasedGame.getState(4).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(4, new FadeOutTransition(), new FadeInTransition());
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }
}
