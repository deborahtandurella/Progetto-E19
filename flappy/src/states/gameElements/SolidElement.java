package states.gameElements;

public interface SolidElement {
    boolean collide(SolidElement other);
    Hitbox getHitbox();
}
