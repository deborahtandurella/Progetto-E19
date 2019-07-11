package GameScore;

import logic.SinglePlayer.Result;

import java.io.*;

public class ScoreFacade {

    public void readScoreBoard(Result[] results, int nPlayers) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File("res/records/leaderboard_E.txt")));
        for(int i = 0; i <nPlayers; i++){
            String riga = in.readLine();
            String[] result = riga.split("\\t");
            results[i] = new Result();
            results[i].setName(result[0]);
            results[i].setScore(Integer.parseInt(result[1]));
        }
        in.close();
    }

    public void writePlayers(Result[] results, int nPlayers) throws IOException {
        PrintWriter f = new PrintWriter(new FileWriter(new File("res/records/leaderboard_E.txt")));
        for(int i = 0; i < nPlayers; i++){
            String line =  results[i].getName() + "\t" + results[i].getScore();
            f.println(line);
        }
        f.close();
    }


}
