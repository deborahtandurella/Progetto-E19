package logic.SinglePlayer;

public class Player {

    private int hearts;
    public Player() {
        this.hearts = 3;
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
