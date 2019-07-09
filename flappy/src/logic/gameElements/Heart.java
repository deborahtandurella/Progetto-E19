package logic.gameElements;

import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.HEART_SIZE;

public class Heart extends GameElement implements SolidElement {
    private final double speedX;

    public Heart(double x, double y, double speedX){
        super(x,y);
        addHitboxShape(new Ellipse( (float)(x+HEART_SIZE/2), (float)(y+HEART_SIZE/2), (float) (HEART_SIZE/2), (float)(HEART_SIZE/2)));
        this.speedX = speedX;
   }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }


}
