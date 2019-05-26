package entitiyComponent;

import entitiyComponent.components.GraphicComponent;
import entitiyComponent.components.LogicComponent;
import entitiyComponent.components.gameElements.GameElementGraphicComponent;
import entitiyComponent.components.gameElements.GameElementLogicComponent;

public abstract class GameElementEntity extends Entity {
    public GameElementEntity(GameElementLogicComponent logicComponent, GameElementGraphicComponent graphicComponent) {
        super(logicComponent, graphicComponent);
        graphicComponent.setLogicComponent(logicComponent);
    }
}
