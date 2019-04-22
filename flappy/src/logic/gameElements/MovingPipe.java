package logic.gameElements;

import java.util.Random;

public class MovingPipe extends Pipe {

    private double speedY;
    private double centerY;
    private boolean upwards;
    private Random random;

    public MovingPipe(double x, double centerY, double speedX,double speedY) {
        super(x, centerY, speedX);
        this.speedY = speedY;
        random = new Random();
        upwards = random.nextBoolean();
    }

    public void update(int delta) {
        setX(getX() - getSpeedX()*delta);
        if(upwards){
            setY(getY() - speedY*delta);
            if(getY()<0.25){
                upwards = false;
            }
        }
        if(!upwards){
            setY(getY() + speedY*delta);
            if(getY()>0.75){
                upwards = true;
            }
        }
    }


}
