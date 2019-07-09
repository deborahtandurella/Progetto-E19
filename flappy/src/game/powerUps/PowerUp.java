package game.powerUps;

import game.RemoteGame;
import network.test.CommandHandler;

public interface PowerUp {
    int getPrice();
    void execute(CommandHandler handler, RemoteGame remoteGame);
}
