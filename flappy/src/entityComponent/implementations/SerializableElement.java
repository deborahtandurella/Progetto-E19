package entityComponent.implementations;

import entityComponent.Entity;
import graphics.Canvas;

import java.io.Serializable;

public interface SerializableElement extends Serializable {
    Entity instantiate(Canvas canvas);
}
