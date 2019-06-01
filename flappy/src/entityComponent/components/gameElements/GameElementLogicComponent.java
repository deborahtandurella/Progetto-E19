package entityComponent.components.gameElements;

import entityComponent.components.LogicComponent;

public abstract class GameElementLogicComponent implements LogicComponent {
    private double x;
    private double y;
    private double speedX;
    private double speedY;


    public GameElementLogicComponent(double x, double y, double speedX, double speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public  double getY(){
        return this.y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    @Override
    public void update(int i) {
        x += speedX*i;
        y += speedY*i;
    }
}
