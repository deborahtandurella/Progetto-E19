package states.menu;

import graphics.GUI.AbstractMenuGUI;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.FlappyState;

/**
 *  tutti gli stati del package menu estendono AbstractMenuState, i metodi qui implementati permettono di gestire
 *  una AbstractMenuGUI comune in modo da poter gestire tutte le GUI allo stesso modo.
 */

public abstract class AbstractMenuState extends FlappyState {
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
