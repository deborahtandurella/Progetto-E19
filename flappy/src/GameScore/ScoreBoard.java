package GameScore;

import logic.SinglePlayer.Result;

import java.io.IOException;

public class ScoreBoard {

  private Result[] results;
  private ScoreFacade scoreFacade;
  private static final int N_PLAYERS = 10;
  private boolean newRecord;
  private int currentRecord;


  public ScoreBoard() throws IOException {
      this.results = new Result[N_PLAYERS];
      this.scoreFacade = new ScoreFacade();
      scoreFacade.readScoreBoard(results, N_PLAYERS);
      newRecord = false;
      currentRecord = results[0].getScore();
  }

  public int getCurrentRecord(){
    return currentRecord;
  }

  public boolean compareScore(Result p)  {
    for(int i=0; i<N_PLAYERS; i++){
      if( p.getScore() > results[i].getScore()){
        shiftPlayers(i);
        results[i].setName(p.getName());
        results[i].setScore(p.getScore());
        newRecord();
        return newRecord = true;
      }
    }
    return newRecord = false;
  }
  private void newRecord(){
    try {
      scoreFacade.writePlayers(results, N_PLAYERS);
    } catch (IOException e) {
      e.printStackTrace();
    }
    currentRecord = results[0].getScore();
  };


  public boolean getnewRecord(){
    return newRecord;
  }
  private void shiftPlayers(int i){
    for(int j=N_PLAYERS-1; j>i; j--){
      results[j].setScore(results[j-1].getScore());
      results[j].setName(results[j-1].getName());
    }
  }


    public String printName(){
    String s = "";
    for (int i=0; i<N_PLAYERS;i++){
      s += results[i].getName() +"\n";
    } return s;
  }

  public String printPoint(){
      String s = "";
      for (int i=0; i<N_PLAYERS;i++){
          s += results[i].getScore()+"\n";
      } return s;
  }

  public void setNewRecord(){
    newRecord = false;
  }

  public void deleteScoreBoard() throws IOException {
    currentRecord = 0;

    for(int j=0; j<N_PLAYERS; j++){
      results[j].setScore(0);
      results[j].setName("-------------");
    }
    scoreFacade.writePlayers(results, N_PLAYERS);
  }

}
