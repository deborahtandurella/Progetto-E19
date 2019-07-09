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
    public void execute(RemoteGame game) {
        game.getBird().setX(birdX);
        game.getBird().setY(birdY);
        game.playerJump();
    }
}
