package states;

import graphics.GUI.ConnectionErrorGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ConnectionErrorState extends AbstractMenuState{
    private static final int ID = 19;
    private GameContainer container;
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return 19;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame= stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new ConnectionErrorGUI(gameContainer, screen,this));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

    public void backToMenu(){
        stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
    }
}
