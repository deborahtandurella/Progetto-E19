package states;

import GameScore.ScoreBoard;
import graphics.GUI.ScoreBoardButtons;
import graphics.GUI.ScoreBoardMenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import logic.SinglePlayer.Record;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.test.ScoreInterface;

import java.io.IOException;

public class ScoreBoardState extends BasicGameState implements ScoreInterface {
    private static final int ID = 9;
    private StateBasedGame stateBasedGame;
    public Screen screen;
    private GameContainer container;
    private ScoreBoard scoreBoard;
    private Record record;
    private SpriteDrawer drawer;
    private ScoreBoardButtons scoreBoardButtons;
    private ScoreBoardMenuGUI scoreBoardGUI;


    @Override
    public int getID() {
        return ID;
    }

    public ScoreBoardState(Record record, ScoreBoard scoreBoard){
        super();
        this.scoreBoard = scoreBoard;
        this.record = record;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        this.container = gameContainer;
        screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0,0);
        drawer = new SpriteDrawer(screen);
        scoreBoardButtons = new ScoreBoardButtons(gameContainer, this, screen);
        scoreBoardGUI = new ScoreBoardMenuGUI(gameContainer, this, screen);
        container.getGraphics().clearWorldClip();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.clearWorldClip();
        drawer.drawBackgroundSingle(graphics);
        scoreBoardGUI.render();
        scoreBoardButtons.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }

    public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }
    public void keyPressed(int key, char c){
        if(key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }


    public void backToMenu(){
        try {
            stateBasedGame.getState(1).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(1,new FadeOutTransition(),new FadeInTransition());

    }

    public void deleteLeaderBoard() throws IOException, SlickException {
        scoreBoard.deleteScoreBoard();
        scoreBoardGUI = new ScoreBoardMenuGUI(container, this, screen);


    }

}