package network.commands;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import game.multiplayer.powerUps.PowerUp;
import game.player.MultiModePlayer;
import network.Command;

/**
 *  Comunica l'attivazione di un powerup
 */
public class PowerUpCommand extends Command {
    private static final long serialVersionUID = -539210512249000007L;

    private PowerUp powerUp;

    public PowerUpCommand(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public void execute(OnlineRemoteGame remoteGame, OnlineLocalGame localGame) {
        if(powerUp.getAffectedGame()==PowerUp.LOCAL_GAME) {
            MultiModePlayer enemy = remoteGame.getPlayer();
            enemy.setCoins(enemy.getCoins() - powerUp.getPrice());
        }
        powerUp.execute(localGame, remoteGame);
    }
}
