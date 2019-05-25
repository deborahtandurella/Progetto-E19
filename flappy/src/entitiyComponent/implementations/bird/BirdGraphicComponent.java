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
    private BirdLogicComponent logicComponent;

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
        birdImage.setRotation( (float) 180*( (float) Math.atan2(logicComponent.getSpeedY(), JUMP_SPEED))/((float)Math.PI)) ;
        birdImage.setCenterOfRotation( (float)(BIRD_WIDTH*getScreen().getWidth())/2f,  (float)(BIRD_HEIGHT*getScreen().getHeight())/2f);
        birdImage.draw( (float) (getScreen().getOffsetX()+ logicComponent.getX()*getScreen().getWidth()),
                (float) logicComponent.getY()*getScreen().getHeight()+ getScreen().getOffsetY(),
                (float) BIRD_WIDTH*getScreen().getWidth(),
                (float) BIRD_HEIGHT*getScreen().getHeight()+ getScreen().getOffsetY());
    }
}

