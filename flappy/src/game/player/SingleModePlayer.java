package game.player;

/**
 *  Giogatore del SinglePlayer
 */
public class SingleModePlayer implements Player {
    /**
     * Le vite del giocatore
     */
    private int hearts;
    /**
     * Il punteggio del giocatore
     */
    private int score;
    /**
     * Le informazioni non mutevoli sul giocatore
     */
    private PlayerInfo playerInfo;
    public SingleModePlayer(PlayerInfo playerInfo) {
        this.hearts = 3;
        this.playerInfo=playerInfo;
    }

    public void addScore(){
        score++;
    }

    public int getScore(){
        return score;
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void loseHeart() {
        this.hearts--;
    }

    public void addHeart() {
        if(hearts<5){
            this.hearts++;
        }
    }

    public int getHearts(){
        return hearts;
    }

}
