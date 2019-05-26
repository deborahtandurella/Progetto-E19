package entitiyComponent.implementations.bird;

import entitiyComponent.components.gameElements.GameElementGraphicComponent;
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
        birdImage.setRotation( (float) 180*( (float) Math.atan2(getLogic().getSpeedY(), JUMP_SPEED))/((float)Math.PI)) ;
        birdImage.setCenterOfRotation( (float)(BIRD_WIDTH*getScreen().getWidth())/2f,  (float)(BIRD_HEIGHT*getScreen().getHeight())/2f);
        birdImage.draw( (float) (getScreen().getOffsetX()+ getLogic().getX()*getScreen().getWidth()),
                (float) getLogic().getY()*getScreen().getHeight()+ getScreen().getOffsetY(),
                (float) BIRD_WIDTH*getScreen().getWidth(),
                (float) BIRD_HEIGHT*getScreen().getHeight()+ getScreen().getOffsetY());
    }
    private BirdLogicComponent getLogic(){
        return (BirdLogicComponent) getLogicComponent();
    }
}

