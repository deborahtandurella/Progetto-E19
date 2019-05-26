package entitiyComponent.implementations.obstacles;

import entitiyComponent.components.gameElements.SolidGameElementLogicComponent;

public abstract class ObstacleLogicComponent extends SolidGameElementLogicComponent {

    public ObstacleLogicComponent(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
    }
    abstract public boolean outOfBounds();
    abstract public boolean destroyOnHit();
}
