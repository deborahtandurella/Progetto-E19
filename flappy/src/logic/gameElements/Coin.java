package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Coin extends GameElement implements SolidElement {
    private final double speedX;

   public Coin(double x, double y, double speedX){
       super(x,y);
       this.speedX = speedX;
   }

    @Override
    public void update(int delta) {
       setX(getX() - speedX);
    }

    @Override
    public boolean collide(ArrayList<Shape> shape) {
        return false;
    }

}
