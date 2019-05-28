package GameScore;

import jdk.internal.util.xml.impl.Input;
import logic.SinglePlayer.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ScoreBoard {

  private Player[] players;

  public ScoreBoard() throws IOException {
      this.players = new Player[10];

      readScoreBoard();
  }

  public void readScoreBoard() throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("res/record.txt"));


    for(int i = 0; i < 10; i++){
      String riga = in.readLine();
      String[] result = riga.split("\\t");
      players[i] = new Player();
      players[i].setName(result[1]);
      players[i].setScore(Integer.parseInt(result[2]));
    }
    in.close();


  }

  public void compareScore(Player p) throws IOException {
    System.out.println(players[0].getName() + players[0].getScore());
    System.out.println(p.getScore());
    for(int i=0; i<10; i++){
      if( p.getScore() > players[i].getScore()){
        shiftPlayers(i);
        Scanner s = new Scanner(System.in);
        p.setName("io");
        players[i].setName(p.getName());
        players[i].setScore(p.getScore());
        writePlayers();
        break;
      }
    }  }

  public void shiftPlayers(int i){

    for(int j=9; j>i; j--){
      players[j].setScore(players[j-1].getScore());
      players[j].setName(players[j-1].getName());
    }

  }

  public void writePlayers() throws IOException {
    PrintWriter f = new PrintWriter(new FileWriter("res/record.txt"));
    for(int i = 0; i < 10; i++){
      String line = i+1 +")\t"+ players[i].getName() + "\t" + players[i].getScore();
      f.println(line);
    }
    f.close();
  }

}
