package game.itemGeneration.obstacle;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import graphics.Canvas;

import java.util.Random;

public class EasyObstacleGenerator extends ObstacleGenerator{

    EasyObstacleGenerator(Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void generatePeriodicObstacle() {
        Entity obstacle = EntityFactory.makeNormalPipe(1,0.25 + (new Random()).nextFloat() * 0.5, getCanvas());
        notifyListeners(obstacle);
    }
}
