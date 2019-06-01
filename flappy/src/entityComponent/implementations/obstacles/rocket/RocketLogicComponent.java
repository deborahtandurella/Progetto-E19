package entityComponent.implementations.obstacles.rocket;

import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import org.newdawn.slick.geom.Rectangle;

import static logic.gameConstants.GameConstants.ROCKET_SIZE;

public class RocketLogicComponent extends ObstacleLogicComponent {
    public RocketLogicComponent(double x, double y, double speedX) {
        super(x, y, speedX, 0);
        addHitboxShape(new Rectangle( (float)(x+ROCKET_SIZE/8), (float)(y+ROCKET_SIZE/5), (float) (ROCKET_SIZE/8), (float)(ROCKET_SIZE/5)) );

    }

    @Override
    public boolean isPeriodic() {
        return false;
    }

    @Override
    public boolean outOfBounds() {
        return (getX()+ROCKET_SIZE < 0);
    }

    @Override
    public boolean destroyOnHit() {
        return true;
    }
}