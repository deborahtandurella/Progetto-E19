package entityComponent.components.gameElements;

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

    @Override
    public void update(int i) {
        super.update(i);
        hitbox.shift(getSpeedX()*i, getSpeedY()*i);
    }

    public void addHitboxShape(Shape shape){
        hitbox.addShape(shape);
    }

    public Hitbox getHitbox() {
        return hitbox;
    }
    public boolean collide(SolidGameElementLogicComponent other) {
        return hitbox.collides(other.getHitbox());
    }
}
