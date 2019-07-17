package network.commands;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import network.Command;

/**
 *  Comunica un salto del bird e la sua posizione
 */
public class JumpCommand extends Command {
    private static final long serialVersionUID = -539210512249000001L;

    private Double birdX;
    private Double birdY;

    /**
     * @param x coordinate x del bird
     * @param y coodrdinate y del bird
     */
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
