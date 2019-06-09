package entityComponent.implementations.items.heart;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import graphics.Screen;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import static logic.gameConstants.GameConstants.HEART_SIZE;

public class HeartGraphicComponent extends GameElementGraphicComponent {
    private Animation heartAnimation;

    public HeartGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            SpriteSheet heartSheet = new SpriteSheet(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.HEART), 16, 16);
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
