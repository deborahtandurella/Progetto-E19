package flappyEntities.logic.items;

import flappyEntities.logic.ScrollingElement;
import flappyEntities.logic.SolidGameElementLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static game.GameConstants.HEART_SIZE;
import static game.GameConstants.PIPE_SPEED;

/**
 *  Componente logica del cuore
 */
public class HeartLogicComponent extends SolidGameElementLogicComponent implements ScrollingElement {
    public HeartLogicComponent(double x, double y) {
        super(x, y, -PIPE_SPEED, 0);
        addHitboxShape(new Ellipse( (float)(x+HEART_SIZE/2), (float)(y+HEART_SIZE/2), (float) (HEART_SIZE/2), (float)(HEART_SIZE/2)));
    }

    @Override
    public boolean outOfHorizontalBounds() {
        return (getX()+HEART_SIZE < 0);
    }
}
