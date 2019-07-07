package network.test.commands;

import entityComponent.Entity;
import entityComponent.implementations.ScrollingElement;
import game.RemoteGame;

public class CoinCollisionCommand extends Command {
    private Integer ID;
    public CoinCollisionCommand(Entity coin){
        ID= coin.getID();
    }
    @Override
    public void execute(RemoteGame game) {
        game.increaseCoins();
        Entity coin = game.getEntityByID(ID);
        if (coin!=null)
            game.removeScrollingElement((ScrollingElement)coin.getLogicComponent());


    }
}
