package states;

import graphics.GUI.SingleplayerReplayMenuGUI;
import graphics.SpriteDrawer;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class SingleplayerReplayMenuState extends BasicGameState  {
    private static final int ID = 3;

    private GameContainer container;
    private Image gameOver;
    private Image yes;
    private Image no;
    private StateBasedGame stateBasedGame;
    private MouseOverArea yesButton;
    private MouseOverArea noButton;
    private SpriteDrawer drawer;
    private SingleplayerReplayMenuGUI gui;

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container = gameContainer;
        this.stateBasedGame = stateBasedGame;
        drawer = new SpriteDrawer(container.getWidth()/2,container.getHeight(),container.getWidth()/4);
        gui= new SingleplayerReplayMenuGUI(gameContainer, this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        gui.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException{
    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }
    public void rematch(){
        try {
            stateBasedGame.getState(2).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(2,new FadeOutTransition(),new FadeInTransition());
    }
    public void noRematch(){
        try {
            stateBasedGame.getState(0).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(0,new FadeOutTransition(),new FadeInTransition());

    }

}
