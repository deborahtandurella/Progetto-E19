package flappyEntities.logic;

import flappyEntities.Entity;
import graphics.Canvas;

import java.io.Serializable;

/**
 *  Versione serializzabile di un'entità
 */
public interface SerializableEntity extends Serializable {
    long serialVersionUID = -539210512250000000L;

    Entity instantiate(Canvas canvas);
}
