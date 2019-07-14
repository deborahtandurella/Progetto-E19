package game;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.LogicComponent;
import flappyEntities.logic.ScrollingElement;
import flappyEntities.logic.bird.BirdLogicComponent;
import game.gameEvents.GameEventDispatcher;
import game.gameEvents.GameEventType;
import graphics.Canvas;
import graphics.HUD.Hud;
import graphics.HUD.MultiplayerHud;
import logic.player.MultiModePlayer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.util.concurrent.CopyOnWriteArrayList;

public class RemoteGame extends GameEventDispatcher implements OnlineGame{
    private CopyOnWriteArrayList<Entity> entities;
    private CopyOnWriteArrayList<ScrollingElement> scrollingElements;
    private BirdLogicComponent bird;
    private MultiModePlayer player;
    private Canvas canvas;
    private double gameSpeed;
    private Image background;
    private Hud hud;
    private long startTime;
    private boolean gameOver;

    public RemoteGame(Canvas canvas, DifficultySettings settings, MultiModePlayer player) {
        this.canvas = canvas;
        this.gameSpeed = settings.getSpeed();
        entities = new CopyOnWriteArrayList<>();
        scrollingElements = new CopyOnWriteArrayList<>();
        Entity birdEntity = EntityFactory.makeBird(0.2, 0.5,canvas);
        entities.add(birdEntity);
        bird = (BirdLogicComponent) birdEntity.getLogicComponent();
        startTime= System.currentTimeMillis();
        this.player=player;
        try {
            hud = new MultiplayerHud(player, canvas);
            background = new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.BACKGROUND));
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
    public void update(int i){
        if(!gameOver){
            double delta= (double)i*gameSpeed;
            updateEntities(delta);
            checkOutOfBounds();
        }
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
        for (ScrollingElement element: scrollingElements){
            if (element.outOfBounds()){
                scrollingElements.remove(element);
                removeEntity(element);
            }
        }
    }
    public void removeScrollingElement(ScrollingElement toRemove){
        scrollingElements.remove(toRemove);
        removeEntity(toRemove);
    }
    public void addScrollingElement(Entity scrollingElement){
        scrollingElements.add((ScrollingElement)scrollingElement.getLogicComponent());
        entities.add(scrollingElement);
    }
    private void updateEntities(double delta){
        for(Entity entity: entities){
            entity.update(delta);
        }
    }

    private void removeEntity(LogicComponent logic){
        entities.removeIf(entity -> entity.getLogicComponent() == logic);
    }
    private void renderEntities(){
        for(Entity entity: entities)
            entity.render();
    }
    public void gameOver(){
        gameOver=true;
        notifyEvent(GameEventType.GAMEOVER);
    }

    @Override
    public Entity getEntity(LogicComponent logicComponent) {
        for(Entity entity: entities){
            if (entity.getLogicComponent()==logicComponent)
                return entity;
        }
        return null;
    }

    public BirdLogicComponent getBird() {
        return bird;
    }

    @Override
    public void setBird(BirdLogicComponent bird) {
        this.bird=bird;
    }

    @Override
    public long getTimeLeft() {
        return System.currentTimeMillis()-startTime;
    }

    public void obstacleCollision(){
        notifyEvent(GameEventType.COLLISION);
        bird.acquireImmunity();

    }
    public void setSpeed(double speed){
        gameSpeed=speed;
    }
    public void increaseScore(){
        player.addScore();
    }
    public void increaseCoins(){
        player.addCoin();
    }
    public Entity getEntityByID(int ID) {
        for(Entity entity: entities)
            if (entity.getID()==ID)
                return entity;
        return null;
    }
    public Canvas getCanvas() {
        return canvas;
    }

    public boolean isOver() {
        return gameOver;
    }

    public MultiModePlayer getPlayer() {
        return player;
    }
}

