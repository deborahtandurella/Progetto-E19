package game.player;

/**
 *  Giogatore del SinglePlayer
 */
public class SingleModePlayer implements Player {
    private static final int MAX_HEARTS = 5;
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
    @Override
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
        if(hearts< MAX_HEARTS){
            this.hearts++;
        }
    }

    public int getHearts(){
        return hearts;
    }

}
