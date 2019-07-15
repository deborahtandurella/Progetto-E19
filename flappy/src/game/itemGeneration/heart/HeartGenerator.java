package game.itemGeneration.heart;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.obstacles.ObstacleLogicComponent;
import game.itemGeneration.obstacle.ObstacleListener;
import graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import static game.GameConstants.HEART_SIZE;
import static game.GameConstants.PIPE_WIDTH;

public class HeartGenerator implements ObstacleListener {
    private ArrayList<HeartListener> listeners;
    private Canvas canvas;

    public HeartGenerator(Canvas canvas) {
        this.canvas = canvas;
        listeners= new ArrayList<>();
    }

    public void addListener(HeartListener listener){
        listeners.add(listener);
    }
    private void notifyListeners(Entity heart){
        for(HeartListener listener: listeners){
            listener.onHeartGenerated(heart);
        }
    }
    @Override
    public void onObstacleGenerated(Entity obstacle) {
        if ( ((ObstacleLogicComponent) obstacle.getLogicComponent()).isPeriodic()) {
            if ((new Random()).nextInt(2) == 0)
                generateHeart();
        }
    }
    private void generateHeart(){
        Entity heart= EntityFactory.makeHeart(1 +PIPE_WIDTH*0.75 + 0.25  - HEART_SIZE*0.5, 0.25 + (new Random()).nextFloat()*0.5, canvas);
        notifyListeners(heart);
    }

}
