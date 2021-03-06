package game.itemGeneration.coin;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.obstacles.ObstacleLogicComponent;
import game.itemGeneration.obstacle.ObstacleListener;
import graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import static game.GameConstants.COIN_SIZE;
import static game.GameConstants.PIPE_WIDTH;

/**
 *  Generatore di monete. E' necessario che ascolti un ObstacleGenerator perchè possa produrre monete in modo sincronizzato
 */
public class CoinGenerator implements ObstacleListener {
    private ArrayList<CoinListener> listeners;
    private Canvas canvas;

    public CoinGenerator(Canvas canvas) {
        this.canvas = canvas;
        listeners= new ArrayList<>();
    }

    /**
     * Aggiunge un listener
     * @param listener
     */
    public void addListener(CoinListener listener){
        listeners.add(listener);
    }

    /**
     * La moneta generata viene inviata ai listeners
     * @param coin la moneta generata
     */
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
