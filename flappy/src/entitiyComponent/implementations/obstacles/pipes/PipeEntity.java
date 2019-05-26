package entitiyComponent.implementations.obstacles.pipes;

import entitiyComponent.Entity;
import entitiyComponent.GameElementEntity;
import entitiyComponent.components.gameElements.GameElementLogicComponent;
import entitiyComponent.implementations.bird.BirdGraphicComponent;
import entitiyComponent.implementations.bird.BirdLogicComponent;
import graphics.Screen;
import org.newdawn.slick.Graphics;

public class PipeEntity extends GameElementEntity {
    public PipeEntity(Graphics graphics, Screen screen, double x, double centerY, double speedX) {
        super(new PipeLogicComponent(x, centerY, speedX), new PipeGraphicComponent(graphics, screen));
    }
}
