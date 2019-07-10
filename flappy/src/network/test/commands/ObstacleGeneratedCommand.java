package network.test.commands;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.SerializableElement;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.OnlineLocalGame;
import game.RemoteGame;

import static logic.gameConstants.GameConstants.PIPE_SPEED;

public class ObstacleGeneratedCommand extends Command {
    private SerializableElement obstacle;
    private int ID;

    public ObstacleGeneratedCommand(Entity obstacleEntity) {
        obstacle= ((ObstacleLogicComponent) obstacleEntity.getLogicComponent()).getTransmittableVersion();
        ID= obstacleEntity.getID();
    }

    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        Entity obstacleEntity= obstacle.instantiate(remoteGame.getCanvas());
        obstacleEntity.setID(ID);
        remoteGame.addScrollingElement(obstacleEntity);
    }
}
