package network;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

import java.io.Serializable;

/**
 *  Comando eseguibile sulla partita remota e quella locale
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = -539210512249000000L;

    /**
     * Esegue il comando sulla partita remota e su quella locale
     * @param remoteGame la partita remota
     * @param localGame la partita locale
     */
    abstract public void execute(OnlineRemoteGame remoteGame, OnlineLocalGame localGame);
}
