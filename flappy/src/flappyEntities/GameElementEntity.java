package flappyEntities;

import flappyEntities.graphic.GameElementGraphicComponent;
import flappyEntities.logic.GameElementLogicComponent;
import flappyEntities.logic.LogicComponent;

public class GameElementEntity extends Entity {
    public GameElementEntity(GameElementLogicComponent logicComponent, GameElementGraphicComponent graphicComponent) {
        super(logicComponent, graphicComponent);
        graphicComponent.setLogicComponent(logicComponent);
    }

    @Override
    public void setLogicComponent(LogicComponent logicComponent) {
        super.setLogicComponent(logicComponent);
        ((GameElementGraphicComponent) getGraphicComponent()).setLogicComponent((GameElementLogicComponent)logicComponent);
    }
}
