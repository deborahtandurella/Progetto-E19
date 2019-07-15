package network.commands;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import network.Command;

public class JumpCommand extends Command {
    private static final long serialVersionUID = -539210512249000001L;

    private Double birdX;
    private Double birdY;

    public JumpCommand(Double x, Double y) {
        birdX = x;
        birdY = y;
    }

    @Override
    public void execute(OnlineRemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.getBird().setX(birdX);
        remoteGame.getBird().setY(birdY);
        remoteGame.playerJump();
    }
}
