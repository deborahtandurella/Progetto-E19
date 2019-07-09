package network.test.commands;

import entityComponent.Entity;
import entityComponent.implementations.ScrollingElement;
import game.OnlineLocalGame;
import game.RemoteGame;

public class CoinCollisionCommand extends Command {
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
