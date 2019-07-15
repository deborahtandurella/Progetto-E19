package flappyEntities.logic.obstacles;

import flappyEntities.logic.HasSerializableVersion;
import flappyEntities.logic.ScrollingElement;
import flappyEntities.logic.SolidGameElementLogicComponent;

/**
 *  Componente logica degli ostacoli
 */
public abstract class ObstacleLogicComponent extends SolidGameElementLogicComponent implements ScrollingElement, HasSerializableVersion {
    private boolean passed;
    public ObstacleLogicComponent(double x, double y, double speedX, double speedY) {
        super(x, y, speedX, speedY);
        passed = false;
    }
    abstract public boolean outOfBounds();

    /**
     * @return true se l'ostacolo deve essere distrutto in seguito a una collisione
     */
    abstract public boolean destroyOnHit();

    /**
     * @return true se l'ostacolo è di tipo periodico
     */
    abstract public boolean isPeriodic();

    /**
     * @return true se l'ostacolo è stato superato
     */
    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
