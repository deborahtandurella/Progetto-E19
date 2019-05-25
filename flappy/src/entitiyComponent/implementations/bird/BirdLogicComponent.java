package entitiyComponent.implementations.bird;

import entitiyComponent.components.gameElements.SolidGameElementLogicComponent;
import org.newdawn.slick.geom.Ellipse;

import static logic.gameConstants.GameConstants.*;

public class BirdLogicComponent extends SolidGameElementLogicComponent {

    public BirdLogicComponent(double x, double y) {
        super(x, y, 0, 0);
        addHitboxShape(new Ellipse((float)x+(float)BIRD_WIDTH/2f, (float)y+(float)BIRD_HEIGHT/2f, (float)BIRD_WIDTH/2*0.9f, (float)BIRD_HEIGHT/2*0.9f));

    }

    @Override
    public void update(int delta) {
        setSpeedY(getSpeedX() + delta * ACCELERATION_Y);
        setY(getY() + getSpeedY() * delta);
        if (getY() > 1){
            jump();
        }

    }
    public void jump(){
        setSpeedY( - JUMP_SPEED);
    }

}
