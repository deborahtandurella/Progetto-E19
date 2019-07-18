package game.player;

/**
 *  Giocatore di FlappyBird
 */
public interface Player{
    /**
     * @return il punteggio del giocatore
     */
    int getScore();
    void addScore();

    /**
     * @return le informazioni del giocatore
     */
    PlayerInfo getPlayerInfo();
}