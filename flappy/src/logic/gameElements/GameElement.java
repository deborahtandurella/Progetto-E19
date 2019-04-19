package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public abstract class GameElement implements SolidElement{
    private double x;
    private double y;
    protected ArrayList<Shape> shape;

    public GameElement(double x, double y){
        this.x= x;
        this.y= y;
    }

    public double getX(){
        return this.x;
    }
    public  double getY(){
        return this.y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public abstract void update(int delta);

    @Override
    public boolean collide(ArrayList<Shape> otherShape) {
        for(Shape myShape: shape){
            for(Shape yourShape: otherShape){
                if (myShape.intersects(yourShape)){
                    return true;
                }
            }

        }
        return false;
    }
}
