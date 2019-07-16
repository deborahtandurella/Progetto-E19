package states.menu;

import Main.GiocoAStati;
import graphics.GUI.ScoreBoardMenuGUI;
import graphics.GUI.SingleplayerReplayMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import scoreboard.ScoreBoard;
import scoreboard.ScoreBoardMenu;

public class SingleplayerReplayMenu extends AbstractMenuState implements ScoreBoardMenu {
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private ScoreBoardMenuGUI scoregui;
    private ScoreBoard scoreBoard;
    private boolean newRecord;

    public SingleplayerReplayMenu( ){

    }
    @Override
    public int getID() {
        return GiocoAStati.SINGLE_REPLAY_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        scoreBoard= ((GiocoAStati)stateBasedGame).getScoreBoard();
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

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i){
    }

   public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }

    @Override
    public boolean hasNewRecord() {
        return newRecord;
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }
    public void rematch(){
        try {
            stateBasedGame.getState(GiocoAStati.SINGLEPLAYER).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(GiocoAStati.SINGLEPLAYER,new FadeOutTransition(),new FadeInTransition());
    }
    public void noRematch(){
        try {
            stateBasedGame.getState(GiocoAStati.GENERAL_MENU).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(GiocoAStati.GENERAL_MENU,new FadeOutTransition(),new FadeInTransition());

    }

    public void newRecord() {
        newRecord=true;
    }
}
