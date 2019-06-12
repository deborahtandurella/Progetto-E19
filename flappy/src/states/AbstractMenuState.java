package states;

import graphics.GUI.AbstractMenuGUI;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

public abstract class AbstractMenuState extends BasicGameState {
   private AbstractMenuGUI gui;
    public void reload() throws SlickException {
        gui.setBackground();
    }
    protected AbstractMenuGUI getGui(){
        return gui;
    }
    protected void setGui(AbstractMenuGUI gui){
        this.gui = gui;
    }
    protected void renderGui() throws SlickException {
        gui.render();
    }
}
