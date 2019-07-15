package game.itemGeneration.heart;

import flappyEntities.Entity;

public interface HeartListener {
    /**
     * Evento che si verifica quando viene generato un cuore
     * @param heart il cuore generato
     */
    void onHeartGenerated(Entity heart);
}
