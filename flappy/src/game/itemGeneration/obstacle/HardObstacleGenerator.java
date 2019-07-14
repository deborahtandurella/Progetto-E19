package game.itemGeneration.obstacle;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import graphics.Canvas;

import java.util.Random;

public class HardObstacleGenerator extends NormalObstacleGenerator {
    private int rocketTime=0;
    private static final int ROCKET_PERIOD = (int) PIPE_PERIOD/2;

    HardObstacleGenerator(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        rocketTime+=delta;
        if ( rocketTime > ROCKET_PERIOD){
            rocketTime=0;
            if ( (new Random()).nextInt(15) == 0)
                generateRocket();
        }
    }
    private void generateRocket(){
        Entity obstacle = EntityFactory.makeRocket(1, 0.25 + (new Random()).nextFloat() * 0.5, getCanvas());
        notifyListeners(obstacle);
    }
}
