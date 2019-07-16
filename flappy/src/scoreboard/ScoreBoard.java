package scoreboard;

import game.player.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  La classifica di gioco, rappresenta
 */
public class ScoreBoard {

    private ArrayList<Result> results;
    private ScoreboardDAO scoreboardDAO;
    private static final int N_PLAYERS = 10;


    public ScoreBoard() throws IOException {
        this.scoreboardDAO = CSVScoreboardDAO.getInstance();
        results = scoreboardDAO.readScoreBoard();
        results = new ArrayList<>(results.subList(0, N_PLAYERS));
    }



    public boolean addResult(Result newResult) {
        results.add(newResult);
        Collections.sort(results);
        if (results.indexOf(newResult) < N_PLAYERS) {
            newRecord();
            results.remove(results.size() - 1);
            return true;
        } else {
            results.remove(results.size() - 1);
            return false;
        }
    }

    private void newRecord() {
        try {
            scoreboardDAO.writePlayers(results);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }


    public String printName() {
        StringBuilder sb = new StringBuilder();
        for (Result result : results) {
            sb.append(result.getName()).append("\n");
        }
        return sb.toString();
    }

    public String printScores() {
        StringBuilder s = new StringBuilder();
        for (Result result : results) {
            s.append(result.getScore()).append("\n");
        }
        return s.toString();
    }


    public void clearScoreBoard() {
        for (Result result : results) {
            result.setName("-------------");
            result.setScore(0);
        }
        try {
            scoreboardDAO.writePlayers(results);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
