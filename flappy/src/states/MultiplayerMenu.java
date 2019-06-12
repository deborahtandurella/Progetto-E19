package states;

import graphics.GUI.MultiplayerMenuGUI;
import graphics.Screen;
import network.Client;
import network.Server;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MultiplayerMenu extends AbstractMenuState {
    private static final int ID = 5;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private Screen screen;
    private Client socketClient = new Client();
    private Server socketServer = new Server();
    private boolean initialized = false;
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        if (!initialized){
            setGui(new MultiplayerMenuGUI(container, screen, this));
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
        MultiplayerLoading.setIp(ip);
        MultiplayerLoading.setPort(port);
        stateBasedGame.enterState(6,new FadeOutTransition(),new FadeInTransition());
    }

    public void host(int port){
        MultiplayerLoading.setPort(port);
        stateBasedGame.enterState(6,new FadeOutTransition(),new FadeInTransition());
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        ((MultiplayerMenuGUI)getGui()).activate();
    }
}
