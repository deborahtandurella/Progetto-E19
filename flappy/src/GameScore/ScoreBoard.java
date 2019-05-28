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

  public void compareScore(Record p) throws IOException {

    for(int i=0; i<nPlayers; i++){
      if( p.getScore() > records[i].getScore()){
        shiftPlayers(i);
        Scanner s = new Scanner(System.in);
        p.setName("qwertyuiop");
        records[i].setName(p.getName());
        records[i].setScore(p.getScore());
        scoreFacade.writePlayers(records, nPlayers);
        break;
      }
    }  }

  private void shiftPlayers(int i){

    for(int j=nPlayers-1; j>i; j--){
      records[j].setScore(records[j-1].getScore());
      records[j].setName(records[j-1].getName());
    }

  }


}
