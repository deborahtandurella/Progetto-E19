package game.multiplayer;

import flappyEntities.Entity;
import flappyEntities.logic.LogicComponent;
import flappyEntities.logic.bird.BirdLogicComponent;
import game.player.Player;

/**
 *  Partita Online, questa interfaccia permette ai PowerUp di essere eseguiti sia sulla partita locale che su quella remota in modo indistinto
 */
public interface OnlineGame{
    long ONLINE_GAME_DURATION = 60*1000; //in millisecondi
    Entity getEntity(LogicComponent logicComponent);
    BirdLogicComponent getBird();
    void setBird(BirdLogicComponent bird);
    long getTimeLeft();
    Player getPlayer();
}
