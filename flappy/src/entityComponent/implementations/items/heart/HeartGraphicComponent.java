package entityComponent.implementations.items.heart;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import resources.PathHandler;
import resources.ResourcePacks;
import resources.Resources;

import static logic.gameConstants.GameConstants.HEART_SIZE;

public class HeartGraphicComponent extends GameElementGraphicComponent {
    private Animation heartAnimation;

    public HeartGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            SpriteSheet heartSheet = new SpriteSheet(PathHandler.getInstance().getPath(ResourcePacks.SPRITES, Resources.HEART), 16, 16);
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
