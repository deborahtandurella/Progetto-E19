package states.menu;

import states.FlappyStateGame;
import graphics.GUI.ScoreBoardButtons;
import graphics.GUI.ScoreBoardGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import scoreboard.ScoreBoard;

/**
 * stato che si occupa di mostrare la classifica all'utente, da qui è possibile anche resettare la classifica
 */

public class ScoreBoardMenu extends AbstractMenuState implements ScoreBoardUsingMenu {
    private StateBasedGame stateBasedGame;
    public Screen screen;
    private GameContainer container;
    private ScoreBoard scoreBoard;
    private ScoreBoardButtons scoreBoardButtons;

    @Override
    public int getID() {
        return FlappyStateGame.SCORE_BOARD_MENU;
    }


    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        this.container = gameContainer;
        this.scoreBoard = ((FlappyStateGame) stateBasedGame).getScoreBoard();
        screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0,0);
        scoreBoardButtons = new ScoreBoardButtons(gameContainer, screen, this);
        setGui(new ScoreBoardGUI(gameContainer, screen, this));
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

    public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }

    @Override
    public boolean hasNewRecord() {
        return false;
    }

    public void backToMenu(){
        stateBasedGame.enterState(FlappyStateGame.GENERAL_MENU,new FadeOutTransition(),new FadeInTransition());
    }

    /**
     * deleteLeaderBoard() resetta la classifica e ripristina il relativo file
     */
    public void deleteLeaderBoard()  {
        scoreBoard.clear();
        try {
            setGui(new ScoreBoardGUI(container, screen, this));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


}