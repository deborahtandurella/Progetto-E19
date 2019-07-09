package entityComponent.implementations.obstacles.pipes.movingPipe;


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
        double myX= getX();
        double myY= getY();
        double mySpeedY = getSpeedY();
        return new SerializableElement() {
            private double x = myX;
            private double y = myY;
            private double speedY=mySpeedY;
            @Override
            public Entity instantiate(Canvas canvas) {
                return EntityFactory.makeMovingPipe(x, y, speedY, canvas);
            }
        };
    }
}
