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

public class Singleplayer extends BasicGameState {
    private static final int ID = 2;
    private GameContainer container;
    private Bird bird;
    private Heart heart;
    private Image background;
    private int score;
    private Player player;
    private ArrayList<Pipe> pipes;
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
        pipes= new ArrayList<>();
        bird= new Bird(0.2, 0.5);
        pipes.add(new Pipe(1, 0.5, gameSpeed));
        pipes.add(new Pipe( 1.5 + GameConstants.PIPE_WIDTH/2, 0.5, gameSpeed));
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
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }
}
