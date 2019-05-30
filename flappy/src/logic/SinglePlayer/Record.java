package logic.SinglePlayer;

public class Record {

    private String playerName;
    private int score;
    private boolean firstLogin = true;

    public void setRecord(Player player){
        this.score = player.getScore();
    }

    public void setLogin(boolean bool){
        firstLogin = bool;
    }

    public boolean getLogin(){
        return firstLogin;
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
