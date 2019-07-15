package flappyEntities.logic.obstacles.pipes;


import flappyEntities.logic.SerializableEntity;
import flappyEntities.logic.obstacles.ObstacleLogicComponent;
import org.newdawn.slick.geom.Rectangle;

import static game.GameConstants.*;

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
    public SerializableEntity getSerializableVersion() {
        return new SerializablePipe(getX(), getY());
    }
}