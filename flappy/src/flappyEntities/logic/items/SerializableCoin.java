package flappyEntities.logic.items;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.SerializableEntity;
import graphics.Canvas;

public class SerializableCoin implements SerializableEntity {
    private static final long serialVersionUID = -539210512250000003L;

    private Double x;
    private Double y;

    public SerializableCoin(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Entity instantiate(Canvas canvas) {
        return EntityFactory.makeCoin(x, y, canvas);
    }
}
