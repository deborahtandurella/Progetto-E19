package flappyEntities.logic;


public interface ScrollingElement extends LogicComponent {
    /**
     * @return se l'entità è considerata fuori dai confini di gioco
     */
    boolean outOfBounds();
}
