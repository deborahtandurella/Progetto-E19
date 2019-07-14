package flappyEntities;

import flappyEntities.graphic.*;
import flappyEntities.logic.bird.BirdLogicComponent;
import flappyEntities.logic.items.CoinLogicComponent;
import flappyEntities.logic.items.HeartLogicComponent;
import flappyEntities.logic.obstacles.pipes.MovingPipeLogicComponent;
import flappyEntities.logic.obstacles.pipes.PipeLogicComponent;
import flappyEntities.logic.obstacles.rocket.RocketLogicComponent;
import graphics.Canvas;

public class EntityFactory {
    public static Entity makeBird(double x, double y, Canvas canvas){
        return new GameElementEntity(new BirdLogicComponent(x,y), new BirdGraphicComponent(canvas));
    }
    public static Entity makeNormalPipe(double x, double y, Canvas canvas){
        return new GameElementEntity(new PipeLogicComponent(x,y), new PipeGraphicComponent(canvas));
    }
    public static Entity makeMovingPipe(double x, double y, double speedY, Canvas canvas){
        return new GameElementEntity(new MovingPipeLogicComponent(x,y,speedY), new PipeGraphicComponent(canvas));
    }
    public static Entity makeHeart(double x, double y, Canvas canvas){
        return new GameElementEntity(new HeartLogicComponent(x,y), new HeartGraphicComponent(canvas));
    }
    public static Entity makeRocket(double x, double y, Canvas canvas){
        return new GameElementEntity(new RocketLogicComponent(x,y), new RocketGraphicComponent(canvas));
    }
    public static Entity makeCoin(double x, double y, Canvas canvas){
        return new GameElementEntity(new CoinLogicComponent(x,y), new CoinGraphicComponent(canvas));
    }
}
