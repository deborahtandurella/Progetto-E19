package states.menu;

import Main.FlappyStateGame;
import graphics.GUI.MultiplayerMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * stato che permette l'inserimento delle informazioni necessarie per creare o accedere ad una partita Multiplayer
 */

public class MultiplayerMenu extends AbstractMenuState {
    private StateBasedGame stateBasedGame;
    private boolean initialized = false;
    @Override
    public int getID() {
        return FlappyStateGame.MULTI_MENU;
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
    public void backToMenu(){
        stateBasedGame.enterState(1,new FadeOutTransition(),new FadeInTransition());
    }

    /**
     * @param ip: indirizzo IP dell'host
     * @param port: porta dell'host
     */
    public void join(String ip,int port){
        stateBasedGame.enterState(FlappyStateGame.MULTI_LOADING,new FadeOutTransition(),new FadeInTransition());
        ((MultiplayerLoadingMenu)stateBasedGame.getState(FlappyStateGame.MULTI_LOADING)).join(ip, port);
    }

    /**
     * @param port: porta dell'host
     */
    public void host(int port){
        stateBasedGame.enterState(FlappyStateGame.MULTI_LOADING,new FadeOutTransition(),new FadeInTransition());
        ((MultiplayerLoadingMenu)stateBasedGame.getState(FlappyStateGame.MULTI_LOADING)).host(port);


    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        ((MultiplayerMenuGUI)getGui()).activate();
    }
}
