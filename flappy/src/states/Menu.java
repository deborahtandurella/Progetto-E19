package states;

import gameMusic.MusicPlayer;
import graphics.GUI.MenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends AbstractMenuState{
    private static final int ID = 1;

    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private SpriteDrawer drawer;
    private MusicPlayer musicPlayer;
    private Screen screen;

    public Menu(){
        super();
    }
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
        setGui(new MenuGUI(gameContainer, screen, this));
        musicPlayer.backgroundMusic();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException { }

    public void single(){
        stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
    }

    public void multi(){
        stateBasedGame.enterState(5, new FadeOutTransition(), new FadeInTransition());
    }


    public void leaderBoard(){
        try {
            stateBasedGame.getState(9).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(9, new FadeOutTransition(), new FadeInTransition());
    }


    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

    public void custom(){
        stateBasedGame.enterState(11, new FadeOutTransition(), new FadeInTransition());
    }

}
