package network.test.commands;

import game.RemoteGame;

public class IncreaseScoreCommand extends Command {
    @Override
    public void execute(RemoteGame game) {
        game.increaseScore();
    }
}
