package entityComponent.implementations.obstacles.rocket;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.SerializableElement;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import graphics.Canvas;
import org.newdawn.slick.geom.Rectangle;

import static logic.gameConstants.GameConstants.ROCKET_SIZE;
import static logic.gameConstants.GameConstants.ROCKET_SPEED;

public class RocketLogicComponent extends ObstacleLogicComponent {
    public RocketLogicComponent(double x, double y) {
        super(x, y, -ROCKET_SPEED, 0);
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
    @Override
    public SerializableElement getTransmittableVersion() {
        return new SerializableElement() {
            private double x = getX();
            private double y = getY();
            @Override
            public Entity instantiate(Canvas canvas) {
                return EntityFactory.makeRocket(x, y, canvas);
            }
        };
    }
}