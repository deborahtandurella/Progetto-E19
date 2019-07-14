package network.test.commands;

import flappyEntities.Entity;
import flappyEntities.logic.SerializableEntity;
import flappyEntities.logic.obstacles.ObstacleLogicComponent;
import game.OnlineLocalGame;
import game.RemoteGame;

public class ObstacleGeneratedCommand extends Command {
    private static final long serialVersionUID = -539210512249000006L;

    private SerializableEntity obstacle;
    private int ID;

    public ObstacleGeneratedCommand(Entity obstacleEntity) {
        obstacle= ((ObstacleLogicComponent) obstacleEntity.getLogicComponent()).getSerializableVersion();
        ID= obstacleEntity.getID();
    }

    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        Entity obstacleEntity= obstacle.instantiate(remoteGame.getCanvas());
        obstacleEntity.setID(ID);
        remoteGame.addScrollingElement(obstacleEntity);
    }
}
