package game.multiplayer.powerUps;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.RemoteGame;

import java.util.Timer;
import java.util.TimerTask;

public class SpeedPowerUP implements PowerUp{
    static final long serialVersionUID = -539210512251000003L;

    private long duration = 20000; //durata del powerup in millisecondi
    @Override
    public int getPrice() {
        return 4;
    }

    @Override
    public void execute(OnlineLocalGame localGame, RemoteGame remoteGame) {
        localGame.changeSpeedLimits(+0.4);
        localGame.changeSpeed(+0.4);
        if(localGame.getTimeLeft()-duration>0)
        (new Timer()).schedule(new TimerTask(){
            @Override
            public void run() {
                localGame.changeSpeedLimits(-0.4);
            }
        }, duration);
    }

    @Override
    public int getAffectedGame() {
        return PowerUp.LOCAL_GAME;
    }


}
