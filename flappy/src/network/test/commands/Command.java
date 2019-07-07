package network.test.commands;

import game.RemoteGame;

import java.io.Serializable;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = -5392105122490343339L;
    abstract public void execute(RemoteGame game);
}
