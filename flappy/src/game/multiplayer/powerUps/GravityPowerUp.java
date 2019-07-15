package game.multiplayer.powerUps;

import flappyEntities.Entity;
import flappyEntities.graphic.BirdGraphicComponent;
import flappyEntities.logic.bird.BirdLogicComponent;
import flappyEntities.logic.bird.ReversedBirdLogicComponent;
import game.multiplayer.OnlineGame;
import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  PowerUp che inverte la gravità del giocatore avversario per un certo intervallo di tempo
 */
public class GravityPowerUp implements PowerUp{
    static final long serialVersionUID = -539210512251000002L;
    /**
     *  Il tempo in millisecondi per il quale la gravità rimane invertita
     */
    private long duration = 10000;
    private int affectedGame;


    public GravityPowerUp() {
        affectedGame=PowerUp.LOCAL_GAME;
    }

    @Override
    public int getPrice() {
        return 3;
    }

    @Override
    public void execute(OnlineLocalGame localGame, OnlineRemoteGame remoteGame) {
        if (affectedGame ==PowerUp.LOCAL_GAME){
            changeGravity(localGame);
            affectedGame=REMOTE_GAME;
            localGame.powerUpReceived(this);
        } else {
            changeGravity(remoteGame);
        }
    }

    /**
     * Inverte la gravità della partita
     * @param game la partita per la quale invertire la gravità
     */
    private void changeGravity(OnlineGame game){
        BirdLogicComponent newBird=new ReversedBirdLogicComponent(game.getBird());
        Entity birdEntity= Objects.requireNonNull(game.getEntity(game.getBird()));
        birdEntity.setLogicComponent(newBird);
        BirdGraphicComponent birdGraphic=(BirdGraphicComponent)birdEntity.getGraphicComponent();
        if(!birdGraphic.isFlipped())
            birdGraphic.flip();
        game.setBird(newBird);
        if(game.getTimeLeft()>duration)
            (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    restoreGravity(game);
                }
            }, duration);
    }

    /**
     * Raddrizza la gravità della partita
     * @param game la partita per la quale raddrizzare la gravità
     */
    private void restoreGravity(OnlineGame game){
        BirdLogicComponent newBird=new BirdLogicComponent(game.getBird());
        Entity birdEntity= Objects.requireNonNull(game.getEntity(game.getBird()));
        birdEntity.setLogicComponent(newBird);
        BirdGraphicComponent birdGraphic=(BirdGraphicComponent)birdEntity.getGraphicComponent();
        if(birdGraphic.isFlipped())
            birdGraphic.flip();
        game.setBird(newBird);
    }
    @Override
    public int getAffectedGame() {
        return affectedGame;
    }
}
