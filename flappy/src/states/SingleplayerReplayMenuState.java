package states;

import GameScore.ScoreBoard;
import graphics.GUI.ScoreBoardMenuGUI;
import graphics.GUI.SingleplayerReplayMenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.test.ScoreInterface;

public class SingleplayerReplayMenuState extends BasicGameState implements ScoreInterface {
    private static final int ID = 4;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private SpriteDrawer drawer;
    private SingleplayerReplayMenuGUI gui;
    private ScoreBoardMenuGUI scoregui;
    private Screen screen;
    private ScoreBoard scoreBoard;

    public SingleplayerReplayMenuState(ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
    }
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container = gameContainer;
        this.stateBasedGame = stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        drawer = new SpriteDrawer(screen);
        gui= new SingleplayerReplayMenuGUI(gameContainer, screen, this);
        scoregui = new ScoreBoardMenuGUI(gameContainer, screen, this);
        container.getGraphics().clearWorldClip();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.clearWorldClip();
        drawer.drawBackgroundSingle(graphics);
        gui.render();
        scoregui.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException{
    }

   public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }
    public void rematch(){
        try {
            stateBasedGame.getState(3).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(3,new FadeOutTransition(),new FadeInTransition());
    }
    public void noRematch(){
     /*   try {
            stateBasedGame.getState(1).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }*/
        stateBasedGame.enterState(1,new FadeOutTransition(),new FadeInTransition());

    }

}
