package flappyEntities.graphic;

import graphics.Canvas;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import static game.GameConstants.HEART_SIZE;

/**
 *  Componente grafica del cuore
 */
public class HeartGraphicComponent extends GameElementGraphicComponent {
    private Animation heartAnimation;

    public HeartGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            SpriteSheet heartSheet = new SpriteSheet(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.HEART), 16, 16);
            heartAnimation = new Animation(heartSheet, 200);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        getCanvas().drawAnimation(heartAnimation,
                (float) getLogicComponent().getX(),
                (float) getLogicComponent().getY(),
                (float) HEART_SIZE,
                (float) HEART_SIZE);
    }
}
