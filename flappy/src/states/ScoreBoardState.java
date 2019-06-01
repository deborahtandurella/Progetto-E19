package states;

import GameScore.ScoreBoard;
import graphics.GUI.ScoreBoardMenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import logic.SinglePlayer.Record;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.test.ScoreInterface;

public class ScoreBoardState extends BasicGameState implements ScoreInterface {
    private static final int ID = 9;
    private StateBasedGame stateBasedGame;
    private GameContainer container;
    private ScoreBoard scoreBoard;
    private Record record;

    @Override
    public int getID() {
        return ID;
    }

    public ScoreBoardState(Record record){
        super();
        this.record = record;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        this.container = container;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }
    }

