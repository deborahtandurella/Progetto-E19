package game.itemGeneration.obstacle;

import game.ObstacleGeneratorType;
import graphics.Canvas;

public class ObstacleGeneratorFactory {
    public static ObstacleGenerator makeObstacleGenerator(ObstacleGeneratorType type, Canvas canvas){
        switch(type){
            case EASY:
                return new EasyObstacleGenerator(canvas);
            case MEDIUM:
                return new NormalObstacleGenerator(canvas);
            case HARD:
                return new HardObstacleGenerator(canvas);
            default:
                return new NormalObstacleGenerator(canvas);
        }
    }
}
