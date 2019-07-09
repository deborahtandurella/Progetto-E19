package network.test.commands;

import game.OnlineLocalGame;
import game.RemoteGame;

public class JumpCommand extends Command {
    private Double birdX;
    private Double birdY;

    public JumpCommand(Double x, Double y) {
        birdX = x;
        birdY = y;
    }


    @Override
    public String toString() {
        return "JumpCommand{" +
                "birdX=" + birdX +
                ", birdY=" + birdY +
                '}';
    }

    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.getBird().setX(birdX);
        remoteGame.getBird().setY(birdY);
        remoteGame.playerJump();
    }
}
