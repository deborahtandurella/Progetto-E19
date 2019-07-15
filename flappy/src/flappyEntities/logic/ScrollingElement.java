package flappyEntities.logic;


public interface ScrollingElement extends LogicComponent {
    /**
     * @return true se l'entità è considerata fuori dai confini di gioco
     */
    boolean outOfBounds();
}
