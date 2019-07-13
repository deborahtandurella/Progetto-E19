package entityComponent;

import entityComponent.components.LogicComponent;
import entityComponent.components.gameElements.GameElementGraphicComponent;
import entityComponent.components.gameElements.GameElementLogicComponent;

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
