package entityComponent.implementations.bird;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Screen;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import static logic.gameConstants.GameConstants.*;

public class BirdGraphicComponent extends GameElementGraphicComponent
{
    private Image birdImage;

    public BirdGraphicComponent(Graphics graphics, Screen screen) {
        super(graphics, screen);
        try {
            birdImage = new Image("res/bird.png").getScaledCopy((int) (BIRD_WIDTH * screen.getWidth()), (int) (BIRD_HEIGHT * screen.getHeight()));
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        float angle = (float) (180*Math.atan2(getLogicComponent().getSpeedY(), JUMP_SPEED)/Math.PI);
        getScreen().drawRotatedImage(birdImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) BIRD_WIDTH,
                (float) BIRD_HEIGHT,
                angle);
    }
}

