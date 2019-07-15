package flappyEntities.logic.obstacles.pipes;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.SerializableEntity;
import graphics.Canvas;

/**
 *  Versione serializzabile del tubo semplice
 */
public class SerializablePipe implements SerializableEntity {
    private static final long serialVersionUID = -539210512250000002L;

    private Double x;
    private Double y;

    public SerializablePipe(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Entity instantiate(Canvas canvas) {
        return EntityFactory.makeNormalPipe(x, y, canvas);
    }
}
