package entityComponent.implementations.obstacles.pipes;

import entityComponent.GameElementEntity;
import graphics.Screen;
import org.newdawn.slick.Graphics;

public class PipeEntity extends GameElementEntity {
    public PipeEntity(Graphics graphics, Screen screen, double x, double centerY, double speedX) {
        super(new PipeLogicComponent(x, centerY, speedX), new PipeGraphicComponent(graphics, screen));
    }
}
