package entityComponent.implementations.obstacles.pipes;

import entityComponent.components.gameElements.GameElementGraphicComponent;
import graphics.Canvas;
import org.newdawn.slick.Image;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import static logic.gameConstants.GameConstants.*;

public class PipeGraphicComponent extends GameElementGraphicComponent {
    private Image upperPipeImage;
    private Image lowerPipeImage;
    public PipeGraphicComponent(Canvas canvas) {
        super(canvas);
        try{
            lowerPipeImage = new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.PIPE));
            upperPipeImage= lowerPipeImage.getFlippedCopy(false, true);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void render() {
        getCanvas().drawImage(upperPipeImage,
                (float) getLogicComponent().getX(),
                (float) (getLogicComponent().getY() - PIPE_HEIGHT - 0.5*PIPE_FREE_SPACE),
                (float) PIPE_WIDTH,
                (float) PIPE_HEIGHT);
        getCanvas().drawImage(lowerPipeImage,
                (float) getLogicComponent().getX(),
                (float) (getLogicComponent().getY() + 0.5*PIPE_FREE_SPACE),
                (float) PIPE_WIDTH,
                (float) PIPE_HEIGHT);

    }


}
