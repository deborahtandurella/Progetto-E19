package game.player;

/**
 *  Informazioni non mutevoli sul giocatore
 */
public class PlayerInfo {
    private String name;

    public PlayerInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}