package game;

import entityComponent.Entity;
import entityComponent.components.LogicComponent;
import entityComponent.implementations.bird.BirdLogicComponent;
import entityComponent.implementations.items.heart.HeartLogicComponent;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.gameEvents.GameEventDispatcher;
import game.gameEvents.GameEventType;
import logic.SinglePlayer.Player;

import java.util.ArrayList;

public class LocalGame extends GameEventDispatcher {
    private ArrayList<Entity> entities;
    private ArrayList<ObstacleLogicComponent> obstacles;
    private ArrayList<HeartLogicComponent> hearts;
    private BirdLogicComponent bird;
    private Player player;

    private void addEntity(Entity entity){
    }
    public void update(int delta){
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
        if(obstacle.isPeriodic()){

        }
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
}

/*TODO inizializzazione, aggiunta di entity*/