package network.test.commands;

import flappyEntities.Entity;
import flappyEntities.logic.obstacles.ObstacleLogicComponent;
import game.OnlineLocalGame;
import game.RemoteGame;

public class ObstacleCollisionCommand extends Command {
    private static final long serialVersionUID = -539210512249000003L;

    private Integer ID;
    public ObstacleCollisionCommand(Entity obstacle) {
        ID= obstacle.getID();
    }

    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        remoteGame.obstacleCollision();
        Entity entity = remoteGame.getEntityByID(ID);
        if (entity!=null){
            ObstacleLogicComponent obstacle = (ObstacleLogicComponent) entity.getLogicComponent();
            if (obstacle.destroyOnHit())
                remoteGame.removeScrollingElement(obstacle);
        }
    }
}
