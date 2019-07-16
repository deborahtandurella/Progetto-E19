package network;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

public class CommandReceiver implements NetworkReceiver{
    private OnlineLocalGame localGame;
    private OnlineRemoteGame remoteGame;

    public CommandReceiver(OnlineLocalGame localGame, OnlineRemoteGame remoteGame) {
        this.localGame = localGame;
        this.remoteGame = remoteGame;
    }
    public void startListening(NetworkHandle network){
        network.setReceiver(this);
    }

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
