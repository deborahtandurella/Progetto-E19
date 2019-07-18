package scoreboard;

import game.player.Result;

import java.io.*;
import java.util.ArrayList;

/**
 * Implementazione di ScoreboardDAO per file .csv
 */
public class CSVScoreboardDAO implements ScoreboardDAO {
    static private CSVScoreboardDAO instance;
    private CSVScoreboardDAO(){

    }

    @Override
    synchronized public ArrayList<Result> readScoreBoard( ) throws DatabaseException {
        try {
            ArrayList<Result> results = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File("res/records/leaderboard.csv")));
            String riga;
            while((riga = reader.readLine())!=null){
                String[] resultString = riga.split("\\t");
                results.add(new Result(resultString[0], Integer.parseInt(resultString[1])));
            }
            reader.close();
            return results;
        } catch (IOException e){
            throw new DatabaseException(e);
        }
    }

    @Override
    synchronized public void writePlayers(ArrayList<Result> results) throws DatabaseException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(new File("res/records/leaderboard.csv")));
            for(Result result: results){
                String line =  result.getName() + "\t" + result.getScore();
                writer.println(line);
            }
            writer.close();
        } catch (IOException e){
            throw new DatabaseException(e);
        }
    }

    /**
     * @return l'istanza del singleton
     */
    static synchronized public ScoreboardDAO getInstance() {
        if (instance != null)
            return instance;
        else
            return instance= new CSVScoreboardDAO();
    }


}
