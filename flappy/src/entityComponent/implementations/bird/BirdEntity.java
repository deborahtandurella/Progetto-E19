package entityComponent.implementations.bird;

import entityComponent.GameElementEntity;
import graphics.Canvas;
import graphics.Screen;
import org.newdawn.slick.Graphics;

public class BirdEntity extends GameElementEntity {
    public BirdEntity(Canvas canvas) {
        super(new BirdLogicComponent(0.2, 0.5), new BirdGraphicComponent(canvas));
    }
}
