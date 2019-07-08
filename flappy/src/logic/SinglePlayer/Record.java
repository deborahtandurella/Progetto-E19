package logic.SinglePlayer;

import logic.player.Player;

public class Record {

    private String playerName;
    private int score;

    public void setRecord(Player player){
        this.score = player.getScore();
    }

    public void setName(String name){
        this.playerName = name;
    }

    public String getName(){
        return playerName;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }
}
