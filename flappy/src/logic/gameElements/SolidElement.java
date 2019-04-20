package logic.gameElements;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public interface SolidElement {
    boolean collide(Hitbox otherHitbox);
}
