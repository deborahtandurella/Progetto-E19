package entitiyComponent.implementations.obstacles.pipes;

import entitiyComponent.components.gameElements.GameElementGraphicComponent;
import entitiyComponent.implementations.bird.BirdLogicComponent;
import graphics.Screen;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.PathKeys;

import java.io.IOException;

import static logic.gameConstants.GameConstants.PIPE_FREE_SPACE;
import static logic.gameConstants.GameConstants.PIPE_HEIGHT;
import static logic.gameConstants.GameConstants.PIPE_WIDTH;

public class PipeGraphicComponent extends GameElementGraphicComponent {
    private Image upperPipeImage;
    private Image lowerPipeImage;
    public PipeGraphicComponent(Graphics graphics, Screen screen) {
        super(graphics, screen);
        try{
            lowerPipeImage = new Image(PathHandler.getInstance().getPath(PathKeys.BIRD));
            upperPipeImage= lowerPipeImage.getFlippedCopy(false, true);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void render() {
        getScreen().drawImage(upperPipeImage,
                (float) getLogicComponent().getX(),
                (float) (getLogicComponent().getY() - PIPE_HEIGHT - 0.5*PIPE_FREE_SPACE),
                (float) PIPE_WIDTH,
                (float) PIPE_HEIGHT);
        getScreen().drawImage(lowerPipeImage,
                (float) getLogicComponent().getX(),
                (float) (getLogicComponent().getY() + 0.5*PIPE_FREE_SPACE),
                (float) PIPE_WIDTH,
                (float) PIPE_HEIGHT);

    }


}
