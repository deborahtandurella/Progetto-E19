package entityComponent.implementations.items.heart;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import graphics.Screen;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import static logic.gameConstants.GameConstants.HEART_SIZE;

public class HeartGraphicComponent extends GameElementGraphicComponent {
    private Image heartImage;

    public HeartGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            heartImage = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES,PathKeys.HEART));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        getCanvas().drawImage(heartImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) HEART_SIZE,
                (float) HEART_SIZE);
    }
}
