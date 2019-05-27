package entitiyComponent.implementations.items.heart;

import entitiyComponent.GameElementEntity;
import entitiyComponent.components.gameElements.GameElementGraphicComponent;
import entitiyComponent.components.gameElements.GameElementLogicComponent;

public class HeartEntity extends GameElementEntity {
    public HeartEntity(GameElementLogicComponent logicComponent, GameElementGraphicComponent graphicComponent) {
        super(logicComponent, graphicComponent);
    }
}
