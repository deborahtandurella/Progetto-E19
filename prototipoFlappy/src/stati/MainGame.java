package stati;

import elementi.Bird;
import elementi.EnemyBird;
import elementi.Pipe;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends BasicGameState {
    public static final int ID = 1;
    private GameContainer container;
    private Bird bird;
    private EnemyBird enemyBird;
    private Image background;
    private ArrayList<Pipe> pipes;
    private int score;
    private TrueTypeFont font;
    private Music flap;
    private Music gameOverTheme;
    private float gameSpeed;
//    private int angle;

    @Override
    public int getID() {
        return MainGame.ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        pipes= new ArrayList<>();
        background= new Image("res/bg.jpeg");
        bird= new Bird(container);
        enemyBird= new EnemyBird(container);
        pipes.add(new Pipe(container, container.getWidth(), container.getHeight()/2f));
        pipes.add(new Pipe(container, container.getWidth()*3f/2 + Pipe.WIDTH_PROPORION*container.getWidth()/2, container.getHeight()/2f));
        java.awt.Font font1= new java.awt.Font("Verdana", java.awt.Font.BOLD, 32);
        font= new TrueTypeFont(font1, true);
        score=0;
        flap = new Music("res/flap.ogg");
        gameOverTheme = new Music("res/gameOver.ogg");
        gameSpeed = DifficultyMenu.getGameSpeed();
//        angle=0;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
//        graphics.rotate(gameContainer.getWidth()/2f, gameContainer.getHeight()/2f, angle);

        background.draw(0,0, container.getWidth(), container.getHeight());
        bird.render(gameContainer, graphics);
        enemyBird.render(gameContainer, graphics);
        for(Pipe pipe: pipes)
            pipe.render(gameContainer, graphics);
        font.drawString(200,200,String.valueOf(score));

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        i*=gameSpeed;
        bird.update(gameContainer, i);
        enemyBird. update(gameContainer, i);
        for(Pipe pipe: pipes){
            pipe.update(gameContainer, i);
            if(pipe.getX()<bird.getX()&&!pipe.isPassed()){
                score++;
                pipe.setPassed(true);

            }
            if (pipe.outOfBounds()){
                pipe.regenerate(randomHeight());
                pipe.setPassed(false);

            }
            if (pipe.collides(bird.getShape())){
                gameOverTheme.play(1.0f,0.4f);
                stateBasedGame.enterState(2);
            }
        }
//        angle+=i*0.3;
    }

    private float randomHeight(){
        return container.getHeight()/4f + (new Random()).nextFloat()*(container.getHeight())/2f;
    }

    public void keyPressed(int key, char c){
        if (key== Input.KEY_SPACE){
            flap.play(1.0f,1.0f);
            bird.jump();
        }
        if (key== Input.KEY_LCONTROL){
            enemyBird.jump();
        }
    }

}
