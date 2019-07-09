package game.powerUps;

import game.RemoteGame;
import network.test.CommandHandler;

public class GravityPowerUp implements PowerUp{
    @Override
    public int getPrice() {
        return 3;
    }

    @Override
    public void execute(CommandHandler handler, RemoteGame remoteGame) {

    }
}
