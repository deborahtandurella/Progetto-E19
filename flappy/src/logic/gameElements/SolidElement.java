package logic.gameElements;

public interface SolidElement {
    boolean collide(SolidElement other);
    Hitbox getHitbox();
}
