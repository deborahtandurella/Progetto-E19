package entityComponent.implementations;

import entityComponent.Entity;
import graphics.Canvas;

import java.io.Serializable;

public interface SerializableElement extends Serializable {
    long serialVersionUID = -5392105122490345559L;

    Entity instantiate(Canvas canvas);
}
