package game.powerUps;

import entityComponent.Entity;
import entityComponent.implementations.bird.BirdGraphicComponent;
import entityComponent.implementations.bird.BirdLogicComponent;
import entityComponent.implementations.bird.ReversedBirdLogicComponent;
import game.OnlineGame;
import game.OnlineLocalGame;
import game.RemoteGame;

import java.util.Objects;

public class GravityPowerUp implements PowerUp{
    static final long serialVersionUID = -539210512251000002L;
    private int affectedGame;
    public GravityPowerUp() {
        affectedGame=PowerUp.LOCAL_GAME;
    }

    @Override
    public int getPrice() {
        return 3;
    }

    @Override
    public void execute(OnlineLocalGame localGame, RemoteGame remoteGame) {
        if (affectedGame ==PowerUp.LOCAL_GAME){
            changeGravity(localGame);
            affectedGame=REMOTE_GAME;
            localGame.powerUpReceived(this);
        } else {
            changeGravity(remoteGame);
        }
    }
    private void changeGravity(OnlineGame game){
        BirdLogicComponent newBird=new ReversedBirdLogicComponent(game.getBird());
        Entity birdEntity= Objects.requireNonNull(game.getEntity(game.getBird()));
        birdEntity.setLogicComponent(newBird);
        ((BirdGraphicComponent)birdEntity.getGraphicComponent()).setFlipped(true);
        game.setBird(newBird);
    }
    @Override
    public int getAffectedGame() {
        return affectedGame;
    }
}
