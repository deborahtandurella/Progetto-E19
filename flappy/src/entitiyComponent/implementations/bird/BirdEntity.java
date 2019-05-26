package entitiyComponent.implementations.bird;

import entitiyComponent.Entity;
import entitiyComponent.GameElementEntity;
import entitiyComponent.components.gameElements.GameElementLogicComponent;
import graphics.Screen;
import org.newdawn.slick.Graphics;

public class BirdEntity extends GameElementEntity {
    public BirdEntity(Graphics graphics, Screen screen) {
        super(new BirdLogicComponent(0.2, 0.5), new BirdGraphicComponent(graphics, screen));
    }
}
