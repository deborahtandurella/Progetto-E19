package entityComponent.implementations.items.heart;

import entityComponent.components.gameElements.SolidGameElementLogicComponent;
import entityComponent.implementations.ScrollingElement;
import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.HEART_SIZE;
import static logic.gameConstants.GameConstants.PIPE_SPEED;

public class HeartLogicComponent extends SolidGameElementLogicComponent implements ScrollingElement {
    public HeartLogicComponent(double x, double y) {
        super(x, y, -PIPE_SPEED, 0);
        addHitboxShape(new Ellipse( (float)(x+HEART_SIZE/2), (float)(y+HEART_SIZE/2), (float) (HEART_SIZE/2), (float)(HEART_SIZE/2)));
    }

    @Override
    public boolean outOfBounds() {
        return (getX()+HEART_SIZE < 0);
    }
}
