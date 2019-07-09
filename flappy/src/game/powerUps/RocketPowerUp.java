package game.powerUps;

import game.RemoteGame;
import network.test.CommandHandler;

public class RocketPowerUp implements PowerUp{
    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public void execute(CommandHandler handler, RemoteGame remoteGame) {

    }
}
