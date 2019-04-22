package logic.gameElements;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

import static logic.gameConstants.GameConstants.COIN_SIZE;

public class Coin extends GameElement implements SolidElement {
    private final double speedX;

   public Coin(double x, double y, double speedX){
       super(x,y);
       addHitboxShape(new Ellipse( (float)(x+COIN_SIZE/2), (float)(y+COIN_SIZE/2), (float) (COIN_SIZE/2), (float)(COIN_SIZE/2)));

       this.speedX = speedX;
   }

    @Override
    public void update(int delta) {
       setX(getX() - speedX*delta);
    }


}
