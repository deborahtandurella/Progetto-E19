package states.gameElements;

import org.newdawn.slick.geom.Rectangle;

import static game.GameConstants.*;

public class Pipe extends GameElement implements SolidElement {
    private final double speedX;
    private boolean passed;


    public Pipe(double x, double centerY, double speedX){
        super(x, centerY);
        this.speedX = speedX;
        setX(x);
        setY(centerY);
        addHitboxShape(new Rectangle((float) x, (float) (centerY-PIPE_HEIGHT-PIPE_FREE_SPACE/2d), (float)PIPE_WIDTH, (float) PIPE_HEIGHT));
        addHitboxShape(new Rectangle((float) x, (float) (centerY+PIPE_FREE_SPACE/2d),  (float)PIPE_WIDTH, (float) PIPE_HEIGHT));
        passed = false;
    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }

    public void setPassed(boolean passed){
        this.passed = passed;
    }

    public boolean isPassed(){
        return passed;
    }

    public double getSpeedX() {
        return speedX;
    }
}
