package entityComponent.implementations.obstacles.pipes;


import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.SerializableElement;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import graphics.Canvas;
import org.newdawn.slick.geom.Rectangle;

import static logic.gameConstants.GameConstants.*;

public class PipeLogicComponent extends ObstacleLogicComponent {
    public PipeLogicComponent(double x, double centerY) {
        super(x, centerY, -PIPE_SPEED, 0);
        addHitboxShape(new Rectangle((float) x, (float) (centerY-PIPE_HEIGHT-PIPE_FREE_SPACE/2d), (float)PIPE_WIDTH, (float) PIPE_HEIGHT));
        addHitboxShape(new Rectangle((float) x, (float) (centerY+PIPE_FREE_SPACE/2d),  (float)PIPE_WIDTH, (float) PIPE_HEIGHT));
    }

    @Override
    public boolean outOfBounds() {
        return getX() + PIPE_WIDTH < 0;
    }

    @Override
    public boolean isPeriodic() {
        return true;
    }

    @Override
    public boolean destroyOnHit() {
        return false;
    }

    @Override
    public SerializableElement getTransmittableVersion() {
        return new SerializableElement() {
            private double x= getX();
            private double y= getY();
            @Override
            public Entity instantiate(Canvas canvas) {
                return EntityFactory.makeNormalPipe(x, y, canvas);
            }
        };
    }
}