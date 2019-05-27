package entitiyComponent.implementations.obstacles.rocket;

import entitiyComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Screen;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import resources.PathHandler;
import resources.PathKeys;

import static logic.gameConstants.GameConstants.ROCKET_SIZE;

public class RocketGraphicComponent extends GameElementGraphicComponent
{
    private Image rocketImage;

    public RocketGraphicComponent(Graphics graphics, Screen screen) {
        super(graphics, screen);
        try {
            rocketImage = new Image(PathHandler.getInstance().getPath(PathKeys.ROCKET));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        getScreen().drawImage(rocketImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) ROCKET_SIZE,
                (float) ROCKET_SIZE);
    }
}
