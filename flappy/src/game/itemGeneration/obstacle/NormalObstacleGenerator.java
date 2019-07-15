package game.itemGeneration.obstacle;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import game.GameConstants;
import graphics.Canvas;

import java.util.Random;

public class NormalObstacleGenerator extends ObstacleGenerator {


    NormalObstacleGenerator(Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void generatePeriodicObstacle() {
        Entity obstacle;
        if ( (new Random()).nextInt(5) > 2 )
            obstacle = EntityFactory.makeNormalPipe(1,0.25 + (new Random()).nextFloat() * 0.5, getCanvas());
        else {
            double speedY = GameConstants.PIPE_VERTICAL_SPEED;
            if(new Random().nextBoolean()){
                speedY = -speedY;
            }
            obstacle = EntityFactory.makeMovingPipe(1,0.25 + (new Random()).nextFloat() * 0.5, speedY, getCanvas());
        }
        notifyListeners(obstacle);
    }
}
