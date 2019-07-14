package flappyEntities.logic.obstacles.pipes;


import flappyEntities.logic.SerializableEntity;

public class MovingPipeLogicComponent extends PipeLogicComponent {

    public MovingPipeLogicComponent(double x, double centerY, double speedY) {
        super(x, centerY);
        setSpeedY(speedY);
    }

    @Override
    public void update(double i) {
        super.update(i);
        if (getY()<0.25){
            setSpeedY(Math.abs(getSpeedY()));
        }
        else if (getY()>0.75){
            setSpeedY(-Math.abs(getSpeedY()));
        }


    }
    @Override
    public SerializableEntity getSerializableVersion() {
        return new SerializableMovingPipe(getX(), getY(), getSpeedY());
    }
}
