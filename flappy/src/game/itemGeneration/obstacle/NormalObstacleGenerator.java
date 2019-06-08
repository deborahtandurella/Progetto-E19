package game.itemGeneration.obstacle;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import graphics.Canvas;


import java.util.Random;

public class NormalObstacleGenerator extends ObstacleGenerator {
    protected NormalObstacleGenerator(Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void generatePeriodicObstacle() {
        Entity obstacle;
        if ( (new Random()).nextInt(5) > 2 )
            obstacle = EntityFactory.makeNormalPipe(1,0.25 + (new Random()).nextFloat() * 0.5, getCanvas());
        else
            obstacle = EntityFactory.makeMovingPipe(1,0.25 + (new Random()).nextFloat() * 0.5, getCanvas());
        notifyListeners(obstacle);
    }
}
