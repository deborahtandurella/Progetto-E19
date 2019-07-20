package network.commands;

import flappyEntities.Entity;
import flappyEntities.logic.HasSerializableVersion;
import flappyEntities.logic.SerializableEntity;
import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import network.Command;

/**
 *  Comunica un evento di generazione di un elemento di gioco che scorre orizzontalmente
 */
public class ScrollingElementGeneratedCommand extends Command {
    private static final long serialVersionUID = -539210512249000006L;

    private SerializableEntity entity;
    private int ID;

    /**
     * @param scrollingElement l'elemento creato
     */
    public ScrollingElementGeneratedCommand(Entity scrollingElement) {
        entity= ((HasSerializableVersion) scrollingElement.getLogicComponent()).getSerializableVersion();
        ID= scrollingElement.getID();
    }

    @Override
    public void execute(OnlineRemoteGame remoteGame, OnlineLocalGame localGame) {
        Entity newEntity= entity.instantiate(remoteGame.getCanvas());
        newEntity.setID(ID);
        remoteGame.addScrollingElement(newEntity);
    }
}
