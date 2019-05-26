package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Hitbox {
    private ArrayList<Shape> shapes;

    public Hitbox() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    public ArrayList<Shape> getShapes(){
        return this.shapes;
    }
    public boolean collides(Hitbox other){
        for(Shape myShape: this.shapes){
            for(Shape otherShape: other.getShapes()){
                if(myShape.intersects(otherShape)){
                    return true;
                }
            }
        }
        return false;
    }
    public void shift(double deltaX, double deltaY) {
        for(Shape shape: shapes){
            shape.setX(shape.getX()+ (float) deltaX);
            shape.setY(shape.getY()+ (float) deltaY);
        }
    }
}
