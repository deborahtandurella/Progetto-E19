package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public interface SolidElement {
    public boolean collide(ArrayList<Shape> shape);
}
