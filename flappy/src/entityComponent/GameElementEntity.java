package entityComponent;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import entityComponent.components.gameElements.GameElementLogicComponent;

public abstract class GameElementEntity extends Entity {
    public GameElementEntity(GameElementLogicComponent logicComponent, GameElementGraphicComponent graphicComponent) {
        super(logicComponent, graphicComponent);
        graphicComponent.setLogicComponent(logicComponent);
    }
}
