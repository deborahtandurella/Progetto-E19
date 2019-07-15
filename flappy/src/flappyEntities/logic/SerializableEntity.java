package flappyEntities.logic;

import flappyEntities.Entity;
import graphics.Canvas;

import java.io.Serializable;

/**
 *  Versione serializzabile di un'entità
 */
public interface SerializableEntity extends Serializable {
    long serialVersionUID = -539210512250000000L;

    /**
     * Genera l'entità corrispondente
     * @param canvas il Canvas da utilizzare per la parte grafica dell'entità
     * @return
     */
    Entity instantiate(Canvas canvas);
}
