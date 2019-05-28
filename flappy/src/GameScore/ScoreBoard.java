package GameScore;

import jdk.internal.util.xml.impl.Input;
import logic.SinglePlayer.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ScoreBoard {

  private Player[] players;
  private ScoreFacade scoreFacade;
  private final int nPlayers = 10;

  public ScoreBoard() throws IOException {
      this.players = new Player[nPlayers];
      this.scoreFacade = new ScoreFacade();
      scoreFacade.readScoreBoard(players, nPlayers);
  }

  public void compareScore(Player p) throws IOException {

    for(int i=0; i<nPlayers; i++){
      if( p.getScore() > players[i].getScore()){
        shiftPlayers(i);
        Scanner s = new Scanner(System.in);
        p.setName("io");
        players[i].setName(p.getName());
        players[i].setScore(p.getScore());
        scoreFacade.writePlayers(players, nPlayers);
        break;
      }
    }  }

  public void shiftPlayers(int i){

    for(int j=nPlayers-1; j>i; j--){
      players[j].setScore(players[j-1].getScore());
      players[j].setName(players[j-1].getName());
    }

  }


}
