package game.multiplayer.powerUps;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

import java.io.Serializable;

public interface PowerUp extends Serializable {
    long serialVersionUID = -539210512251000000L;
    int REMOTE_GAME =0;
    int LOCAL_GAME =1;

    /**
     * @return il prezzo del PowerUp
     */
    int getPrice();

    /**
     * Esegue il PowerUp
     * @param localGame la partita locale
     * @param remoteGame la partita remota
     */
    void execute(OnlineLocalGame localGame, OnlineRemoteGame remoteGame);

    /**
     * Alcuni PowerUp dopo essere stati utilizzati per l'OnlineLocalGame dell'avversario necessitano di essere reinviati
     * al giocatore che li ha attivati per azionare il PowerUp anche nel suo OnlineRemoteGame. Il metodo restituisce
     * a quale dei due OnlineGame si riferisce il PowerUp.
     * @return il tipo di partita alla quale si riferisce il PowerUp in questo istante
     */
    int getAffectedGame();
}
