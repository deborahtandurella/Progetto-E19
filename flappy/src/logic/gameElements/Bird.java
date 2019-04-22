package logic.gameElements;

import static logic.gameConstants.GameConstants.ACCELERATION_Y;
import static logic.gameConstants.GameConstants.JUMP_SPEED;


public class Bird extends GameElement implements SolidElement{
    private double speedY;

    public Bird(double x, double y){
        super(x, y);
    }

    @Override
    public void update(int delta) {
        speedY+=delta*ACCELERATION_Y;
        setY(getY()+speedY*delta);
        if (getY()>1){
            jump();
        }

    }
    public void jump(){
        speedY = - JUMP_SPEED;

    }

}
