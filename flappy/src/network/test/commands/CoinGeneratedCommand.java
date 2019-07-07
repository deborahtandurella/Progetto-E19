package network.test.commands;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.items.coin.CoinLogicComponent;
import game.RemoteGame;

public class CoinGeneratedCommand extends Command {
    private Double x;
    private Double y;
    private Integer ID;

    public CoinGeneratedCommand(Entity coinEntity) {
        CoinLogicComponent coin = (CoinLogicComponent) coinEntity.getLogicComponent();
        x= coin.getX();
        y= coin.getY();
        ID = coinEntity.getID();
    }

    @Override
    public void execute(RemoteGame game) {
        Entity coin = EntityFactory.makeCoin(x, y, game.getCanvas());
        coin.setID(ID);
        game.addScrollingElement(coin);
    }
}
