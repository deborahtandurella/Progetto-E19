package game.multiplayer;

import flappyEntities.Entity;
import flappyEntities.EntityFactory;
import flappyEntities.logic.LogicComponent;
import flappyEntities.logic.ScrollingElement;
import flappyEntities.logic.bird.BirdLogicComponent;
import game.DifficultySettings;
import game.gameEvents.GameEventDispatcher;
import game.gameEvents.GameEventType;
import game.player.MultiModePlayer;
import graphics.Canvas;
import graphics.HUD.MultiplayerHud;
import graphics.HUD.PlayerHud;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import utilities.FontUtility;

import java.util.concurrent.CopyOnWriteArrayList;

import static game.GameConstants.BIRD_WIDTH;

/**
 * Gestore della partita del giocatore avversario. Esegue l'update e il render delle sue componenti ma non ne gestisce le interazioni.
 * Mette a disposizione funzionalità per modificare lo stato della partita in modo che rifletta quella remota dell'avversario
 */
public class OnlineRemoteGame extends GameEventDispatcher implements OnlineGame{
    private CopyOnWriteArrayList<Entity> entities;
    private CopyOnWriteArrayList<ScrollingElement> scrollingElements;
    private BirdLogicComponent bird;
    private MultiModePlayer player;
    private Canvas canvas;
    private double gameSpeed;
    private Image background;
    private PlayerHud hud;
    private long startTime;
    private boolean gameOver;
    private UnicodeFont font;

    public OnlineRemoteGame(Canvas canvas, DifficultySettings settings, MultiModePlayer player) {
        this.canvas = canvas;
        this.gameSpeed = settings.getSpeed();
        font= FontUtility.makeFont((int) (canvas.getScreen().getWidth()*0.04));
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

    /**
     * Esegue l'update delle componenti
     * @param i l'intervallo di tempo dall'ultimo frame
     */
    public void update(int i){
        if(!gameOver){
            double delta= (double)i*gameSpeed;
            updateEntities(delta);
            checkOutOfBounds();
        }
    }

    /**
     * Renderizza le componenti
     */
    public void render(){
        canvas.drawImage(background, 0, 0, 1, 1);
        renderEntities();
        hud.render();
        canvas.drawStringCentered(player.getPlayerInfo().getName(),font, (float)bird.getX()+(float)BIRD_WIDTH/2f, (float)bird.getY()-0.02f);
    }

    /**
     * Esegue un salto
     */
    public void playerJump(){
        bird.jump();
        notifyEvent(GameEventType.JUMP);
    }
    private void checkOutOfBounds(){
        for (ScrollingElement element: scrollingElements){
            if (element.outOfHorizontalBounds()){
                scrollingElements.remove(element);
                removeEntity(element);
            }
        }
    }

    /**
     * Rimuove uno ScrollingElement
     * @param toRemove lo scrollingElement che si desidera rimuovere
     */
    public void removeScrollingElement(ScrollingElement toRemove){
        scrollingElements.remove(toRemove);
        removeEntity(toRemove);
    }

    /**
     * Aggiunge uno ScrollingElement
     * @param scrollingElement lo ScrollingElement da aggiungere
     */
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

    /**
     *  Segnala il gameover
     */
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

    /**
     *
     */
    public void obstacleCollision(){
        notifyEvent(GameEventType.COLLISION);
        bird.acquireImmunity();

    }

    /**
     * Imposta la velocità della partita
     * @param speed la velocità
     */
    public void setSpeed(double speed){
        gameSpeed=speed;
    }
    /**
     * Aumenta il punteggio
     */
    public void increaseScore(){
        player.addScore();
    }

    /**
     *  Aumenta le monete
     */
    public void increaseCoins(){
        player.addCoin();
    }

    /**
     * Restituisce un'Entity in base al suo ID
     * @param ID ID dell'Entiy
     * @return Entity avente quell'ID
     */
    public Entity getEntityByID(int ID) {
        for(Entity entity: entities)
            if (entity.getID()==ID)
                return entity;
        return null;
    }
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @return true se la partita è finita
     */
    public boolean isOver() {
        return gameOver;
    }

    public MultiModePlayer getPlayer() {
        return player;
    }
}

