package entitiyComponent.implementations.obstacles.pipes;


import entitiyComponent.implementations.obstacles.ObstacleLogicComponent;
import org.newdawn.slick.geom.Rectangle;

import static logic.gameConstants.GameConstants.PIPE_FREE_SPACE;
import static logic.gameConstants.GameConstants.PIPE_HEIGHT;
import static logic.gameConstants.GameConstants.PIPE_WIDTH;

public class PipeLogicComponent extends ObstacleLogicComponent {
    private boolean passed;

    public PipeLogicComponent(double x, double centerY, double speedX) {
        super(x, centerY, speedX, 0);
        addHitboxShape(new Rectangle((float) x, (float) (centerY-PIPE_HEIGHT-PIPE_FREE_SPACE/2d), (float)PIPE_WIDTH, (float) PIPE_HEIGHT));
        addHitboxShape(new Rectangle((float) x, (float) (centerY+PIPE_FREE_SPACE/2d),  (float)PIPE_WIDTH, (float) PIPE_HEIGHT));
        passed = false;
    }

    @Override
    public boolean outOfBounds() {
        if (getX()+PIPE_WIDTH<0)
            return true;
        else
            return false;
    }

    @Override
    public boolean destroyOnHit() {
        return false;
    }


    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}