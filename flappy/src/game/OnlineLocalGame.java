package game;

import entityComponent.Entity;
import entityComponent.EntityFactory;
import entityComponent.components.LogicComponent;
import entityComponent.implementations.bird.BirdLogicComponent;
import entityComponent.implementations.items.coin.CoinLogicComponent;
import entityComponent.implementations.obstacles.ObstacleLogicComponent;
import game.gameEvents.GameEventDispatcher;
import game.gameEvents.GameEventType;
import game.itemGeneration.coin.CoinGenerator;
import game.itemGeneration.coin.CoinListener;
import game.itemGeneration.obstacle.ObstacleGenerator;
import game.itemGeneration.obstacle.ObstacleListener;
import graphics.Canvas;
import graphics.HUD.Hud;
import graphics.HUD.MultiplayerHud;
import logic.player.MultiModePlayer;
import network.test.CommandHandler;
import network.test.commands.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class OnlineLocalGame extends GameEventDispatcher implements CoinListener, ObstacleListener {
    private CopyOnWriteArrayList<Entity> entities;
    private CopyOnWriteArrayList<ObstacleLogicComponent> obstacles;
    private CopyOnWriteArrayList<CoinLogicComponent> coins;
    private BirdLogicComponent bird;
    private MultiModePlayer player;
    private Canvas canvas;
    private double gameSpeed;
    private ObstacleGenerator obstacleGenerator;
    private Image background;
    private Hud hud;
    private CommandHandler commandHandler;
    private int IDcount;

    public OnlineLocalGame(Canvas canvas, DifficultySettings settings, CommandHandler commandHandler, MultiModePlayer player) {
        this.canvas = canvas;
        this.gameSpeed = settings.getSpeed();
        this.obstacleGenerator = settings.getObstacleGenerator().create(canvas);
        this.commandHandler= commandHandler;
        this.player= player;
        entities = new CopyOnWriteArrayList<>();
        coins = new CopyOnWriteArrayList<>();
        obstacles = new CopyOnWriteArrayList<>();
        Entity birdEntity = EntityFactory.makeBird(0.2, 0.5,canvas);
        addEntity(birdEntity);
        bird = (BirdLogicComponent) birdEntity.getLogicComponent();
        CoinGenerator coinGenerator = new CoinGenerator(canvas);
        obstacleGenerator.addListener(this);
        obstacleGenerator.addListener(coinGenerator);
        coinGenerator.addListener(this);
        try {
            hud= new MultiplayerHud(player, canvas);
            background = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.BACKGROUND));
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }


    public void update(int delta){
 //       delta*=gameSpeed;
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

    private void addEntity(Entity entity){
        entity.setID(IDcount++);
        entities.add(entity);
    }
    public void playerJump(){
        bird.jump();
        notifyEvent(GameEventType.JUMP);
        commandHandler.sendCommand(new JumpCommand(bird.getX(), bird.getY()));
    }
    private void checkOutOfBounds(){
        for( ObstacleLogicComponent obstacle : obstacles)
            if (obstacle.outOfBounds())
                removeObstacle(obstacle);
        for( CoinLogicComponent coin : coins)
            if (coin.outOfBounds())
                removeCoin(coin);
    }
    private void checkCollisions(){
        checkCoinCollisions();
        checkObstacleCollisions();
    }

    public BirdLogicComponent getBird() {
        return bird;
    }

    private void checkScore(){
        for(ObstacleLogicComponent obstacle: obstacles){
            if( ( !obstacle.isPassed() ) && (bird.getX() > obstacle.getX()) ){
                obstacle.setPassed(true);
                increaseScore();
            }
        }
    }
    private void increaseScore(){
        player.addScore();
        gameSpeed += 0.05;
        commandHandler.sendCommand(new IncreaseScoreCommand());
    }
    private void checkObstacleCollisions(){
        for(ObstacleLogicComponent obstacle : obstacles)
            if (obstacle.collide(bird))
                obstacleCollision(obstacle);
    }
    private void obstacleCollision(ObstacleLogicComponent obstacle){
        notifyEvent(GameEventType.COLLISION);
        bird.acquireImmunity();
        if (gameSpeed>0.45)
            gameSpeed-=0.20;
        commandHandler.sendCommand(new ObstacleCollisionCommand(Objects.requireNonNull(getEntity(obstacle))));
        if(obstacle.destroyOnHit())
            removeObstacle(obstacle);

    }

    private void checkCoinCollisions(){
        for(CoinLogicComponent coin: coins){
            if(coin.collide(bird)){
                commandHandler.sendCommand(new CoinCollisionCommand(Objects.requireNonNull(getEntity(coin))));
                removeCoin(coin);
                player.addCoin();
            }
        }
    }

    private void updateEntities(int delta){
        for(Entity entity: entities)
            entity.update(delta);
    }

    private void removeCoin(LogicComponent logic){
        coins.removeIf(obstacleLogicComponent -> obstacleLogicComponent == logic);
        removeEntity(logic);

    }

    private void removeObstacle(LogicComponent logic){
        obstacles.removeIf(obstacleLogicComponent -> obstacleLogicComponent == logic);
        removeEntity(logic);
    }

    private Entity getEntity(LogicComponent logic){
        for (Entity entity: entities)
            if(entity.getLogicComponent()== logic)
                return entity;
        return null;
    }

    private void removeEntity(LogicComponent logic){
        entities.remove(getEntity(logic));
    }

    private void renderEntities(){
        for(Entity entity: entities)
            entity.render();
    }
    private void gameover(){
        notifyEvent(GameEventType.GAMEOVER);
    }
    @Override
    public void onCoinGenerated(Entity coin) {
        addEntity(coin);
        coins.add((CoinLogicComponent) coin.getLogicComponent());
        commandHandler.sendCommand(new CoinGeneratedCommand(coin));

    }

    @Override
    public void onObstacleGenerated(Entity obstacle) {
        addEntity(obstacle);
        obstacles.add((ObstacleLogicComponent) obstacle.getLogicComponent());
        commandHandler.sendCommand(new ObstacleGeneratedCommand(obstacle));
    }
}
