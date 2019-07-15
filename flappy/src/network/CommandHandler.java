package network;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import game.player.PlayerInfo;


public interface CommandHandler {
    void sendCommand(Command command);
    void startListening(OnlineRemoteGame remoteGame, OnlineLocalGame localGame);
    void addConnectionListener(ConnectionListener listener);
    void removeListener(ConnectionListener listener);
    PlayerInfo getOthersInfo();
    void closeConnection();
}
