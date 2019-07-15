package game.multiplayer.powerUps;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

import java.io.Serializable;

public interface PowerUp extends Serializable {
    long serialVersionUID = -539210512251000000L;
    int REMOTE_GAME =0;
    int LOCAL_GAME =1;
    int getPrice();
    void execute(OnlineLocalGame localGame, OnlineRemoteGame remoteGame);
    int getAffectedGame();
}
