package GameScore;

import jdk.internal.util.xml.impl.Input;
import logic.SinglePlayer.Player;
import logic.SinglePlayer.Record;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ScoreBoard {

  private Record[] records;
  private ScoreFacade scoreFacade;
  private final int nPlayers = 10;

  public ScoreBoard() throws IOException {
      this.records = new Record[nPlayers];
      this.scoreFacade = new ScoreFacade();
      scoreFacade.readScoreBoard(records, nPlayers);
  }

  public boolean compareScore(Record p) throws IOException {

    for(int i=0; i<nPlayers; i++){
      if( p.getScore() > records[i].getScore()){
        shiftPlayers(i);
        records[i].setName(p.getName());
        records[i].setScore(p.getScore());
        scoreFacade.writePlayers(records, nPlayers);
        return true;
      }
    }
    return false;
  }

  private void shiftPlayers(int i){

    for(int j=nPlayers-1; j>i; j--){
      records[j].setScore(records[j-1].getScore());
      records[j].setName(records[j-1].getName());
    }

  }

    public Record[] getRecords() {
        return records;
    }

    public String printScore(){

    String s = "";
    for (int i=0; i<nPlayers;i++){
      s += i+1 +")  "+ records[i].getName() + "   " + records[i].getScore()+"\n";
    } return s;
  }


}
