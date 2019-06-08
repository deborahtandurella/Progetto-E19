package game.itemGeneration.obstacle;

import entityComponent.Entity;
import game.itemGeneration.ObstacleListener;
import graphics.Canvas;

import java.util.ArrayList;

import static logic.gameConstants.GameConstants.PIPE_SPEED;
import static logic.gameConstants.GameConstants.PIPE_WIDTH;

public abstract class ObstacleGenerator  {
    public static final double PIPE_PERIOD =( PIPE_WIDTH*0.5 + 0.5 ) / (PIPE_SPEED);
    private int time=0;
    private ArrayList<ObstacleListener> listeners;
    private Canvas canvas;

    protected ObstacleGenerator(Canvas canvas) {
        this.canvas=canvas;
    }

    public void addListener(ObstacleListener listener){
        listeners.add(listener);
    }
    protected void notifyListeners(Entity obstacle){
        for(ObstacleListener listener: listeners){
            listener.onObstacleGenerated(obstacle);
        }
    }
    public void update(int delta){
        time+=delta;
        if (time > PIPE_PERIOD) {
            time -= PIPE_PERIOD;
            generatePeriodicObstacle();
        }
    };
    protected abstract void generatePeriodicObstacle();

    public Canvas getCanvas() {
        return canvas;
    }

    public int getTime() {
        return time;
    }
}
