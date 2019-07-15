package network.commands;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.items.CoinLogicComponent;
import game.multiplayer.OnlineLocalGame;
import game.multiplayer.RemoteGame;
import network.Command;

public class CoinGeneratedCommand extends Command {
    private static final long serialVersionUID = -539210512249000004L;

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
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        Entity coin = EntityFactory.makeCoin(x, y, remoteGame.getCanvas());
        coin.setID(ID);
        remoteGame.addScrollingElement(coin);
    }
}
