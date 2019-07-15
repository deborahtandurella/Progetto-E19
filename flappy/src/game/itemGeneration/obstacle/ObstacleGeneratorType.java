package game.itemGeneration.obstacle;

import graphics.Canvas;

/**
 *  Tipi di ObstacleGenerator disponibili, utilizzata anche come Factory per generarli
 */
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

    /**
     * Crea un ObstacleGenerator
     * @param canvas il contesto grafico che l'OstacleGenerator utilizzerà per genearre gli ostacoli
     * @return il generatore di ostacoli
     */
    public abstract ObstacleGenerator create(Canvas canvas);
}
