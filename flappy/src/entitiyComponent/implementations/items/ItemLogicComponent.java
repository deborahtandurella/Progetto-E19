package entitiyComponent.implementations.items;

import entitiyComponent.components.gameElements.SolidGameElementLogicComponent;

public abstract class ItemLogicComponent extends SolidGameElementLogicComponent {
    public ItemLogicComponent(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
    }
    abstract public boolean outOfBounds();

}
