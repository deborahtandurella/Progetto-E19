package GameScore;

import logic.SinglePlayer.Record;
import java.io.*;

public class ScoreBoard {

  private Record[] records;
  private ScoreFacade scoreFacade;
  private static final int N_PLAYERS = 10;

  public ScoreBoard() throws IOException {
      this.records = new Record[N_PLAYERS];
      this.scoreFacade = new ScoreFacade();
      scoreFacade.readScoreBoard(records, N_PLAYERS);
  }

  public boolean compareScore(Record p) throws IOException {
    for(int i=0; i<N_PLAYERS; i++){
      if( p.getScore() > records[i].getScore()){
        shiftPlayers(i);
        records[i].setName(p.getName());
        records[i].setScore(p.getScore());
        scoreFacade.writePlayers(records, N_PLAYERS);
        return true;
      }
    }
    return false;
  }

  private void shiftPlayers(int i){
    for(int j=N_PLAYERS-1; j>i; j--){
      records[j].setScore(records[j-1].getScore());
      records[j].setName(records[j-1].getName());
    }
  }


    public String printScore(){
    String s = "";
    for (int i=0; i<N_PLAYERS;i++){
      s += i+1 +")  "+ records[i].getName() + " " + records[i].getScore()+"\n";
    } return s;
  }


}
