package flappyEntities.logic.items;

import flappyEntities.logic.HasSerializableVersion;
import flappyEntities.logic.ScrollingElement;
import flappyEntities.logic.SerializableEntity;
import flappyEntities.logic.SolidGameElementLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static game.GameConstants.COIN_SIZE;
import static game.GameConstants.PIPE_SPEED;

/**
 *  Componente logica della moneta
 */
public class CoinLogicComponent extends SolidGameElementLogicComponent implements ScrollingElement, HasSerializableVersion {
    private double size = COIN_SIZE;
    public CoinLogicComponent(double x, double y) {
        super(x, y, -PIPE_SPEED, 0);
        addHitboxShape(new Ellipse( (float)(x+size/2), (float)(y+size/2), (float) (size/2), (float)(size/2)));
    }

    @Override
    public boolean outOfHorizontalBounds() {
        return (getX()+size < 0);
    }

    @Override
    public SerializableEntity getSerializableVersion() {
        return new SerializableCoin(getX(), getY());
    }
}
