package game;

import entityComponent.Entity;
import entityComponent.components.LogicComponent;
import entityComponent.implementations.bird.BirdLogicComponent;

public interface OnlineGame {
    long ONLINE_GAME_DURATION = 120*1000; //in millisecondi
    Entity getEntity(LogicComponent logicComponent);
    BirdLogicComponent getBird();
    void setBird(BirdLogicComponent bird);
    long getTimeLeft();
}
