package logic.gameElements;

import org.newdawn.slick.geom.Rectangle;

import static logic.gameConstants.GameConstants.ROCKET_HEIGHT;
import static logic.gameConstants.GameConstants.ROCKET_WIDTH;

public class Rocket extends GameElement implements SolidElement{
    private final double speedX;

    public Rocket(double x, double y, double speedX){
        super(x,y);
        addHitboxShape(new Rectangle( (float)(x+ ROCKET_WIDTH ), (float)(y+ ROCKET_HEIGHT), (float) (ROCKET_WIDTH ), (float)(ROCKET_HEIGHT)) );
        this.speedX = speedX;
    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }
}
