package states.deprecati;

import GameScore.ScoreBoard;
import Main.GiocoAStati;
import gameMusic.MusicPlayer;
import graphics.Screen;
import graphics.SpriteDrawer;
import logic.SinglePlayer.Result;
import logic.SinglePlayer.SingleModePlayer;
import logic.gameElements.*;
import logic.player.PlayerInfo;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.Font;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static logic.gameConstants.GameConstants.*;

public class Singleplayer extends BasicGameState {
    private static final int ID = 3;
    private GameContainer container;
    private Bird bird;
    private SingleModePlayer player;
    private List<Pipe> pipes;
    private List<Heart> hearts;
    private List<Rocket> rockets;
    private SpriteDrawer spriteDrawer;
    private Random random;
    private int pipeDecider;
    private int lifeSpawner;
    private int rocketSpawner;
    private MusicPlayer musicPlayer;
    private boolean immunity;
    private long immunityTimer;
    private long newRecordTimer;
    private TrueTypeFont font;
    private Result result;
    private boolean first;
    private Screen screen;
    private ScoreBoard scoreboard;
    private UnicodeFont uniFontMessage;
    private String newRecordString;
    private boolean newRecord;
    private boolean initialized;


    @Override
    public int getID() {
        return ID;
    }

    public Singleplayer(Result result){
        super();
        this.result = result;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.scoreboard = ((GiocoAStati) stateBasedGame).getScoreBoard();
        this.container= gameContainer;
        player = new SingleModePlayer(new PlayerInfo("ciao"));
        immunity = false;
        first = true;
        newRecord = false;
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

        Font font = new Font("Comic Sans MS", Font.BOLD, 4*container.getWidth()/100);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.red));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        newRecordString = "NEW RECORD!";
}

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        spriteDrawer.drawBackgroundSingle(graphics);
        if(!initialized) {
            setClip(graphics);
            initialized= true;
        }

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

        if(newRecord){
            uniFontMessage.drawString(container.getWidth() / 2f - uniFontMessage.getWidth(newRecordString) / 2f, 23 * container.getHeight() / 100f, newRecordString);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i){

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
            if(rocket.collide(bird)&& !immunity){
                musicPlayer.explosionMusic();
                player.loseHeart();
                rockets.remove(rocket);
                if(player.getHearts()==0){
                    musicPlayer.gameOverMusic();

                    scoreboard.compareScore(result);

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
            if(rocket.getX()<0- ROCKET_WIDTH){
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
                player.addScore();
                pipe.setPassed(true);

            }

            if(pipe.collide(bird)&& !immunity){
                player.loseHeart();
                if(player.getHearts()==0){
                    musicPlayer.gameOverMusic();

                    scoreboard.compareScore(result);

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




        if(player.getScore() > scoreboard.getCurrentRecord() && first ){
            newRecord = true;
            first = false;
            newRecordTimer = System.currentTimeMillis();
        }
        if((System.currentTimeMillis()-newRecordTimer) > 2000){
            newRecord = false;
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

    private void setClip(Graphics graphics){
        graphics.setWorldClip(screen.getOffsetX(), screen.getOffsetY(), screen.getWidth(), screen.getHeight());
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        setClip(container.getGraphics());
    }
}
