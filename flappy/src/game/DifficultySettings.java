package game;

import game.itemGeneration.obstacle.ObstacleGeneratorType;

/**
 *  Impostazioni di difficoltà per una partita
 */
public class DifficultySettings {
    private double speed;
    private ObstacleGeneratorType obstacleGenerator;

    /**
     *
     * @param speed la velocità di gioco desiderata
     * @param obstacleGenerator il tipo di ObstacleGenerator desiderato
     */
    public DifficultySettings(double speed, ObstacleGeneratorType obstacleGenerator) {
        this.speed = speed;
        this.obstacleGenerator = obstacleGenerator;
    }

    /**
     * @return la velocità di gioco
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return la tipologia di generatoe di ostacoli
     */
    public ObstacleGeneratorType getObstacleGenerator() {
        return obstacleGenerator;
    }
}
