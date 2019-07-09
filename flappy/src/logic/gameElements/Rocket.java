package logic.gameElements;

import org.newdawn.slick.geom.Rectangle;

import static logic.gameConstants.GameConstants.ROCKET_SIZE;

public class Rocket extends GameElement implements SolidElement{
    private final double speedX;

    public Rocket(double x, double y, double speedX){
        super(x,y);
        addHitboxShape(new Rectangle( (float)(x+ROCKET_SIZE/8), (float)(y+ROCKET_SIZE/5), (float) (ROCKET_SIZE/8), (float)(ROCKET_SIZE/5)) );
        this.speedX = speedX;
    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }
}
