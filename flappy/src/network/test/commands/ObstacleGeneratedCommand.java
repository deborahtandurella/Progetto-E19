package network.test.commands;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.RemoteGame;

public class ObstacleGeneratedCommand extends Command {
    private double x;
    private double y;
    private double speedY;
    private int ID;

    public ObstacleGeneratedCommand(Entity obstacleEntity) {
        ObstacleLogicComponent obstacle= (ObstacleLogicComponent) obstacleEntity.getLogicComponent();
        x= obstacle.getX();
        y= obstacle.getY();
        speedY = obstacle.getSpeedY();
        ID= obstacleEntity.getID();
    }

    @Override
    public void execute(RemoteGame game) {
        Entity obstacle;
        if (speedY == 0){
            obstacle = EntityFactory.makeNormalPipe(x, y, game.getCanvas());
        } else {
            obstacle = EntityFactory.makeMovingPipe(x, y, speedY, game.getCanvas());
        }
        obstacle.setID(ID);
        game.addScrollingElement(obstacle);
    }
}
