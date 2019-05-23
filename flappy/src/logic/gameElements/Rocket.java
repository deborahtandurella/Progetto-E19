package logic.gameElements;

import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.HEART_SIZE;
import static logic.gameConstants.GameConstants.ROCKET_SIZE;

public class Rocket extends GameElement implements SolidElement{
    private final double speedX;

    public Rocket(double x, double y, double speedX){
        super(x,y);
        addHitboxShape(new Ellipse( (float)(x+ROCKET_SIZE/8), (float)(y+ROCKET_SIZE/8), (float) (ROCKET_SIZE/8), (float)(ROCKET_SIZE/8)));
        this.speedX = speedX;
    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }
}
