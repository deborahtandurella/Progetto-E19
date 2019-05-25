package entitiyComponent.components.gameElements;

import logic.gameElements.Hitbox;
import org.newdawn.slick.geom.Shape;

public abstract class SolidGameElementLogicComponent extends GameElementLogicComponent{
    private Hitbox hitbox;

    public SolidGameElementLogicComponent(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
        this.hitbox = new Hitbox();
    }


    public void setX(double x){
        super.setX(x);
        hitbox.shift(x-getX(), 0);
    }
    public void setY(double y){
        super.setY(y);
        hitbox.shift(0, y-getY());
    }

    public abstract void update(int delta);
    public void addHitboxShape(Shape shape){
        hitbox.addShape(shape);
    }
    public void shiftHitbox(double deltaX, double deltaY){
        hitbox.shift(deltaX, deltaY);
    }

    public Hitbox getHitbox() {
        return hitbox;
    }
    public boolean collide(SolidGameElementLogicComponent other) {
        return hitbox.collides(other.getHitbox());
    }
}
