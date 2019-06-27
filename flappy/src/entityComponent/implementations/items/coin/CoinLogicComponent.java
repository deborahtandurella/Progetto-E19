package entityComponent.implementations.items.coin;
import entityComponent.implementations.items.ItemLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.COIN_SIZE;
import static logic.gameConstants.GameConstants.PIPE_SPEED;

public class CoinLogicComponent extends ItemLogicComponent {
    public CoinLogicComponent(double x, double y) {
        super(x, y, -PIPE_SPEED, 0);
        addHitboxShape(new Ellipse( (float)(x+COIN_SIZE/2), (float)(y+COIN_SIZE/2), (float) (COIN_SIZE/2), (float)(COIN_SIZE/2)));
    }

    @Override
    public boolean outOfBounds() {
        return (getX()+COIN_SIZE < 0);
    }
}
