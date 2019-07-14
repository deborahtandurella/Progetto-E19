package game;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.LogicComponent;
import flappyEntities.logic.bird.BirdLogicComponent;
import flappyEntities.logic.items.HeartLogicComponent;
import flappyEntities.logic.obstacles.ObstacleLogicComponent;
import game.gameEvents.GameEventDispatcher;
import game.gameEvents.GameEventType;
import game.itemGeneration.heart.HeartGenerator;
import game.itemGeneration.heart.HeartListener;
import game.itemGeneration.obstacle.ObstacleGenerator;
import game.itemGeneration.obstacle.ObstacleListener;
import graphics.Canvas;
import graphics.HUD.Hud;
import graphics.HUD.SinglePlayerHud;
import logic.SinglePlayer.SingleModePlayer;
import logic.player.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.util.concurrent.CopyOnWriteArrayList;

import static logic.gameConstants.GameConstants.BIRD_WIDTH;

public class LocalGame extends GameEventDispatcher implements HeartListener, ObstacleListener {
    private CopyOnWriteArrayList<Entity> entities;
    private CopyOnWriteArrayList<ObstacleLogicComponent> obstacles;
    private CopyOnWriteArrayList<HeartLogicComponent> hearts;
    private BirdLogicComponent bird;
    private SingleModePlayer player;
    private Canvas canvas;
    private double gameSpeed;
    private ObstacleGenerator obstacleGenerator;
    private Image background;
    private Hud hud;

    public LocalGame(Canvas canvas, DifficultySettings settings, SingleModePlayer player) {
        this.canvas = canvas;
        this.gameSpeed = settings.getSpeed();
        this.obstacleGenerator = settings.getObstacleGenerator().create(canvas);
        this.player= player;
        entities = new CopyOnWriteArrayList<>();
        hearts = new CopyOnWriteArrayList<>();
        obstacles = new CopyOnWriteArrayList<>();
        Entity birdEntity = EntityFactory.makeBird(0.2, 0.5,canvas);
        entities.add(birdEntity);
        bird = (BirdLogicComponent) birdEntity.getLogicComponent();
        obstacleGenerator.addListener(this);
        HeartGenerator heartGenerator = new HeartGenerator(canvas);
        obstacleGenerator.addListener(heartGenerator);
        heartGenerator.addListener(this);
        try {
            hud = new SinglePlayerHud(player, canvas);
            background = new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.BACKGROUND));
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    private void addEntity(Entity entity){
        entities.add(entity);
    }
    public void update(int delta){
        delta*=gameSpeed;
        updateEntities(delta);
        obstacleGenerator.update(delta);
        if (!bird.isImmune()){
            checkCollisions();
            checkScore();
        }
        checkOutOfBounds();
    }
    public void render(){
        canvas.drawImage(background, 0, 0, 1, 1);
        renderEntities();
        hud.render();
    }
    public void playerJump(){
        bird.jump();
        notifyEvent(GameEventType.JUMP);
    }
    private void checkOutOfBounds(){
        for( ObstacleLogicComponent obstacle : obstacles)
            if (obstacle.outOfBounds())
                removeObstacle(obstacle);
        for( HeartLogicComponent heart : hearts)
            if (heart.outOfBounds())
                removeHeart(heart);
    }
    private void checkCollisions(){
        checkHeartCollisions();
        checkObstacleCollisions();
    }
    private void checkScore(){
        for(ObstacleLogicComponent obstacle: obstacles){
            if( ( !obstacle.isPassed() ) && (Math.abs(bird.getX()-obstacle.getX())<BIRD_WIDTH/4)) {
                obstacle.setPassed(true);
                player.addScore();
            }
        }
    }
    private void checkObstacleCollisions(){
        for(ObstacleLogicComponent obstacle : obstacles)
            if (obstacle.collide(bird))
                obstacleCollision(obstacle);
    }
    private void obstacleCollision(ObstacleLogicComponent obstacle){
        notifyEvent(GameEventType.COLLISION);
        bird.acquireImmunity();
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
        for(Entity entity: entities)
            entity.update(delta);
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
        for(Entity entity: entities)
            entity.render();
    }

    public Player getPlayer() {
        return player;
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
