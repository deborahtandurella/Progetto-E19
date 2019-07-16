package states.menu;

import graphics.GUI.AbstractMenuGUI;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.FlappyGameState;

public abstract class AbstractMenuState extends FlappyGameState {
   private AbstractMenuGUI gui;
    void reloadTheme() throws SlickException {
        gui.setBackground();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }

    protected AbstractMenuGUI getGui(){
        return gui;
    }
    protected void setGui(AbstractMenuGUI gui){
        this.gui = gui;
    }
    void renderGui() throws SlickException {
        gui.render();
    }

}
