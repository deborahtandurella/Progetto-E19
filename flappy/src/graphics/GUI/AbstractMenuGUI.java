package graphics.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.ComponentListener;



public abstract class AbstractMenuGUI implements ComponentListener {
    protected GameContainer container;
    public AbstractMenuGUI(GameContainer container) {
        this.container = container;
    }
    public abstract void render();
}
