package states.menu;

import Main.FlappyStateGame;
import graphics.GUI.ScoreBoardMenuGUI;
import graphics.GUI.SingleplayerReplayMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import scoreboard.ScoreBoard;

/**
 * Stato post-partita Singleplayer, qui viene mostrata la classifica e viene data all'utente la possibilità
 * di tornare al menu o iniziare una nuova partita con la medesima difficoltà della partita terminata
 */

public class SingleplayerReplayMenu extends AbstractMenuState implements ScoreBoardUsingMenu {
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private ScoreBoardMenuGUI scoregui;
    private ScoreBoard scoreBoard;
    private boolean newRecord;

    public SingleplayerReplayMenu( ){

    }
    @Override
    public int getID() {
        return FlappyStateGame.SINGLE_REPLAY_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        scoreBoard= ((FlappyStateGame)stateBasedGame).getScoreBoard();
        this.container = gameContainer;
        this.stateBasedGame = stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new SingleplayerReplayMenuGUI(gameContainer, screen, this));
        scoregui = new ScoreBoardMenuGUI(gameContainer, screen, this);
        container.getGraphics().clearWorldClip();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        scoregui.reload();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        super.leave(container, game);
        newRecord=false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.clearWorldClip();
        scoregui.render();
        renderGui();
    }

   public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }

    @Override
    public boolean hasNewRecord() {
        return newRecord;
    }

    public void rematch(){
        try {
            stateBasedGame.getState(FlappyStateGame.SINGLEPLAYER).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(FlappyStateGame.SINGLEPLAYER,new FadeOutTransition(),new FadeInTransition());
    }
    public void noRematch(){
        try {
            stateBasedGame.getState(FlappyStateGame.GENERAL_MENU).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(FlappyStateGame.GENERAL_MENU,new FadeOutTransition(),new FadeInTransition());

    }

    public void newRecord() {
        newRecord=true;
    }
}
