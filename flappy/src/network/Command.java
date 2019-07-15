package network;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.RemoteGame;

import java.io.Serializable;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = -539210512249000000L;
    abstract public void execute(RemoteGame remoteGame, OnlineLocalGame localGame);
}
