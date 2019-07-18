package game.gameEvents;

public interface GameEventListener {
    /**
     * Segnalazione di un GameEvent
     * @param event evento segnalato
     */
    void gameEvent(GameEventType event);
}
