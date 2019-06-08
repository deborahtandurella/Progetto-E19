package entityComponent.implementations.obstacles.pipes;

import entityComponent.GameElementEntity;
import graphics.Canvas;
import graphics.Screen;
import org.newdawn.slick.Graphics;

public class PipeEntity extends GameElementEntity {
    public PipeEntity(Canvas canvas, double x, double centerY, double speedX) {
        super(new PipeLogicComponent(x, centerY), new PipeGraphicComponent(canvas));
    }
}
