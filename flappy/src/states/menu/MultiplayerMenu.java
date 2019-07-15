package states.menu;

import Main.GiocoAStati;
import graphics.GUI.MultiplayerMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MultiplayerMenu extends AbstractMenuState {
    private StateBasedGame stateBasedGame;
    private boolean initialized = false;
    @Override
    public int getID() {
        return GiocoAStati.MULTI_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame= stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        if (!initialized){
            setGui(new MultiplayerMenuGUI(gameContainer, screen, this));
            initialized=true;
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        ((MultiplayerMenuGUI)getGui()).update();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        super.leave(container, game);
        ((MultiplayerMenuGUI)getGui()).deactivate();
    }
    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

    public void backToMenu(){
        stateBasedGame.enterState(1,new FadeOutTransition(),new FadeInTransition());
    }

    public void join(String ip,int port){
        stateBasedGame.enterState(GiocoAStati.MULTI_LOADING,new FadeOutTransition(),new FadeInTransition());
        ((MultiplayerLoadingMenu)stateBasedGame.getState(GiocoAStati.MULTI_LOADING)).join(ip, port);
    }

    public void host(int port){
        stateBasedGame.enterState(GiocoAStati.MULTI_LOADING,new FadeOutTransition(),new FadeInTransition());
        ((MultiplayerLoadingMenu)stateBasedGame.getState(GiocoAStati.MULTI_LOADING)).host(port);


    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        ((MultiplayerMenuGUI)getGui()).activate();
    }
}
