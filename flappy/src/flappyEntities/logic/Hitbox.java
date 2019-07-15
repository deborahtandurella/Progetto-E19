package flappyEntities.logic;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Hitbox {
    private ArrayList<Shape> shapes;

    public Hitbox() {
        shapes = new ArrayList<>();
    }

    /**
     * Aggiunge una forma geometrica all'hitbox
     * @param shape la forma geometrica da aggiungere
     *
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    public ArrayList<Shape> getShapes(){
        return this.shapes;
    }

    /**
     * Controlla la collisione fra due hitbox
     * @param other l'altro hitbox con il quale avviene la collisione
     * @return se la collisione è avvenuta
     */
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

    /**
     *  Sposta l'hitbox
     * @param deltaX spostamento sull'asse delle x
     * @param deltaY spostamento sull'asse delle y
     */
    public void shift(double deltaX, double deltaY) {
        for(Shape shape: shapes){
            shape.setX(shape.getX()+ (float) deltaX);
            shape.setY(shape.getY()+ (float) deltaY);
        }
    }
}
