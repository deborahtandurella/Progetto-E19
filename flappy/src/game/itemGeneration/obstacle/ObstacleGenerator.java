package game.itemGeneration.obstacle;

import flappyEntities.Entity;
import graphics.Canvas;

import java.util.ArrayList;

import static game.GameConstants.PIPE_SPEED;
import static game.GameConstants.PIPE_WIDTH;

public abstract class ObstacleGenerator  {
    public static final double PIPE_PERIOD =( PIPE_WIDTH*0.5 + 0.5 ) / (PIPE_SPEED);
    private double time=0;
    private ArrayList<ObstacleListener> listeners;
    private Canvas canvas;

    protected ObstacleGenerator(Canvas canvas) {
        this.canvas=canvas;
        listeners = new ArrayList<>();
    }
    /**
     * Aggiunge un listener
     * @param listener
     */
    public void addListener(ObstacleListener listener){
        listeners.add(listener);
    }

    /**
     * Notifica la creazione di un ostacolo ai listener
     * @param obstacle l'ostacolo generato
     */
    protected void notifyListeners(Entity obstacle){
        for(ObstacleListener listener: listeners){
            listener.onObstacleGenerated(obstacle);
        }
    }

    /**
     * Periodicamente genera un ostacolo periodico
     * @param delta
     */
    public void update(double delta){
        time+=delta;
        if (time > PIPE_PERIOD) {
            time -= PIPE_PERIOD;
            generatePeriodicObstacle();
        }
    }

    /**
     *  Genera un ostacolo di tipo periodico
     */
    protected abstract void generatePeriodicObstacle();

    public Canvas getCanvas() {
        return canvas;
    }

    public double getTime() {
        return time;
    }
}
