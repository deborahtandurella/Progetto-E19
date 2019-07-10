package network.test;

import game.RemoteGame;
import logic.player.PlayerInfo;
import network.ConnectionListener;
import network.test.commands.Command;


public interface CommandHandler {
    void sendCommand(Command command);
    void startListening(RemoteGame game);
    void addConnectionListener(ConnectionListener listener);
    PlayerInfo getOthersInfo();
}
