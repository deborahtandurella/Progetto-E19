package game.player;

public class SingleModePlayer implements Player {
    private int hearts;
    private int score;
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
