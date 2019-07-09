package network.test.commands;

import entityComponent.Entity;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.OnlineLocalGame;
import game.RemoteGame;

public class ObstacleCollisionCommand extends Command {
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
