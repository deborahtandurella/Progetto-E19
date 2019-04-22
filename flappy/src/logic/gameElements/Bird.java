package logic.gameElements;

import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.*;


public class Bird extends GameElement implements SolidElement{
    private double speedY;

    public Bird(double x, double y){
        super(x, y);
        addHitboxShape(new Ellipse((float)x+(float)BIRD_WIDTH/2f, (float)y+(float)BIRD_HEIGHT/2f, (float)BIRD_WIDTH/2*0.9f, (float)BIRD_HEIGHT/2*0.9f));
    }

    @Override
    public void update(int delta) {
        speedY+=delta*ACCELERATION_Y;
        shiftHitbox(0, speedY*delta);
        setY(getY()+speedY*delta);
        if (getY()>1){
            jump();
        }

    }
    public void jump(){
        speedY = - JUMP_SPEED;

    }

}
