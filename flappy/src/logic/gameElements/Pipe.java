package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Pipe extends GameElement implements SolidElement {

    public Pipe(double x, double y){
        super(x, y);
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
