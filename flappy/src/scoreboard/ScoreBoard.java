package scoreboard;

import game.player.Result;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  La classifica di gioco, mantiene in cache la classifica ed è in grado di leggerla e scriverla servendosi di uno ScoreboardDAO
 */
public class ScoreBoard {

    private ArrayList<Result> results;
    private ScoreboardDAO scoreboardDAO;
    private static final int N_PLAYERS = 10;

    /**
     * Inizializza la classifica leggendola dal database
     *
     */
    public ScoreBoard()  {
        this.scoreboardDAO = CSVScoreboardDAO.getInstance();
        try{
            results = scoreboardDAO.readScoreBoard();
            results = new ArrayList<>(results.subList(0, N_PLAYERS));
        } catch (DatabaseException e){
            e.printStackTrace();
        }
    }

    /**
     * Prova ad aggiungere un risultato alla classifica
     * @param newResult il nuovo risultato
     * @return true se il nuovo risultato è tale da piazzarsi nella classifica
     */
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
            scoreboardDAO.writeResults(results);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return i risultati in classifica
     */
    public ArrayList<Result> getResults(){
        return results;
    }



    /**
     *  Svuota la classifica
     */
    public void clear() {
        for (Result result : results) {
            result.setName("-------------");
            result.setScore(0);
        }
        try {
            scoreboardDAO.writeResults(results);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
