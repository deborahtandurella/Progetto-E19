package GameScore;

import logic.SinglePlayer.Record;
import java.io.*;
import java.util.Collections;

public class ScoreBoard {

  private Record[] records;
  private ScoreFacade scoreFacade;
  private static final int N_PLAYERS = 10;
  private boolean newRecord;
  private int currentRecord;


  public ScoreBoard() throws IOException {
      this.records = new Record[N_PLAYERS];
      this.scoreFacade = new ScoreFacade();
      scoreFacade.readScoreBoard(records, N_PLAYERS);
      newRecord = false;
      currentRecord = records[0].getScore();
  }

  public int getCurrentRecord(){
    return currentRecord;
  }

  public boolean compareScore(Record p) throws IOException {
    for(int i=0; i<N_PLAYERS; i++){
      if( p.getScore() > records[i].getScore()){
        shiftPlayers(i);
        records[i].setName(p.getName());
        records[i].setScore(p.getScore());
        scoreFacade.writePlayers(records, N_PLAYERS);
        currentRecord = records[0].getScore();
        return newRecord = true;
      }
    }
    return newRecord = false;
  }



  public boolean getnewRecord(){
    return newRecord;
  }
  private void shiftPlayers(int i){
    for(int j=N_PLAYERS-1; j>i; j--){
      records[j].setScore(records[j-1].getScore());
      records[j].setName(records[j-1].getName());
    }
  }


    public String printName(){
    String s = "";
    for (int i=0; i<N_PLAYERS;i++){
      s += records[i].getName() +"\n";
    } return s;
  }

  public String printPoint(){
      String s = "";
      for (int i=0; i<N_PLAYERS;i++){
          s += records[i].getScore()+"\n";
      } return s;
  }

  public void setNewRecord(){
    newRecord = false;
  }

  public void deleteScoreBoard() throws IOException {
    currentRecord = 0;

    for(int j=0; j<N_PLAYERS; j++){
      records[j].setScore(0);
      records[j].setName("-------------");
    }
    scoreFacade.writePlayers(records, N_PLAYERS);
  }

}
