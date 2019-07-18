package game.gameEvents;

import java.util.ArrayList;

/**
 *  I GameEventDispatcher sono in grado di notificare ai listener gli eventi di gioco
 */
public class GameEventDispatcher {
    private ArrayList<GameEventListener> listeners;
    protected GameEventDispatcher(){
        listeners= new ArrayList<>();
    }

    /**
     * Notifica un evento di gioco ai listener
     * @param event evento da notificare
     */
    protected void notifyEvent(GameEventType event){
        for(GameEventListener listener: listeners){
            listener.gameEvent(event);
        }
    }

    /**
     * Aggiunge un listener
     * @param listener
     */
    public void addListener(GameEventListener listener){
        listeners.add(listener);
    }
}
