package game.powerUps;

import game.RemoteGame;
import network.test.CommandHandler;

public class SpeedPowerUP implements PowerUp{
    @Override
    public int getPrice() {
        return 4;
    }

    @Override
    public void execute(CommandHandler handler, RemoteGame remoteGame) {

    }
}
