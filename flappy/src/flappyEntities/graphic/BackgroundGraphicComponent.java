package flappyEntities.graphic;

import graphics.Canvas;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

public class BackgroundGraphicComponent extends GameElementGraphicComponent {

    private Image background;

    public BackgroundGraphicComponent(Canvas canvas) {
        super(canvas);
        try {
            background = new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.BACKGROUND));
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        getCanvas().drawImage(background, 0, 0, 1, 1);
    }
}
