package game;

import game.itemGeneration.obstacle.ObstacleGenerator;

public class DifficultySettings {
    private double speed;
    private ObstacleGenerator obstacleGenerator;

    public DifficultySettings(double speed, ObstacleGenerator obstacleGenerator) {
        this.speed = speed;
        this.obstacleGenerator = obstacleGenerator;
    }

    public double getSpeed() {
        return speed;
    }

    public ObstacleGenerator getObstacleGenerator() {
        return obstacleGenerator;
    }
}
