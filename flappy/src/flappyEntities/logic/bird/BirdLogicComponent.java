package flappyEntities.logic.bird;

import flappyEntities.logic.SolidGameElementLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static game.GameConstants.*;

/**
 *  Componente logica del bird
 */
public class BirdLogicComponent extends SolidGameElementLogicComponent {
    private boolean immunity;
    private int immunityTime;
    public BirdLogicComponent(double x, double y) {
        super(x, y, 0, 0);
        addHitboxShape(new Ellipse((float)x+(float)BIRD_WIDTH/2f, (float)y+(float)BIRD_HEIGHT/2f, (float)BIRD_WIDTH/2*0.9f, (float)BIRD_HEIGHT/2*0.9f));

    }

    /**
     *  Crea una BirdLogicComponent a partire da un'altra BirdLogicComponent imitandone le caratteristiche
     * @param logic: la componente logica di partenza
     */
    public BirdLogicComponent(BirdLogicComponent logic) {
        this(logic.getX(), logic.getY());
        setSpeedX(logic.getSpeedX());
        if(logic.isImmune())
            acquireImmunity();
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        updateImmunity(delta);
        updateSpeed(delta);
        checkForBounce();
    }

    /**
     *  Rimbalza se sta toccando terra
     */
    protected void checkForBounce(){
        if (getY() > 1){
            jump();
        }
    }

    /**
     * Modifica la velocità rispettando l'accelerazione
     * @param delta intervallo di tempo trascorso
     */
    protected void updateSpeed(double delta){
        setSpeedY(getSpeedY() + delta * ACCELERATION_Y);
    }

    private void updateImmunity(double delta){
        if(immunity){
            immunityTime-=delta;
            if (immunityTime<0)
                immunity=false;
        }
    }

    /**
     * @return true se il bird è uscito dallo schermo (ha saltato troppo in alto
     */
    public boolean outOfBounds(){
        return getY() + BIRD_HEIGHT < 0;
    }
    public boolean isImmune() {
        return immunity;
    }

    /**
     *  acquisice immunità temporanea
     */
    public void acquireImmunity() {
        immunity=true;
        immunityTime = 2000;
    }

    /**
     *  Esegue un salto / battito di ali
     */
    public void jump(){
        setSpeedY( - JUMP_SPEED);
    }

}
