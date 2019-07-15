package game.multiplayer.powerUps;

import flappyEntities.EntityFactory;
import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

/**
 * RocketPowerUp genera per il nemico un Rocket in corrispondenza dell'altezza del giocatore che ha attivato il PowerUp
 */
public class RocketPowerUp implements PowerUp{
    static final long serialVersionUID = -539210512251000001L;

    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public void execute(OnlineLocalGame localGame, OnlineRemoteGame remoteGame) {
        localGame.onObstacleGenerated(EntityFactory.makeRocket(1, remoteGame.getBird().getY(), localGame.getCanvas()));
    }

    @Override
    public int getAffectedGame() {
        return PowerUp.LOCAL_GAME;
    }
}
