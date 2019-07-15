package states.gameElements;

import org.newdawn.slick.geom.Shape;

public abstract class GameElement implements SolidElement{
    private double x;
    private double y;
    private Hitbox hitbox;

    public GameElement(double x, double y){
        this.x= x;
        this.y= y;
        hitbox= new Hitbox();
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
    public void addHitboxShape(Shape shape){
        hitbox.addShape(shape);
    }
    public void shiftHitbox(double deltaX, double deltaY){
        hitbox.shift(deltaX, deltaY);
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public boolean collide(SolidElement other) {
        return hitbox.collides(other.getHitbox());
    }
}
