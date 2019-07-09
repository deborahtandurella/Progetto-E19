package game.itemGeneration.heart;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.itemGeneration.obstacle.ObstacleListener;
import graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import static logic.gameConstants.GameConstants.HEART_SIZE;
import static logic.gameConstants.GameConstants.PIPE_WIDTH;

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
    protected void notifyListeners(Entity heart){
        for(HeartListener listener: listeners){
            listener.onHeartGenerated(heart);
        }
    }
    @Override
    public void onObstacleGenerated(Entity obstacle) {
        if ( ((ObstacleLogicComponent) obstacle.getLogicComponent()).isPeriodic()) {
            if ((new Random()).nextInt(15) == 0)
                generateHeart();
        }
    }
    private void generateHeart(){
        Entity heart= EntityFactory.makeHeart(1 + PIPE_WIDTH + 0.25 - HEART_SIZE*0.5, 0.25 + (new Random()).nextFloat()*0.5, canvas);
        notifyListeners(heart);
    }

}
