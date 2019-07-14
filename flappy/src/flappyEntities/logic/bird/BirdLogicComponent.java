package flappyEntities.logic.bird;

import flappyEntities.logic.SolidGameElementLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.*;

public class BirdLogicComponent extends SolidGameElementLogicComponent {
    private boolean immunity;
    private int immunityTime;
    public BirdLogicComponent(double x, double y) {
        super(x, y, 0, 0);
        addHitboxShape(new Ellipse((float)x+(float)BIRD_WIDTH/2f, (float)y+(float)BIRD_HEIGHT/2f, (float)BIRD_WIDTH/2*0.9f, (float)BIRD_HEIGHT/2*0.9f));

    }

    public BirdLogicComponent(BirdLogicComponent logic) {
        this(logic.getX(), logic.getY());
        setSpeedX(logic.getSpeedX());
        if(logic.isImmune())
            acquireImmunity();
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        updateImmunity(delta);
        updateSpeed(delta);
        checkForBounce(delta);
    }
    protected void checkForBounce(int delta){
        if (getY() > 1){
            jump();
        }
    }
    protected void updateSpeed(int delta){
        setSpeedY(getSpeedY() + delta * ACCELERATION_Y);
    }
    private void updateImmunity(int delta){
        if(immunity){
            immunityTime-=delta;
            if (immunityTime<0)
                immunity=false;
        }
    }
    public boolean outOfBounds(){
        return getY() + BIRD_HEIGHT < 0;
    }
    public boolean isImmune() {
        return immunity;
    }

    public void acquireImmunity() {
        immunity=true;
        immunityTime = 2000;
    }

    public void jump(){
        setSpeedY( - JUMP_SPEED);
    }

}
