package game;

import entityComponent.Entity;
import entityComponent.components.LogicComponent;
import entityComponent.implementations.bird.BirdLogicComponent;

public interface OnlineGame {
    Entity getEntity(LogicComponent logicComponent);
    BirdLogicComponent getBird();
    void setBird(BirdLogicComponent bird);
}
