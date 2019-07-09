package game.itemGeneration.obstacle;

import graphics.Canvas;

public enum ObstacleGeneratorType {
    EASY {
        public ObstacleGenerator create(Canvas canvas){
            return new EasyObstacleGenerator(canvas);
        }
    },
    MEDIUM{
        public ObstacleGenerator create(Canvas canvas){
            return new NormalObstacleGenerator(canvas);
        }
    },
    HARD{
        public ObstacleGenerator create(Canvas canvas){
            return new HardObstacleGenerator(canvas);
        }
    };

    public abstract ObstacleGenerator create(Canvas canvas);
}
