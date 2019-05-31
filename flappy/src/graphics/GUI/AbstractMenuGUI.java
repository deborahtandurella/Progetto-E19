package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.ComponentListener;

public abstract class AbstractMenuGUI implements ComponentListener {
    private GameContainer container;
    public AbstractMenuGUI(GameContainer container, Screen screen) {
        this.container = container;
    }
    public abstract void render();
    public GameContainer getContainer() {
        return container;
    }
}

