package GameScore;

import logic.SinglePlayer.Result;

import java.io.*;
import java.util.ArrayList;

public class ScoreFacade {

    public ArrayList<Result> readScoreBoard( int nPlayers) throws IOException {
        ArrayList<Result> results = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(new File("res/records/leaderboard_E.txt")));
        for(int i = 0; i <nPlayers; i++){
            String riga = in.readLine();
            String[] resultString = riga.split("\\t");
            results.add(new Result(resultString[0], Integer.parseInt(resultString[1])));
        }
        in.close();
        return results;
    }

    public void writePlayers(ArrayList<Result> results, int nPlayers) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(new File("res/records/leaderboard_E.txt")));
        for(Result result: results){
            String line =  result.getName() + "\t" + result.getScore();
            writer.println(line);
        }
        writer.close();
    }


}
