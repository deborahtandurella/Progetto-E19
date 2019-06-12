package states;

import graphics.GUI.MultiplayerLoadingGUI;
import graphics.Screen;
import network.Client;
import network.Server;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MultiplayerLoading extends AbstractMenuState {
    private static final int ID = 6;

    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private Screen screen;

    private static int port;
    private static String ip;

    private Client socketClient = new Client();
    private Server socketServer = new Server();

    private static boolean connected;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        connected = false;
        if(ip.equals("")){
            host(port);
        }else{
            join(ip,port);
        }
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new MultiplayerLoadingGUI(container,screen,this));
        ip = "";
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(connected){
            ((MultiplayerLoadingGUI)getGui()).connected();
        }
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }

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

    public static void setPort(int port) {
        MultiplayerLoading.port = port;
    }

    public static void setIp(String ip) {
        MultiplayerLoading.ip = ip;
    }

    public static void isConnected(){
        connected = true;
    }
}
