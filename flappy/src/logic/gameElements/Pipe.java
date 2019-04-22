package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

import static logic.gameConstants.GameConstants.PIPE_WIDTH;

public class Pipe extends GameElement implements SolidElement {
    private final double speedX;


    public Pipe(double x, double centerY, double speedX){
        super(x, centerY);
        this.speedX = speedX;
        setX(x);
        setY(centerY);

    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }

}
