package game.itemGeneration.obstacle;

import flappyEntities.Entity;

public interface ObstacleListener {
    /**
     * Evento che si attiva quando viene generato un ostacolo
     * @param obstacle l'ostacolo genearto
     */
    void onObstacleGenerated(Entity obstacle);
}
