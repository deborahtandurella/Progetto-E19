package entityComponent.implementations.obstacles.rocket;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import org.newdawn.slick.Image;
import resources.PathHandler;
import resources.ResourcePacks;
import resources.Resources;

import static logic.gameConstants.GameConstants.ROCKET_SIZE;

public class RocketGraphicComponent extends GameElementGraphicComponent
{
    private Image rocketImage;

    public RocketGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            rocketImage = new Image(PathHandler.getInstance().getPath(ResourcePacks.SPRITES, Resources.ROCKET));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        getCanvas().drawImage(rocketImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) ROCKET_SIZE,
                (float) ROCKET_SIZE);
    }
}
