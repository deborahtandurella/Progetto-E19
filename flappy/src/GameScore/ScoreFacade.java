package GameScore;

import logic.SinglePlayer.Player;

import java.io.*;

public class ScoreFacade {

    public void readScoreBoard(Player[] players, int nPlayers) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("res/record.txt"));
        for(int i = 0; i <nPlayers; i++){
            String riga = in.readLine();
            String[] result = riga.split("\\t");
            players[i] = new Player();
            players[i].setName(result[1]);
            players[i].setScore(Integer.parseInt(result[2]));
        }
        in.close();
    }

    public void writePlayers(Player[] players, int nPlayers) throws IOException {
        PrintWriter f = new PrintWriter(new FileWriter("res/record.txt"));
        for(int i = 0; i < nPlayers; i++){
            String line = i+1 +")\t"+ players[i].getName() + "\t" + players[i].getScore();
            f.println(line);
        }
        f.close();
    }




}
