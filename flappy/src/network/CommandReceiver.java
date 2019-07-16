package network;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

/**
 *  Esegue i comandi ricevuti sulla partita locale e remota
 */
public class CommandReceiver implements NetworkReceiver{
    private OnlineLocalGame localGame;
    private OnlineRemoteGame remoteGame;

    public CommandReceiver(OnlineLocalGame localGame, OnlineRemoteGame remoteGame) {
        this.localGame = localGame;
        this.remoteGame = remoteGame;
    }

    /**
     * Converte il messaggio in Command e lo esegue
     * @param message il messaggio ricevuto
     */
    @Override
    public void receive(Object message) {
        try {
            Command command = (Command) message;
            command.execute(remoteGame, localGame);
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }
}
