package scoreboard;

import game.player.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoard {

  private ArrayList<Result> results;
  private ScoreboardDAO scoreboardDAO;
  private static final int N_PLAYERS = 10;
  private boolean newRecord;


  public ScoreBoard() throws IOException {
      this.scoreboardDAO = CSVScoreboardDAO.getInstance();
      results= scoreboardDAO.readScoreBoard();
      newRecord = false;
  }

  public int getCurrentRecord(){
    return results.get(0).getScore();
  }

  public void compareScore(Result newResult)  {
    results.add(newResult);
    Collections.sort(results);
    if (results.indexOf(newResult)<N_PLAYERS){
      results.remove(results.size()-1);
      newRecord();
    }
  }

  private void newRecord(){
    newRecord=true;
    try {
      scoreboardDAO.writePlayers(results);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public boolean getnewRecord(){
    return newRecord;
  }


    public String printName(){
    StringBuilder sb = new StringBuilder();
    for (Result result : results){
      sb.append(result.getName()).append("\n");
    }
    return sb.toString();
  }

  public String printScores(){
    StringBuilder s = new StringBuilder();
    for (Result result : results){
      s.append(result.getScore()).append("\n");
    }
    return s.toString();
  }

  public void setNewRecord(){
    newRecord = false;
  }

  public void deleteScoreBoard() {
    for (Result result : results){
      result.setName("-------------");
      result.setScore(0);
    }
    try {
      scoreboardDAO.writePlayers(results);
    } catch (DatabaseException e){
      e.printStackTrace();
    }
  }
}
