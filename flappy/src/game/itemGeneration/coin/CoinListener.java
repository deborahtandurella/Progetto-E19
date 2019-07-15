package game.itemGeneration.coin;

import flappyEntities.Entity;

public interface CoinListener {
    /**
     * Evento che si verifica quando viene generata una moneta
     * @param coin la moneta generata
     */
    void onCoinGenerated(Entity coin);
}
