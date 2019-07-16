package states.menu;

import Main.GiocoAStati;
import graphics.GUI.MultiplayerLoadingGUI;
import graphics.Screen;
import network.ConnectionHandle;
import network.ConnectionListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.game.MultiplayerState;

import java.util.Timer;
import java.util.TimerTask;

public class MultiplayerLoadingMenu extends AbstractMenuState implements ConnectionListener {
    private ConnectionHandle connectionHandle;
    private GiocoAStati giocoAStati;
    private boolean connecting;
    @Override
    public int getID() {
        return GiocoAStati.MULTI_LOADING;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        getGui().reload();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        giocoAStati = (GiocoAStati) stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new MultiplayerLoadingGUI(gameContainer, screen,this));

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }


    public void join(String ip, int port){
        connectionHandle = new ConnectionHandle();
        connectionHandle.addConnectionListener(this);
        Thread connectionThread = new Thread(() -> connectionHandle.setConnection(ip,port, giocoAStati.getPlayerInfo().getName()));
        connectionThread.start();
    }

    public void host(int port){
        connectionHandle = new ConnectionHandle();
        connectionHandle.addConnectionListener(this);
        Thread connectionThread = new Thread(() -> connectionHandle.setConnection(port, giocoAStati.getPlayerInfo().getName()));
        connectionThread.start();
    }

    private void startLoading(){
        (new Timer()).schedule( new TimerTask(){
            @Override
            public void run() {
                ((MultiplayerState) giocoAStati.getState(GiocoAStati.MULTIPLAYER)).setConnectionHandle(connectionHandle);
                connecting=true;
            }
        }, 1000);
        (new Timer()).schedule( new TimerTask(){
            @Override
            public void run() {
                giocoAStati.enterState(GiocoAStati.MULTIPLAYER);
                connecting=false;
            }
        }, 4000);
    }


    @Override
    public void connectionStatus(boolean connected) {
        connectionHandle.removeListener(this);
        if(connected)
            startLoading();
        else {
            giocoAStati.enterState(GiocoAStati.CONNECTION_ERROR_MENU);

        }
    }

    public boolean isConnecting() {
        return connecting;
    }

    public void back(){
        if (connectionHandle !=null)
            connectionHandle.closeConnection();
        giocoAStati.enterState(GiocoAStati.MULTI_MENU, new FadeOutTransition(), new FadeInTransition());
    }
}
