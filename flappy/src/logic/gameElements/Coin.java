package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Coin extends GameElement implements SolidElement {

   public Coin(double x, double y){
       super(x,y);
   }

    @Override
    public boolean collide(ArrayList<Shape> shape) {
        return false;
    }

    @Override
    public Shape getShape() {
        return null;
    }
}
