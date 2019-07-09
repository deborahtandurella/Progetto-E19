package entityComponent.implementations.items.coin;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import static logic.gameConstants.GameConstants.COIN_SIZE;

public class CoinGraphicComponent extends GameElementGraphicComponent {

    private Image coinImage;

    public CoinGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            coinImage = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.COIN));
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

        @Override
        public void render() {
        getCanvas().drawImage(coinImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) COIN_SIZE,
                (float) COIN_SIZE);
    }
}
