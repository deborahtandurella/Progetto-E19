package entityComponent.implementations;

import entityComponent.Entity;
import graphics.Canvas;

import java.io.Serializable;

public interface SerializableElement extends Serializable {
    long serialVersionUID = -539210512250000000L;

    Entity instantiate(Canvas canvas);
}
