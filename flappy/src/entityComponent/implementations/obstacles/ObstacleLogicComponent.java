package entityComponent.implementations.obstacles;

import entityComponent.components.gameElements.SolidGameElementLogicComponent;

public abstract class ObstacleLogicComponent extends SolidGameElementLogicComponent {
    private boolean passed;
    public ObstacleLogicComponent(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
        passed = false;
    }
    abstract public boolean outOfBounds();
    abstract public boolean destroyOnHit();
    abstract public boolean isPeriodic();

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
