package entityComponent.implementations.obstacles.pipes;


import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.SerializableElement;
import entityComponent.implementations.obstacles.pipes.PipeLogicComponent;
import graphics.Canvas;

public class MovingPipeLogicComponent extends PipeLogicComponent {

    public MovingPipeLogicComponent(double x, double centerY, double speedY) {
        super(x, centerY);
        setSpeedY(speedY);
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
    @Override
    public SerializableElement getTransmittableVersion() {
        return new SerializableMovingPipeLogic(getX(), getY(), getSpeedY());
    }
}
