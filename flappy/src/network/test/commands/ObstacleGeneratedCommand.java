package network.test.commands;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.OnlineLocalGame;
import game.RemoteGame;

import static logic.gameConstants.GameConstants.PIPE_SPEED;

public class ObstacleGeneratedCommand extends Command {
    private double x;
    private double y;
    private double speedY;
    private double speedX;
    private int ID;

    public ObstacleGeneratedCommand(Entity obstacleEntity) {
        ObstacleLogicComponent obstacle= (ObstacleLogicComponent) obstacleEntity.getLogicComponent();
        x= obstacle.getX();
        y= obstacle.getY();
        speedY = obstacle.getSpeedY();
        speedX = obstacle.getSpeedX();
        ID= obstacleEntity.getID();
    }

    @Override
    public void execute(RemoteGame remoteGame, OnlineLocalGame localGame) {
        Entity obstacle;
        if (speedX!=PIPE_SPEED)
            obstacle = EntityFactory.makeRocket(x, y, remoteGame.getCanvas());
        else if (speedY == 0){
            obstacle = EntityFactory.makeNormalPipe(x, y, remoteGame.getCanvas());
        } else {
            obstacle = EntityFactory.makeMovingPipe(x, y, speedY, remoteGame.getCanvas());
        }
        obstacle.setID(ID);
        remoteGame.addScrollingElement(obstacle);
    }
}
