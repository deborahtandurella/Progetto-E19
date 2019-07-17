package game.multiplayer.powerUps;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

import java.util.Timer;
import java.util.TimerTask;

/**
 *  SpeedPowerUp agisce sulla partità dell'avversario aumentando velocità e limiti di velocità per una certa durata di tempo
 */
public class SpeedPowerUP implements PowerUp{
    static final long serialVersionUID = -539210512251000003L;

    /**
     * durata del powerup in millisecondi
     */
    private static final long duration = 20000;
    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public void execute(OnlineLocalGame localGame, OnlineRemoteGame remoteGame) {
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
