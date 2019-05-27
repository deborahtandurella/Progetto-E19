package entitiyComponent.implementations.items.heart;

import entitiyComponent.implementations.items.ItemLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.HEART_SIZE;

public class HeartLogicComponent extends ItemLogicComponent {
    public HeartLogicComponent(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
        addHitboxShape(new Ellipse( (float)(x+HEART_SIZE/2), (float)(y+HEART_SIZE/2), (float) (HEART_SIZE/2), (float)(HEART_SIZE/2)));

    }

    @Override
    public boolean outOfBounds() {
        return (getX()+HEART_SIZE < 0);
    }
}
