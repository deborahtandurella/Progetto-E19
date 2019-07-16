package game.player;

/**
 *  Informazioni non mutevoli sul giocatore
 */
public class PlayerInfo {
    private String name;

    public PlayerInfo(String name) {
        this.name = name;
    }

    /**
     * @return il nome del giocatore
     */
    public String getName() {
        return name;
    }
}