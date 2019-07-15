package network.commands;

import flappyEntities.Entity;
import flappyEntities.logic.ScrollingElement;
import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import network.Command;

public class CoinCollisionCommand extends Command {
    private static final long serialVersionUID = -539210512249000005L;

    private Integer ID;
    public CoinCollisionCommand(Entity coin){
        ID= coin.getID();
    }
    @Override
    public void execute(OnlineRemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.increaseCoins();
        Entity coin = remoteGame.getEntityByID(ID);
        if (coin!=null)
            remoteGame.removeScrollingElement((ScrollingElement)coin.getLogicComponent());


    }
}
