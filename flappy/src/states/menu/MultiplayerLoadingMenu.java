package states.menu;

import states.FlappyStateGame;
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

/**
 * menu pre-partita Multiplayer, si rimane in questo stato finché i due giocatori non sono connessi oppure finché
 * non si decide di tornare al GeneralMenu
 */

public class MultiplayerLoadingMenu extends AbstractMenuState implements ConnectionListener {
    private ConnectionHandle connectionHandle;
    private FlappyStateGame flappyStateGame;
    private boolean connecting;
    @Override
    public int getID() {
        return FlappyStateGame.MULTI_LOADING;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        getGui().reload();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        flappyStateGame = (FlappyStateGame) stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new MultiplayerLoadingGUI(gameContainer, screen,this));

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }


    /**
     * @param ip: indirizzo IP dell'host
     * @param port: porta dell'host
     */
    public void join(String ip, int port){
        connectionHandle = new ConnectionHandle();
        connectionHandle.addConnectionListener(this);
        Thread connectionThread = new Thread(() -> connectionHandle.setConnection(ip,port, flappyStateGame.getPlayerInfo().getName()));
        connectionThread.start();
    }

    /**
     * @param port: porta dell'host
     */
    public void host(int port){
        connectionHandle = new ConnectionHandle();
        connectionHandle.addConnectionListener(this);
        Thread connectionThread = new Thread(() -> connectionHandle.setConnection(port, flappyStateGame.getPlayerInfo().getName()));
        connectionThread.start();
    }

    private void startLoading(){
        (new Timer()).schedule( new TimerTask(){
            @Override
            public void run() {
                ((MultiplayerState) flappyStateGame.getState(FlappyStateGame.MULTIPLAYER)).setConnectionHandle(connectionHandle);
                connecting=true;
            }
        }, 1000);
        (new Timer()).schedule( new TimerTask(){
            @Override
            public void run() {
                flappyStateGame.enterState(FlappyStateGame.MULTIPLAYER);
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
            flappyStateGame.enterState(FlappyStateGame.CONNECTION_ERROR_MENU);

        }
    }

    public boolean isConnecting() {
        return connecting;
    }

    public void back(){
        if (connectionHandle !=null)
            connectionHandle.closeConnection();
        flappyStateGame.enterState(FlappyStateGame.MULTI_MENU, new FadeOutTransition(), new FadeInTransition());
    }
}
