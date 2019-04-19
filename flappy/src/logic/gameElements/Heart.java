package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Heart extends GameElement implements SolidElement {

   public Heart(double x, double y){
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
