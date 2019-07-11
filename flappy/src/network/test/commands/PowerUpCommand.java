package network.test.commands;

import game.OnlineLocalGame;
import game.RemoteGame;
import game.powerUps.PowerUp;

public class PowerUpCommand extends Command {
    private PowerUp powerUp;

    public PowerUpCommand(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        powerUp.execute(localGame, remoteGame);
    }
}
