package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Bird extends GameElement implements SolidElement{
    private double speedY;
    private static double ACCELERATION_Y;
    private static double JUMP_SPEED;
    private static final double SIZE = 0.15f;

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
