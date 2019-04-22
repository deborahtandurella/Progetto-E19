package states;

import graphics.SpriteDrawer;
import logic.SinglePlayer.Player;
import logic.gameConstants.GameConstants;
import logic.gameElements.Bird;
import logic.gameConstants.GameConstants.*;
import logic.gameElements.Heart;
import logic.gameElements.Pipe;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static logic.gameConstants.GameConstants.PIPE_SPEED;
import static logic.gameConstants.GameConstants.PIPE_WIDTH;

public class Singleplayer extends BasicGameState {
    private static final int ID = 2;
    private GameContainer container;
    private Bird bird;
    private Heart heart;
    private Image background;
    private int score;
    private Player player;
    private List<Pipe> pipes;
    private TrueTypeFont font;
    private Music flap;
    private Music gameOverTheme;
    private double gameSpeed;
    private SpriteDrawer spriteDrawer;



    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        pipes= new CopyOnWriteArrayList<Pipe>();
        bird= new Bird(0.2, 0.5);
        pipes.add(new Pipe(1, 0.5, PIPE_SPEED));
        pipes.add(new Pipe( 1.5 + PIPE_WIDTH/2, 0.5, PIPE_SPEED));
        java.awt.Font font1= new java.awt.Font("Verdana", java.awt.Font.BOLD, 32);
        font= new TrueTypeFont(font1, true);
        score=0;
        spriteDrawer = new SpriteDrawer(gameContainer.getWidth()/2, gameContainer.getHeight(), gameContainer.getWidth()/4);
        //gameSpeed = DifficultyMenu.getGameSpeed();
        //mancano gli heart da far spawnare, gameSpeed da definire (dev'essere passata dal gioco(?))
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        spriteDrawer.drawBackgroundSingle(graphics);
        spriteDrawer.drawBird((float) bird.getX(), (float) bird.getY(), graphics);
        for(Pipe pipe : pipes)
            spriteDrawer.drawPipe((float) pipe.getX(), (float) pipe.getY(), graphics);
        container.getGraphics().setWorldClip(container.getWidth()/4f, 0, container.getWidth()/2f, container.getHeight());

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        bird.update(i);
        for(Pipe pipe : pipes) {
            pipe.update(i);

            if(pipe.collide(bird))
                System.out.println("COLLISIONE!");
            if (pipe.getX()<0- PIPE_WIDTH) {
                pipes.remove(pipe);
                pipes.add(new Pipe(1, 0.25 + (new Random()).nextFloat() * 0.5, PIPE_SPEED));
            }
        }




    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_SPACE) {
            bird.jump();

        }
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }
}
