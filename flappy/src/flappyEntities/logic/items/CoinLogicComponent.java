package flappyEntities.logic.items;

import flappyEntities.logic.ScrollingElement;
import flappyEntities.logic.SolidGameElementLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.COIN_SIZE;
import static logic.gameConstants.GameConstants.PIPE_SPEED;

public class CoinLogicComponent extends SolidGameElementLogicComponent implements ScrollingElement {
    private double size= COIN_SIZE;
    public CoinLogicComponent(double x, double y) {
        super(x, y, -PIPE_SPEED, 0);
        addHitboxShape(new Ellipse( (float)(x+size/2), (float)(y+size/2), (float) (size/2), (float)(size/2)));
    }

    @Override
    public boolean outOfBounds() {
        return (getX()+size < 0);
    }
}
