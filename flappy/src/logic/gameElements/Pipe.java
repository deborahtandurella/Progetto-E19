package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Pipe extends GameElement implements SolidElement {
    private final double speedX;
    private static final double FREE_SPACE = 0.35f;
    public static final double WIDTH_ = 1f/6;
    private static final double WIDTH_HEIGHT_PROPORTION= 320f/63f;

    public Pipe(double x, double y, double speedX){
        super(x, y);
        this.speedX = speedX;
    }

    @Override
    public void update(int delta) {
        setX(getX() - speedX*delta);
    }

}
