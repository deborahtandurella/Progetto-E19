package entityComponent.implementations.obstacles.rocket;

import entityComponent.GameElementEntity;
import graphics.Canvas;


public class RocketEntity extends GameElementEntity {
    public RocketEntity(Canvas canvas, double x, double y, double speedX) {
        super(new RocketLogicComponent(x, y), new RocketGraphicComponent(canvas));
    }
}


