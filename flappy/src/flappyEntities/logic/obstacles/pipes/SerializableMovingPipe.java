package flappyEntities.logic.obstacles.pipes;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.SerializableEntity;
import graphics.Canvas;

/**
 *  Versione serializzabile del tubo mobile
 */
public class SerializableMovingPipe implements SerializableEntity {
    private static final long serialVersionUID = -539210512250000003L;

    private Double x;
    private Double y;
    private Double speedY;

    public SerializableMovingPipe(Double x, Double y, Double speedY) {
        this.x = x;
        this.y = y;
        this.speedY = speedY;
    }

    @Override
    public Entity instantiate(Canvas canvas) {
        return EntityFactory.makeMovingPipe(x, y, speedY, canvas);
    }
}
