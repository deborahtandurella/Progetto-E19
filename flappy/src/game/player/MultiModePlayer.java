package game.player;

public class MultiModePlayer implements  Player{
    /**
     * Le monete del giocatore
     */
    private int coins;
    /**
     * Il punteggio del giocatore
     */
    private int score;
    /**
     * Le informazioni non mutevoli sul giocatore
     */
    private PlayerInfo playerInfo;
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

    public void addScore(){
        score++;
    }
    public int getScore() {
        return score;
    }

    public int getCoins() {
        return coins;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
