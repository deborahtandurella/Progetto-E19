package game.itemGeneration.coin;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.itemGeneration.obstacle.ObstacleListener;
import graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import static logic.gameConstants.GameConstants.COIN_SIZE;
import static logic.gameConstants.GameConstants.PIPE_WIDTH;

public class CoinGenerator implements ObstacleListener {
    private ArrayList<CoinListener> listeners;
    private Canvas canvas;

    public CoinGenerator(Canvas canvas) {
        this.canvas = canvas;
        listeners= new ArrayList<>();
    }

    public void addListener(CoinListener listener){
        listeners.add(listener);
    }
    private void notifyListeners(Entity coin){
        for(CoinListener listener: listeners){
            listener.onCoinGenerated(coin);
        }
    }
    @Override
    public void onObstacleGenerated(Entity obstacle) {
        if ( ((ObstacleLogicComponent) obstacle.getLogicComponent()).isPeriodic()) {
            if ((new Random()).nextInt(3) == 0){
                generateCoin();
            }
        }
    }
    private void generateCoin(){
        Entity coin= EntityFactory.makeCoin(1 + PIPE_WIDTH*0.75 + 0.25 - COIN_SIZE*0.5, 0.25 + (new Random()).nextFloat()*0.5, canvas);
        notifyListeners(coin);
    }

}
