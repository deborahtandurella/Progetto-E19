package states;

import GameScore.ScoreBoard;
import Main.GiocoAStati;
import graphics.GUI.ScoreBoardButtons;
import graphics.GUI.ScoreBoardMenuGUI;
import graphics.Screen;
import logic.SinglePlayer.Result;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.test.ScoreInterface;

import java.io.IOException;

public class ScoreBoardState extends AbstractMenuState implements ScoreInterface {
    private static final int ID = 9;
    private StateBasedGame stateBasedGame;
    public Screen screen;
    private GameContainer container;
    private ScoreBoard scoreBoard;
    private Result result;
    private ScoreBoardButtons scoreBoardButtons;

    @Override
    public int getID() {
        return ID;
    }

    public ScoreBoardState(Result result){
        super();
        this.result = result;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        this.container = gameContainer;
        this.scoreBoard = ((GiocoAStati) stateBasedGame).getScoreBoard();
        screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0,0);
        scoreBoardButtons = new ScoreBoardButtons(gameContainer, screen, this);
        setGui(new ScoreBoardMenuGUI(gameContainer, screen, this));
        container.getGraphics().clearWorldClip();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        getGui().reload();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.clearWorldClip();
        renderGui();
        scoreBoardButtons.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
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
        stateBasedGame.enterState(1,new FadeOutTransition(),new FadeInTransition());
    }

    public void deleteLeaderBoard() throws IOException, SlickException {
        scoreBoard.deleteScoreBoard();
        setGui(new ScoreBoardMenuGUI(container, screen, this));
    }

}