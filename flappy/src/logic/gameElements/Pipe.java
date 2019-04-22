package logic.gameElements;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

import static logic.gameConstants.GameConstants.PIPE_FREE_SPACE;
import static logic.gameConstants.GameConstants.PIPE_HEIGHT;
import static logic.gameConstants.GameConstants.PIPE_WIDTH;

public class Pipe extends GameElement implements SolidElement {
    private final double speedX;


    public Pipe(double x, double centerY, double speedX){
        super(x, centerY);
        this.speedX = speedX;
        setX(x);
        setY(centerY);
        addHitboxShape(new Rectangle((float) x, (float) (centerY-PIPE_HEIGHT-PIPE_FREE_SPACE/2d), (float)PIPE_WIDTH, (float) PIPE_HEIGHT));
        addHitboxShape(new Rectangle((float) x, (float) (centerY+PIPE_FREE_SPACE/2d),  (float)PIPE_WIDTH, (float) PIPE_HEIGHT));

    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }

    public double getSpeedX() {
        return speedX;
    }
}
