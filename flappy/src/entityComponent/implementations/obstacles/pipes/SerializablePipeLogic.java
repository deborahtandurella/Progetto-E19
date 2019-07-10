package entityComponent.implementations.obstacles.pipes;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.SerializableElement;
import graphics.Canvas;

public class SerializablePipeLogic implements SerializableElement {
    private static final long serialVersionUID = -539210512250000002L;

    private Double x;
    private Double y;

    public SerializablePipeLogic(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Entity instantiate(Canvas canvas) {
        return EntityFactory.makeNormalPipe(x, y, canvas);
    }
}
