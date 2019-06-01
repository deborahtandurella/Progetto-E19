package states;

import GameScore.ScoreBoard;
import gameMusic.MusicPlayer;
import graphics.Screen;
import graphics.SpriteDrawer;
import logic.SinglePlayer.Player;
import logic.SinglePlayer.Record;
import logic.gameElements.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static logic.gameConstants.GameConstants.*;

public class Singleplayer extends BasicGameState {
    private static final int ID = 3;
    private GameContainer container;
    private Bird bird;
    private Player player;
    private List<Pipe> pipes;
    private List<Heart> hearts;
    private List<Rocket> rockets;
    private double gameSpeed;
    private SpriteDrawer spriteDrawer;
    private Random random;
    private int pipeDecider;
    private int lifeSpawner;
    private int rocketSpawner;
    private MusicPlayer musicPlayer;
    private boolean immunity;
    private long immunityTimer;
    private TrueTypeFont font;
    //private int score;
    private Record record;
    private Screen screen;
    private ScoreBoard scoreboard;

    @Override
    public int getID() {
        return ID;
    }

    public Singleplayer(Record record, ScoreBoard scoreBoard){
        super();
        this.record = record;
        this.scoreboard = scoreBoard;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        player = new Player();
        immunity = false;
        random = new Random();
        pipeDecider = random.nextInt(11);
        lifeSpawner = random.nextInt(16);
        rocketSpawner = random.nextInt(16);
        pipes= new CopyOnWriteArrayList<>();
        hearts= new CopyOnWriteArrayList<>();
        rockets= new CopyOnWriteArrayList<>();
        bird= new Bird(0.2, 0.5);
        pipes.add(new Pipe(1, 0.5, PIPE_SPEED));
        pipes.add(new Pipe( 1.5 + PIPE_WIDTH/2, 0.5, PIPE_SPEED));
        musicPlayer = new MusicPlayer();
        screen= new Screen(gameContainer.getWidth()/2,
                gameContainer.getHeight(),
                gameContainer.getWidth()/4,
                0);
        spriteDrawer = new SpriteDrawer(screen);
        java.awt.Font font1= new java.awt.Font("Verdana", java.awt.Font.BOLD, 32);
        font= new TrueTypeFont(font1, true);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        spriteDrawer.drawBackgroundSingle(graphics);
        setClip(graphics);

        spriteDrawer.drawBird((float) bird.getX(), (float) bird.getY(), graphics, bird.getSpeedY());
        for(Pipe pipe : pipes) {
            spriteDrawer.drawPipe((float) pipe.getX(), (float) pipe.getY(), graphics);
        }
        for(Heart heart : hearts) {
            spriteDrawer.drawHeart((float) heart.getX(), (float) heart.getY(), graphics);
        }
        for(Rocket rocket : rockets) {
            spriteDrawer.drawRocket((float) rocket.getX(), (float) rocket.getY(), graphics);
        }
        spriteDrawer.drawLives(player,graphics);
        font.drawString(screen.getWidth()/2f + screen.getOffsetX()- font.getWidth(String.valueOf(player.getScore()))/2f,screen.getHeight()/3f,String.valueOf(player.getScore()));
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
        for(Rocket rocket:rockets){
            rocket.update(i);
            if(rocket.collide(bird)&&immunity == false){
                musicPlayer.explosionMusic();
                player.loseHeart();
                rockets.remove(rocket);
                if(player.getHearts()==0){
                    musicPlayer.gameOverMusic();
                    record.setRecord(player);
                    try {
                        scoreboard.compareScore(record);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        stateBasedGame.getState(4).init(container,stateBasedGame);
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }
                    stateBasedGame.enterState(4, new FadeOutTransition(), new FadeInTransition());
                }


                immunity = true;
                spriteDrawer.setBirdAlpha(0.5f);
                immunityTimer = System.currentTimeMillis();
            }
            if(rocket.getX()<0-ROCKET_SIZE){
                rockets.remove(rocket);
            }
        }
        if(System.currentTimeMillis()-immunityTimer>3000){
            immunity = false;
            spriteDrawer.setBirdAlpha(1f);
        }
        for(Pipe pipe : pipes) {
            pipe.update(i);
            if(pipe.getX()<bird.getX()&&!pipe.isPassed()){
                //score++;
                player.addScore();
                pipe.setPassed(true);

            }

            if(pipe.collide(bird)&&immunity == false){
                player.loseHeart();
                if(player.getHearts()==0){
                    musicPlayer.gameOverMusic();
                    record.setRecord(player);
                    try {
                        scoreboard.compareScore(record);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        stateBasedGame.getState(4).init(container,stateBasedGame);
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }
                    stateBasedGame.enterState(4, new FadeOutTransition(), new FadeInTransition());
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
                rocketSpawner = random.nextInt(16);
                if(rocketSpawner>10){
                    rockets.add(new Rocket(1+2*PIPE_WIDTH, 0.25 + (new Random()).nextFloat() * 0.5, PIPE_SPEED*1.5));
                    rocketSpawner = 0;
                }
                if(pipeDecider>8){
                    pipes.add(new MovingPipe(1,0.25 + (new Random()).nextFloat() * 0.5, PIPE_SPEED,PIPE_VERTICAL_SPEED));
                    pipeDecider=0;
                }else{
                    pipes.add(new Pipe(1,0.25 + (new Random()).nextFloat() * 0.5, PIPE_SPEED));

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

    public void setClip(Graphics graphics){
        graphics.setWorldClip(screen.getOffsetX(), screen.getOffsetY(), screen.getWidth(), screen.getHeight());
    }


}
