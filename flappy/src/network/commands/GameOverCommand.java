package network.commands;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.RemoteGame;
import network.Command;

public class GameOverCommand extends Command {
    private static final long serialVersionUID = -539210512249000009L;

    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.gameOver();
    }
}
