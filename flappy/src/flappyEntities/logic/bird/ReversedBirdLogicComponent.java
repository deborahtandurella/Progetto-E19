package flappyEntities.logic.bird;

import static game.GameConstants.*;

/**
 *  Componente logica dell'uccello a gravità invertita
 */
public class ReversedBirdLogicComponent extends BirdLogicComponent {
    /**
     * Crea una ReverseBirdLogicComponent a partire da una BirdLogicComponent imitandone le caratteristiche
     * @param birdLogicComponent: la componente logica di partenza
     */
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
        return getY()  > 1;
    }
    @Override
    public void jump(){
        setSpeedY( JUMP_SPEED);
    }
}