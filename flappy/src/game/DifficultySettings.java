package game;

public class DifficultySettings {
    private double speed;
    private ObstacleGeneratorType obstacleGenerator;

    public DifficultySettings(double speed, ObstacleGeneratorType obstacleGenerator) {
        this.speed = speed;
        this.obstacleGenerator = obstacleGenerator;
    }

    public double getSpeed() {
        return speed;
    }

    public ObstacleGeneratorType getObstacleGenerator() {
        return obstacleGenerator;
    }
}
