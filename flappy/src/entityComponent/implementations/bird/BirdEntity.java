package entityComponent.implementations.bird;

import entityComponent.GameElementEntity;
import graphics.Canvas;

public class BirdEntity extends GameElementEntity {
    public BirdEntity(Canvas canvas) {
        super(new BirdLogicComponent(0.2, 0.5), new BirdGraphicComponent(canvas));
    }
}
