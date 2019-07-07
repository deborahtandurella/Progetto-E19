package network.test.commands;

import entityComponent.Entity;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.RemoteGame;

public class ObstacleCollisionCommand extends Command {
    private Integer ID;
    public ObstacleCollisionCommand(Entity obstacle) {
        ID= obstacle.getID();
    }

    @Override
    public void execute(RemoteGame game) {
        game.obstacleCollision();
        Entity entity = game.getEntityByID(ID);
        if (entity!=null){
            ObstacleLogicComponent obstacle = (ObstacleLogicComponent) entity.getLogicComponent();
            if (obstacle.destroyOnHit())
                game.removeScrollingElement(obstacle);
        }
 }
}
