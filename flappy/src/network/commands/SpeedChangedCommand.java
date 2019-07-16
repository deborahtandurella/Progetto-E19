package network.commands;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import network.Command;

/**
 *  Comunica la velocità di gioco
 */
public class SpeedChangedCommand extends Command {
    private static final long serialVersionUID = -539210512249000008L;
    private double newSpeed;

    public SpeedChangedCommand(double newSpeed) {
        this.newSpeed = newSpeed;
    }

    @Override
    public void execute(OnlineRemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.setSpeed(newSpeed);
    }
}
