package entityComponent.implementations.obstacles.rocket;

import entityComponent.implementations.SerializableElement;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import org.newdawn.slick.geom.Rectangle;

import static logic.gameConstants.GameConstants.*;

public class RocketLogicComponent extends ObstacleLogicComponent {
    public RocketLogicComponent(double x, double y) {
        super(x, y, -ROCKET_SPEED, 0);
        addHitboxShape(new Rectangle( (float)(x+ ROCKET_WIDTH), (float)(y+ ROCKET_HEIGHT), (float) (ROCKET_WIDTH), (float)(ROCKET_HEIGHT )) );

    }

    @Override
    public boolean isPeriodic() {
        return false;
    }

    @Override
    public boolean outOfBounds() {
        return (getX()+ ROCKET_WIDTH < 0);
    }

    @Override
    public boolean destroyOnHit() {
        return true;
    }
    @Override
    public SerializableElement getTransmittableVersion() {
        return new SerializableRocketLogic(getX(), getY());
    }
}