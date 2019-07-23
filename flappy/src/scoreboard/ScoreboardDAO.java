package scoreboard;

import game.player.Result;

import java.util.ArrayList;

/**
 *  Data Access Object della Scoreboard
 */
public interface ScoreboardDAO {
    /**
     * @return i risultati presenti nella classifica
     * @throws DatabaseException
     */
    ArrayList<Result> readScoreBoard( ) throws DatabaseException;

    /**
     * Scrive i risultati nella classifica
     * @param results i risultati da scrivere
     * @throws DatabaseException
     */
    void writeResults(ArrayList<Result> results) throws DatabaseException;
}
