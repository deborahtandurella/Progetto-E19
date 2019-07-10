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


    private String playerName;
    private CommandHandler commandHandler;
    private GiocoAStati giocoAStati;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        playerName= ((GiocoAStati) game).getPlayerInfo().getName();
        setGui(new MultiplayerLoadingGUI(container,screen,this));
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        giocoAStati = (GiocoAStati) stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }

    }

    public void join(String ip, int port){
        Client client = new Client();
        setCommandHandler(client);
        Thread connectionThread = new Thread(() -> client.setConnection(ip,port, playerName));
        connectionThread.start();
    }

    public void host(int port){
        Server server = new Server();
        setCommandHandler(server);
        Thread connectionThread = new Thread(() -> server.setConnection(port, playerName));
        connectionThread.start();
    }

    private void startLoading(){
        (new Timer()).schedule( new TimerTask(){
            @Override
            public void run() {
                ((MultiplayerLoadingGUI)getGui()).connected();
                ((MultiplayerState) giocoAStati.getState(13)).setCommandHandler(commandHandler);
            }
        }, 1000);
        (new Timer()).schedule( new TimerTask(){
            @Override
            public void run() {
                giocoAStati.enterState(13);
            }
        }, 3000);
    }

    private void setCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        commandHandler.addConnectionListener(this);

    }


    @Override
    public void connectionWorking(boolean connected) {
        if(connected)
            startLoading();
    }
}
