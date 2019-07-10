package entityComponent.implementations.obstacles.rocket;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.SerializableElement;
import graphics.Canvas;

public class SerializableRocketLogic implements SerializableElement {
    private static final long serialVersionUID = -539210512250000001L;

    private Double x;
    private Double y;

    public SerializableRocketLogic(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Entity instantiate(Canvas canvas) {
        return EntityFactory.makeRocket(x, y, canvas);
    }
}
