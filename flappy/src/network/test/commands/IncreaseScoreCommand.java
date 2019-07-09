package network.test.commands;

import game.OnlineLocalGame;
import game.RemoteGame;

public class IncreaseScoreCommand extends Command {
    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.increaseScore();
    }
}
