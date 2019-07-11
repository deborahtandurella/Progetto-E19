package game.powerUps;

import game.OnlineLocalGame;
import game.RemoteGame;

public class SpeedPowerUP implements PowerUp{
    @Override
    public int getPrice() {
        return 4;
    }

    @Override
    public void execute(OnlineLocalGame localGame, RemoteGame remoteGame) {

    }
}
