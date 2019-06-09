package entityComponent.implementations.obstacles.pipes.movingPipe;


import entityComponent.implementations.obstacles.pipes.PipeLogicComponent;
import logic.gameConstants.GameConstants;

import java.util.Random;

public class MovingPipeLogicComponent extends PipeLogicComponent {

    public MovingPipeLogicComponent(double x, double centerY) {
        super(x, centerY);
        Random random = new Random();
        setSpeedY(GameConstants.PIPE_VERTICAL_SPEED);
        if(random.nextBoolean()){
            setSpeedY(-getSpeedY());
        }
    }

    @Override
    public void update(int i) {
        super.update(i);
        if (getY()<0.25){
            setSpeedY(Math.abs(getSpeedY()));
        }
        else if (getY()>0.75){
            setSpeedY(-Math.abs(getSpeedY()));
        }


    }
}
