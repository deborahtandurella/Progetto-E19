package game;

import entityComponent.Entity;
import entityComponent.components.LogicComponent;
import entityComponent.implementations.bird.BirdLogicComponent;
import entityComponent.implementations.items.heart.HeartLogicComponent;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.gameEvents.GameEventDispatcher;
import game.gameEvents.GameEventType;
import game.itemGeneration.heart.HeartGenerator;
import game.itemGeneration.heart.HeartListener;
import game.itemGeneration.obstacle.ObstacleGenerator;
import game.itemGeneration.obstacle.ObstacleGeneratorFactory;
import game.itemGeneration.obstacle.ObstacleListener;
import graphics.Canvas;
import logic.SinglePlayer.Player;
import logic.gameElements.Heart;

import java.util.ArrayList;

public class LocalGame extends GameEventDispatcher implements HeartListener, ObstacleListener {
    private ArrayList<Entity> entities;
    private ArrayList<ObstacleLogicComponent> obstacles;
    private ArrayList<HeartLogicComponent> hearts;
    private BirdLogicComponent bird;
    private Player player;
    private Canvas canvas;
    private double gameSpeed;
    private ObstacleGenerator obstacleGenerator;
    private HeartGenerator heartGenerator;

    public LocalGame(Canvas canvas, DifficultySettings settings) {
        this.canvas = canvas;
        this.gameSpeed = settings.getSpeed();
        this.obstacleGenerator = ObstacleGeneratorFactory.makeObstacleGenerator(settings.getObstacleGenerator(), canvas);
        obstacleGenerator.addListener(this);
        this.heartGenerator = new HeartGenerator(canvas);
        heartGenerator.addListener(this);
    }

    private void addEntity(Entity entity){
        entities.add(entity);
    }
    public void update(int delta){
        delta*=gameSpeed;
        updateEntities(delta);
        checkCollisions();
        checkScore();
        checkOutOfBounds();
    }
    public void render(){
        renderEntities();
    }
    public void playerJump(){
        bird.jump();
        notifyEvent(GameEventType.JUMP);
    }
    private void checkOutOfBounds(){
        for( ObstacleLogicComponent obstacle : obstacles){
            if (obstacle.outOfBounds()){
                removeObstacle(obstacle);
            }
        }
        for( HeartLogicComponent heart : hearts){
            if (heart.outOfBounds()){
                removeObstacle(heart);
            }
        }
    }
    private void checkCollisions(){
        checkHeartCollisions();
        checkObstacleCollisions();
    }
    private void checkScore(){
        for(ObstacleLogicComponent obstacle: obstacles){
            if( ( !obstacle.isPassed() ) && (bird.getX() > obstacle.getX()) ){
                obstacle.setPassed(true);
                player.addScore();
            }
        }
    }
    private void checkObstacleCollisions(){
        for(ObstacleLogicComponent obstacle : obstacles){
            if (obstacle.collide(bird)){
                obstacleCollision(obstacle);
            }
        }
    }
    private void obstacleCollision(ObstacleLogicComponent obstacle){
        notifyEvent(GameEventType.COLLISION);
        decreaseLife();
        if(obstacle.destroyOnHit())
            removeObstacle(obstacle);

    }
    private void checkHeartCollisions(){
        for(HeartLogicComponent heart: hearts){
            if(heart.collide(bird)){
                removeHeart(heart);
                increaseLife();
            }
        }
    }
    private void decreaseLife(){
        player.loseHeart();
        if (player.getHearts()==0)
            gameover();

    }
    private void increaseLife(){
        player.addHeart();
    }
    private void updateEntities(int delta){
        for(Entity entity: entities){
            entity.update(delta);
        }
    }
    private void removeHeart(LogicComponent logic){
        hearts.removeIf(obstacleLogicComponent -> obstacleLogicComponent == logic);
        removeEntity(logic);
    }
    private void removeObstacle(LogicComponent logic){
        obstacles.removeIf(obstacleLogicComponent -> obstacleLogicComponent == logic);
        removeEntity(logic);
    }
    private void removeEntity(LogicComponent logic){
        entities.removeIf(entity -> entity.getLogicComponent() == logic);
    }
    private void renderEntities(){
        for(Entity entity: entities){
            entity.render();
        }
    }
    private void gameover(){
        notifyEvent(GameEventType.GAMEOVER);

    }

    @Override
    public void onHeartGenerated(Entity heart) {
        addEntity(heart);
        hearts.add((HeartLogicComponent) heart.getLogicComponent());

    }

    @Override
    public void onObstacleGenerated(Entity obstacle) {
        addEntity(obstacle);
        obstacles.add((ObstacleLogicComponent) obstacle.getLogicComponent());

    }
}

/*TODO inizializzazione, aggiunta di entity*/