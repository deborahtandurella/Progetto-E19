package states;

import graphics.GUI.DifficultyMenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import org.newdawn.slick.*;

import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class DifficultyMenu extends BasicGameState{
    private static final int ID = 1;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private static float gameSpeed;
    private DifficultyMenuGUI gui;
    private Screen screen;



    private SpriteDrawer drawer;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        drawer = new SpriteDrawer(new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0));
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        gui = new DifficultyMenuGUI(container,screen,this);
        gameSpeed=0.7f;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        gui.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }

    public void easy(){
        try {
            stateBasedGame.getState(2).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
    }

    public void medium(){
        try {
            stateBasedGame.getState(2).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
    }

    public void hard(){
        try {
            stateBasedGame.getState(2).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
    }
}
