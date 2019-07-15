package network;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.RemoteGame;
import game.player.PlayerInfo;


public interface CommandHandler {
    void sendCommand(Command command);
    void startListening(RemoteGame remoteGame, OnlineLocalGame localGame);
    void addConnectionListener(ConnectionListener listener);
    void removeListener(ConnectionListener listener);
    PlayerInfo getOthersInfo();
    void closeConnection();
}
