package states;

import graphics.GUI.MultiplayerMenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import network.Client;
import network.Server;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MultiplayerMenu extends BasicGameState {
    private static final int ID = 5;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private MultiplayerMenuGUI gui;
    private Screen screen;
    private SpriteDrawer drawer;
    private Client socketClient = new Client();
    private Server socketServer = new Server();


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        gui = new MultiplayerMenuGUI(container, screen, this);
        drawer = new SpriteDrawer(new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0));
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        gui.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        gui.update();
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }

    public void backToMenu(){
        try {
            stateBasedGame.getState(1).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(1,new FadeOutTransition(),new FadeInTransition());

    }

    public void join(String ip,int port){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                socketClient.SetConnection(ip,port);
            }
        });
        thread.start();
    }

    public void host(int port){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                socketServer.SetConnection(port);
            }
        });
        thread.start();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);

    }
}
