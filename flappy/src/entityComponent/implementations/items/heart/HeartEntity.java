package entityComponent.implementations.items.heart;

import entityComponent.GameElementEntity;
import entityComponent.components.gameElements.GameElementGraphicComponent;
import entityComponent.components.gameElements.GameElementLogicComponent;

public class HeartEntity extends GameElementEntity {
    public HeartEntity(GameElementLogicComponent logicComponent, GameElementGraphicComponent graphicComponent) {
        super(logicComponent, graphicComponent);
    }
}
