package network.test.commands;

import flappyEntities.Entity;
import flappyEntities.logic.ScrollingElement;
import game.OnlineLocalGame;
import game.RemoteGame;

public class CoinCollisionCommand extends Command {
    private static final long serialVersionUID = -539210512249000005L;

    private Integer ID;
    public CoinCollisionCommand(Entity coin){
        ID= coin.getID();
    }
    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.increaseCoins();
        Entity coin = remoteGame.getEntityByID(ID);
        if (coin!=null)
            remoteGame.removeScrollingElement((ScrollingElement)coin.getLogicComponent());


    }
}
