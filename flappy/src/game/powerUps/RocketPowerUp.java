package game.powerUps;

import entityComponent.EntityFactory;
import game.OnlineLocalGame;
import game.RemoteGame;

public class RocketPowerUp implements PowerUp{
    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public void execute(OnlineLocalGame localGame, RemoteGame remoteGame) {
        localGame.onObstacleGenerated(EntityFactory.makeRocket(1, remoteGame.getBird().getY(), localGame.getCanvas()));
    }
}
