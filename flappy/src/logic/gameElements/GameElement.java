package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public abstract class GameElement implements SolidElement{
    private double x;
    private double y;
    private Hitbox hitbox;

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
        hitbox.shift(x-getX(), 0);
        this.x = x;
    }
    public void setY(double y){
        hitbox.shift(0, y-getY());
        this.y = y;
    }

    public abstract void update(int delta);

    @Override
    public boolean collide(Hitbox otherHitbox) {
        return hitbox.collides(otherHitbox);
    }
}
