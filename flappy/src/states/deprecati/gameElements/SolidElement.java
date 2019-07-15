package states.deprecati.gameElements;

public interface SolidElement {
    boolean collide(SolidElement other);
    Hitbox getHitbox();
}
