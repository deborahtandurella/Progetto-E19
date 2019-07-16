package network.commands;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import network.Command;

/**
 *  Comunica l'evento di GameOver
 */
public class GameOverCommand extends Command {
    private static final long serialVersionUID = -539210512249000009L;

    @Override
    public void execute(OnlineRemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.gameOver();
    }
}
