package flappyEntities.logic.bird;

import static game.GameConstants.*;

public class ReversedBirdLogicComponent extends BirdLogicComponent {
    public ReversedBirdLogicComponent(BirdLogicComponent birdLogicComponent) {
        super(birdLogicComponent.getX(), birdLogicComponent.getY());
        if(birdLogicComponent.isImmune())
            acquireImmunity();
        setSpeedY(birdLogicComponent.getSpeedY());
    }
    @Override
    protected void checkForBounce(){
        if (getY() < 0){
            jump();
        }
    }
    @Override
    protected void updateSpeed(double delta){
        setSpeedY(getSpeedY() + delta * -ACCELERATION_Y);
    }

    @Override
    public boolean outOfBounds(){
        return getY() + BIRD_HEIGHT > 1;
    }
    @Override
    public void jump(){
        setSpeedY( JUMP_SPEED);
    }
}