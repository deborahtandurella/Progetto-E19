package scoreboard;

import game.player.Result;

import java.io.*;
import java.util.ArrayList;

public class ScoreBoardPersistencyFacade {


    public ArrayList<Result> readScoreBoard( ) throws IOException {
        ArrayList<Result> results = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("res/records/leaderboard_E.txt")));
        String riga;
        while((riga = reader.readLine())!=null){
            String[] resultString = riga.split("\\t");
            results.add(new Result(resultString[0], Integer.parseInt(resultString[1])));
        }
        reader.close();
        return results;
    }

    public void writePlayers(ArrayList<Result> results) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(new File("res/records/leaderboard_E.txt")));
        for(Result result: results){
            String line =  result.getName() + "\t" + result.getScore();
            writer.println(line);
        }
        writer.close();
    }


}
