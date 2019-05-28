package entitiyComponent.implementations.obstacles.pipes.movingPipe;


import entitiyComponent.implementations.obstacles.pipes.PipeLogicComponent;

import java.util.Random;

public class MovingPipeLogicComponent extends PipeLogicComponent {

    public MovingPipeLogicComponent(double x, double centerY, double speedX) {
        super(x, centerY, speedX);
        Random random = new Random();
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
