package flappyEntities.logic.obstacles.rocket;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.SerializableEntity;
import graphics.Canvas;

/**
 *  Versione serializzabile del razzo
 */
public class SerializableRocket implements SerializableEntity {
    private static final long serialVersionUID = -539210512250000001L;

    private Double x;
    private Double y;

    public SerializableRocket(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Entity instantiate(Canvas canvas) {
        return EntityFactory.makeRocket(x, y, canvas);
    }
}
