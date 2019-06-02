package game.gameEvents;


import java.util.ArrayList;

public class GameEventDispatcher {
    private ArrayList<GameEventListener> listeners;
    public GameEventDispatcher(){
        listeners= new ArrayList<GameEventListener>();
    }
    public void notifyEvent(GameEventType event){
        for(GameEventListener listener: listeners){
            listener.gameEvent(event);
        }
    }
    public void addListener(GameEventListener listener){
        listeners.add(listener);
    }
}
