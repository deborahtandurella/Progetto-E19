package states;

import graphics.GUI.MultiplayerLoadingGUI;
import graphics.Screen;
import network.Client;
import network.ConnectionListener;
import network.Server;
import network.test.CommandHandler;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.test.MultiplayerState;
import Main.GiocoAStati;

import java.util.Timer;
import java.util.TimerTask;

public class MultiplayerLoading extends AbstractMenuState implements ConnectionListener {
    private static final int ID = 6;

    private Screen screen;

    private static int port;
    private static String ip;
    private String playerName;
    private CommandHandler commandHandler;

    private boolean connected;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        playerName= ((GiocoAStati) game).getPlayerInfo().getName();
        setGui(new MultiplayerLoadingGUI(container,screen,this));

        connected = false;
        if(ip.equals("")){
            host(port);
        }else{
            join(ip,port);
        }
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        ip = "";
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        if(connected){
            ((MultiplayerLoadingGUI)getGui()).connected();
            if (ip.equals("")){
                ((MultiplayerState) stateBasedGame.getState(13)).setCommandHandler(commandHandler);
            } else {
                ((MultiplayerState) stateBasedGame.getState(13)).setCommandHandler(commandHandler);
            }
            (new Timer()).schedule( new TimerTask(){
                @Override
                public void run() {
                    stateBasedGame.enterState(13);
                }
            }, 3000);
            connected=false;
            /*Thread th1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(3000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            th1.start();*/


        }
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }

    }

    private void join(String ip, int port){
        Client client = new Client();
        setCommandHandler(client);
        Thread thread = new Thread(() -> client.setConnection(ip,port, playerName));
        thread.start();
    }

    private void host(int port){
        Server server = new Server();
        setCommandHandler(server);
        Thread thread = new Thread(() -> server.setConnection(port, playerName));
        thread.start();
    }

    static void setPort(int port) {
        MultiplayerLoading.port = port;
    }

    static void setIp(String ip) {
        MultiplayerLoading.ip = ip;
    }

    private void setCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        commandHandler.addConnectionListener(this);

    }


    @Override
    public void connectionWorking(boolean connected) {
        this.connected=connected;
    }
}
