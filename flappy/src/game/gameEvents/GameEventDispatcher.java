package game.gameEvents;


import java.util.ArrayList;

public class GameEventDispatcher {
    private ArrayList<GameEventListener> listeners;
    protected GameEventDispatcher(){
        listeners= new ArrayList<>();
    }
    protected void notifyEvent(GameEventType event){
        for(GameEventListener listener: listeners){
            listener.gameEvent(event);
        }
    }
    public void addListener(GameEventListener listener){
        listeners.add(listener);
    }
}
