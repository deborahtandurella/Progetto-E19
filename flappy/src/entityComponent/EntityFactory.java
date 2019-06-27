package entityComponent;

import entityComponent.implementations.bird.BirdGraphicComponent;
import entityComponent.implementations.bird.BirdLogicComponent;
import entityComponent.implementations.items.coin.CoinGraphicComponent;
import entityComponent.implementations.items.coin.CoinLogicComponent;
import entityComponent.implementations.items.heart.HeartGraphicComponent;
import entityComponent.implementations.items.heart.HeartLogicComponent;
import entityComponent.implementations.obstacles.pipes.PipeGraphicComponent;
import entityComponent.implementations.obstacles.pipes.PipeLogicComponent;
import entityComponent.implementations.obstacles.pipes.movingPipe.MovingPipeLogicComponent;
import entityComponent.implementations.obstacles.rocket.RocketGraphicComponent;
import entityComponent.implementations.obstacles.rocket.RocketLogicComponent;
import graphics.Canvas;
import graphics.Screen;
import org.newdawn.slick.Graphics;

public class EntityFactory {
    public static Entity makeBird(double x, double y, Canvas canvas){
        return new GameElementEntity(new BirdLogicComponent(x,y), new BirdGraphicComponent(canvas));
    }
    public static Entity makeNormalPipe(double x, double y, Canvas canvas){
        return new GameElementEntity(new PipeLogicComponent(x,y), new PipeGraphicComponent(canvas));
    }
    public static Entity makeMovingPipe(double x, double y, Canvas canvas){
        return new GameElementEntity(new MovingPipeLogicComponent(x,y), new PipeGraphicComponent(canvas));
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
