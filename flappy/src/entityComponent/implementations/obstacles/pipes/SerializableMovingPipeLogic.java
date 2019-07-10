package entityComponent.implementations.obstacles.pipes;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.SerializableElement;
import graphics.Canvas;

public class SerializableMovingPipeLogic implements SerializableElement {
    private static final long serialVersionUID = -539210512250000003L;

    private Double x;
    private Double y;
    private Double speedY;

    public SerializableMovingPipeLogic(Double x, Double y, Double speedY) {
        this.x = x;
        this.y = y;
        this.speedY = speedY;
    }

    @Override
    public Entity instantiate(Canvas canvas) {
        return EntityFactory.makeMovingPipe(x, y, speedY, canvas);
    }
}
