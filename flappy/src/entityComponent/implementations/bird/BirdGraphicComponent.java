package entityComponent.implementations.bird;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import static logic.gameConstants.GameConstants.*;

public class BirdGraphicComponent extends GameElementGraphicComponent
{
    private Image birdImage;
    private boolean flipped;

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
            birdImage.setImageColor(1f, 0.f , 0.2f);
        else
            birdImage.setImageColor(255, 255, 255);

        getCanvas().drawRotatedImage(birdImage,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) BIRD_WIDTH,
                (float) BIRD_HEIGHT,
                angle);
    }
    public boolean isFlipped(){
        return flipped;
    }
    public void flip(){
        birdImage=birdImage.getFlippedCopy(false, flipped);
    }

}

