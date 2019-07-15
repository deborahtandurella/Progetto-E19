package game.multiplayer;

import flappyEntities.Entity;
import flappyEntities.logic.LogicComponent;
import flappyEntities.logic.bird.BirdLogicComponent;

public interface OnlineGame {
    long ONLINE_GAME_DURATION = 120*1000; //in millisecondi
    Entity getEntity(LogicComponent logicComponent);
    BirdLogicComponent getBird();
    void setBird(BirdLogicComponent bird);
    long getTimeLeft();
}
