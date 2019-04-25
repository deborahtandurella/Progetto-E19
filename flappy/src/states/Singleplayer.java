package states;

import gameMusic.MusicPlayer;
import graphics.SpriteDrawer;
import logic.SinglePlayer.Player;
import logic.gameElements.Bird;
import logic.gameElements.Heart;
import logic.gameElements.MovingPipe;
import logic.gameElements.Pipe;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static logic.gameConstants.GameConstants.*;

public class Singleplayer extends BasicGameState {
    private static final int ID = 2;
    private GameContainer container;
    private Bird bird;
    private Heart heart;
    private Player player;
    private List<Pipe> pipes;
    private List<Heart> hearts;
    private double gameSpeed;
    private SpriteDrawer spriteDrawer;
    private Random random;
    private int pipeDecider;
    private int lifeSpawner;
    private MusicPlayer musicPlayer;
    private boolean immunity;
    private long immunityTimer;
    private TrueTypeFont font;
    private int score;
    private int c;
    int d;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        player = new Player();
        immunity = false;
        random = new Random();
        pipeDecider = random.nextInt(11);
        lifeSpawner = random.nextInt(16);
        pipes= new CopyOnWriteArrayList<>();
        hearts= new CopyOnWriteArrayList<>();
        bird= new Bird(0.2, 0.5);
        pipes.add(new Pipe(1, 0.5, PIPE_SPEED));
        pipes.add(new Pipe( 1.5 + PIPE_WIDTH/2, 0.5, PIPE_SPEED));
        musicPlayer = new MusicPlayer();
        spriteDrawer = new SpriteDrawer(gameContainer.getWidth()/2, gameContainer.getHeight(), gameContainer.getWidth()/4);
        java.awt.Font font1= new java.awt.Font("Verdana", java.awt.Font.BOLD, 32);
        font= new TrueTypeFont(font1, true);
        score=0;
        c = 2;
        d = 4;

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        spriteDrawer.drawBackgroundSingle(graphics);
        if(!immunity)
            spriteDrawer.setBirdAlpha(1);
        else {

            spriteDrawer.setBirdAlpha(0.1f*d);
        }
        spriteDrawer.drawBird((float) bird.getX(), (float) bird.getY(), graphics, bird.getSpeedY());
        for(Pipe pipe : pipes) {
            spriteDrawer.drawPipe((float) pipe.getX(), (float) pipe.getY(), graphics);
        }
        for(Heart heart : hearts) {
            spriteDrawer.drawHeart((float) heart.getX(), (float) heart.getY(), graphics);
        }
        spriteDrawer.drawLives(player,graphics);
        container.getGraphics().setWorldClip(container.getWidth()/4f, 0, container.getWidth()/2f, container.getHeight());
        font.drawString(gameContainer.getWidth()/2,200,String.valueOf(score));
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        bird.update(i);
        for(Heart heart:hearts){
            heart.update(i);
            if(heart.collide(bird)){
                player.addHeart();
                hearts.remove(heart);
            }
            if(heart.getX()<0-HEART_SIZE){
                hearts.remove(heart);
            }
        }
        if(System.currentTimeMillis()-immunityTimer>3000){
            immunity = false;
            spriteDrawer.setBirdAlpha(1f);
        }
        c += 2;
        if( c == 100){
            c = 2;
            d +=5;
            if(d >10)
                d = 5;
        }
        for(Pipe pipe : pipes) {
            pipe.update(i);
            if(pipe.getX()<bird.getX()&&!pipe.isPassed()){
                score++;
                pipe.setPassed(true);

            }

            if(pipe.collide(bird)&&immunity == false){
                player.loseHeart();
                if(player.getHearts()==0){
                    try {
                        stateBasedGame.getState(3).init(container,stateBasedGame);
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }
                    musicPlayer.gameOverMusic();
                    stateBasedGame.enterState(3, new FadeOutTransition(), new FadeInTransition());
                }
                immunity = true;
                spriteDrawer.setBirdAlpha(0.5f);
                immunityTimer = System.currentTimeMillis();
            }
            if (pipe.getX()<0- PIPE_WIDTH) {
                pipes.remove(pipe);
                random = new Random();
                pipeDecider = random.nextInt(11);
                lifeSpawner = random.nextInt(16);
                if(pipeDecider>8){
                    pipes.add(new MovingPipe(1,0.25 + (new Random()).nextFloat() * 0.5, PIPE_SPEED,PIPE_VERTICAL_SPEED));
                    pipeDecider=0;
                }else{
                    pipes.add(new Pipe(1, 0.25 + (new Random()).nextFloat() * 0.5, PIPE_SPEED));
                }
                if(lifeSpawner>13){
                    hearts.add(new Heart(1+2*PIPE_WIDTH, 0.25 + (new Random()).nextFloat() * 0.5, PIPE_SPEED));
                    lifeSpawner = 0;
                }
            }
        }




    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_SPACE) {
            musicPlayer.flapMusic();
            bird.jump();

        }
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }
}
