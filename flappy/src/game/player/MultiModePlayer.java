package game.player;

/**
 *  Giocatore del Multiplayer
 */
public class MultiModePlayer implements  Player{
    /**
     * Le monete del giocatore
     */
    private int coins;
    /**
     * Il punteggio del giocatore
     */
    private int score;
    private PlayerInfo playerInfo;

    /**
     * @param playerInfo le informazioni non mutevoli sul giocatore
     */
    public MultiModePlayer(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        score= 0;
        coins= 0;
    }
    public void addCoin(){
        coins++;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    @Override
    public void addScore(){
        score++;
    }
    public int getScore() {
        return score;
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
