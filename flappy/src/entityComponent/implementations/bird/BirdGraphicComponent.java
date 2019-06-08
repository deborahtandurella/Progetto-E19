package entityComponent.implementations.bird;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import graphics.Screen;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import static logic.gameConstants.GameConstants.*;

public class BirdGraphicComponent extends GameElementGraphicComponent
{
    private Image birdImage;

    public BirdGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            birdImage = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.BIRD));
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        float angle = (float) (180*Math.atan2(getLogicComponent().getSpeedY(), JUMP_SPEED)/Math.PI);
        if( ((BirdLogicComponent) getLogicComponent()).isImmune())
            birdImage.setAlpha(0.7f);
        else
            birdImage.setAlpha(1);

        getCanvas().drawRotatedImage(birdImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) BIRD_WIDTH,
                (float) BIRD_HEIGHT,
                angle);
    }

}

