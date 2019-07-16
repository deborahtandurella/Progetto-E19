package game.player;

/**
 *  Risultato di una partita
 */
public class Result implements Comparable<Result>{

    /**
     * Nome del giocatore
     */
    private String playerName;
    /**
     * Punteggio
     */
    private int score;

    public Result(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public Result(Player player) {
        playerName = player.getPlayerInfo().getName();
        score = player.getScore();
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

    @Override
    public int compareTo(Result result) {
        return result.getScore()-this.score;
    }
}
