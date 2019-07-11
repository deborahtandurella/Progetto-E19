package game.powerUps;

import entityComponent.EntityFactory;
import game.OnlineLocalGame;
import game.RemoteGame;

public class GravityPowerUp implements PowerUp{
    @Override
    public int getPrice() {
        return 3;
    }

    @Override
    public void execute(OnlineLocalGame localGame, RemoteGame remoteGame) {
    }
}
