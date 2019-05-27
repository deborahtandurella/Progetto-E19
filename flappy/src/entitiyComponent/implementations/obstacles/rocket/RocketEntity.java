package entitiyComponent.implementations.obstacles.rocket;

import entitiyComponent.GameElementEntity;

import graphics.Screen;
import org.newdawn.slick.Graphics;


public class RocketEntity extends GameElementEntity {
    public RocketEntity(Graphics graphics, Screen screen, double x, double y, double speedX) {
        super(new RocketLogicComponent(x, y, speedX), new RocketGraphicComponent(graphics, screen));
    }
}


