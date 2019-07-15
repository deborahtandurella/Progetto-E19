package flappyEntities.graphic;

import graphics.Canvas;
import org.newdawn.slick.Image;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import static game.GameConstants.ROCKET_HEIGHT;
import static game.GameConstants.ROCKET_WIDTH;

public class RocketGraphicComponent extends GameElementGraphicComponent
{
    private Image rocketImage;

    public RocketGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            rocketImage = new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.ROCKET));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        getCanvas().drawImage(rocketImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) ROCKET_WIDTH,
                (float) ROCKET_HEIGHT);
    }
}
