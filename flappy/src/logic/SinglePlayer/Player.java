package logic.SinglePlayer;

public class Player {

    private int hearts;
    private int score;

    public Player() {
        this.hearts = 3;
        this.score = 0;
    }

    public void addScore(){
        score++;
    }

    public int getScore(){
        return score;
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
