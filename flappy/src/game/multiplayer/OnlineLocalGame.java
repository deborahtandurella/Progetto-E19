package game.multiplayer;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.LogicComponent;
import flappyEntities.logic.bird.BirdLogicComponent;
import flappyEntities.logic.items.CoinLogicComponent;
import flappyEntities.logic.obstacles.ObstacleLogicComponent;
import game.DifficultySettings;
import game.gameEvents.GameEventDispatcher;
import game.gameEvents.GameEventType;
import game.itemGeneration.coin.CoinGenerator;
import game.itemGeneration.coin.CoinListener;
import game.itemGeneration.obstacle.ObstacleGenerator;
import game.itemGeneration.obstacle.ObstacleListener;
import game.multiplayer.powerUps.PowerUp;
import game.multiplayer.powerUps.PowerUpShop;
import game.multiplayer.powerUps.PowerUpType;
import game.player.MultiModePlayer;
import graphics.Canvas;
import graphics.HUD.MultiplayerHud;
import graphics.HUD.PlayerHud;
import network.CommandTransmitter;
import network.commands.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import static game.GameConstants.BIRD_WIDTH;

public class OnlineLocalGame extends GameEventDispatcher implements CoinListener, ObstacleListener, OnlineGame {
    private CopyOnWriteArrayList<Entity> entities;
    private CopyOnWriteArrayList<ObstacleLogicComponent> obstacles;
    private CopyOnWriteArrayList<CoinLogicComponent> coins;
    private BirdLogicComponent bird;
    private MultiModePlayer player;
    private Canvas canvas;
    private double gameSpeed;
    private ObstacleGenerator obstacleGenerator;
    private Image background;
    private PlayerHud hud;
    private CommandTransmitter transmitter;
    private int IDcount;
    private long startTime;
    private double maxSpeed= 2;
    private double minSpeed= 0.5;
    private boolean gameOver = false;


    public OnlineLocalGame(Canvas canvas, DifficultySettings settings, CommandTransmitter transmitter, MultiModePlayer player) {
        this.canvas = canvas;
        this.gameSpeed = settings.getSpeed();
        this.obstacleGenerator = settings.getObstacleGenerator().create(canvas);
        this.transmitter= transmitter;
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
        startTime=System.currentTimeMillis();
        try {
            hud= new MultiplayerHud(player, canvas);
            background = new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.BACKGROUND));
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
    @Override
    public long getTimeLeft(){
        return ONLINE_GAME_DURATION - (System.currentTimeMillis()-startTime);
    }
    public void update(int i){
        if(!gameOver) {
            double delta = (double)i * gameSpeed;
            updateEntities(delta);
            obstacleGenerator.update(delta);
            if (!bird.isImmune()) {
                checkCollisions();
                checkScore();
            }
            checkOutOfBounds();
            if(getTimeLeft()<=0)
                gameOver();
        }

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
        if (!bird.outOfBounds()){
            bird.jump();
            notifyEvent(GameEventType.JUMP);
            transmitter.sendCommand(new JumpCommand(bird.getX(), bird.getY()));
        }
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
    public void changeSpeed(double change){
        if (gameSpeed + change > maxSpeed ) {
            gameSpeed = maxSpeed;
        }
        else if (gameSpeed + change < minSpeed)
            gameSpeed=minSpeed;
        else
            gameSpeed+=change;
        transmitter.sendCommand(new SpeedChangedCommand(gameSpeed));
    }
    public BirdLogicComponent getBird() {
        return bird;
    }

    private void checkScore(){
        for(ObstacleLogicComponent obstacle: obstacles){
            if( ( !obstacle.isPassed() ) && (Math.abs(bird.getX()-obstacle.getX())<BIRD_WIDTH/4) ){
                obstacle.setPassed(true);
                increaseScore();
            }
        }
    }
    private void increaseScore(){
        player.addScore();
        changeSpeed(+0.012);
        transmitter.sendCommand(new IncreaseScoreCommand());
    }
    private void checkObstacleCollisions(){
        for(ObstacleLogicComponent obstacle : obstacles)
            if (obstacle.collide(bird))
                obstacleCollision(obstacle);
    }
    private void obstacleCollision(ObstacleLogicComponent obstacle){
        notifyEvent(GameEventType.COLLISION);
        bird.acquireImmunity();
        changeSpeed(-0.07);
        transmitter.sendCommand(new ObstacleCollisionCommand(Objects.requireNonNull(getEntity(obstacle))));
        if(obstacle.destroyOnHit())
            removeObstacle(obstacle);

    }

    private void checkCoinCollisions(){
        for(CoinLogicComponent coin: coins){
            if(coin.collide(bird)){
                transmitter.sendCommand(new CoinCollisionCommand(Objects.requireNonNull(getEntity(coin))));
                removeCoin(coin);
                player.addCoin();
            }
        }
    }

    public void setBird(BirdLogicComponent bird) {
        this.bird = bird;
    }

    private void updateEntities(double delta){
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

    public Entity getEntity(LogicComponent logic){
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
    private void gameOver(){
        gameOver=true;
        transmitter.sendCommand(new GameOverCommand());
        notifyEvent(GameEventType.GAMEOVER);
    }
    @Override
    public void onCoinGenerated(Entity coin) {
        addEntity(coin);
        coins.add((CoinLogicComponent) coin.getLogicComponent());
        transmitter.sendCommand(new CoinGeneratedCommand(coin));

    }
    public void powerUpUsed(PowerUpType powerUpType){
        PowerUp powerUp= PowerUpShop.buy(powerUpType, player);
        if (powerUp!=null)
            transmitter.sendCommand(new PowerUpCommand(powerUp));
    }
    public Canvas getCanvas() {
        return canvas;
    }
    public void powerUpReceived(PowerUp powerUp){
        if (powerUp.getAffectedGame()== PowerUp.REMOTE_GAME){
            transmitter.sendCommand(new PowerUpCommand(powerUp));
        }
    }
    public void changeSpeedLimits(double change){
        maxSpeed+=change;
        minSpeed+=change;
    }
    @Override
    public void onObstacleGenerated(Entity obstacle) {
        addEntity(obstacle);
        obstacles.add((ObstacleLogicComponent) obstacle.getLogicComponent());
        transmitter.sendCommand(new ObstacleGeneratedCommand(obstacle));
    }
    public boolean isOver(){
        return gameOver;
    }
    public MultiModePlayer getPlayer() {
        return player;
    }
}
