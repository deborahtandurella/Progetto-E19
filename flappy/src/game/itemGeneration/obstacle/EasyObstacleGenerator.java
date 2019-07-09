package game.itemGeneration.obstacle;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import graphics.Canvas;

import java.util.Random;

public class EasyObstacleGenerator extends ObstacleGenerator{

    EasyObstacleGenerator(Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void generatePeriodicObstacle() {
        Entity obstacle = EntityFactory.makeNormalPipe(1.5,0.25 + (new Random()).nextFloat() * 0.5, getCanvas());
        notifyListeners(obstacle);
    }
}
