package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Pipe extends GameElement implements SolidElement {
    private final double speedX;
    public static final double FREE_SPACE = 0.35;
    public static final double WIDTH_ = 1/6;
    public static final double WIDTH_HEIGHT_PROPORTION= 320/63;

    public Pipe(double x, double y, double speedX){
        super(x, y);
        this.speedX = speedX;
    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }

}
