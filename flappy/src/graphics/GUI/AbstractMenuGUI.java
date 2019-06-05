package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import java.util.ArrayList;

public abstract class AbstractMenuGUI implements ComponentListener {
    private GameContainer container;
    private ArrayList<MouseOverArea> buttons;
    public AbstractMenuGUI(GameContainer container, Screen screen) {
        this.container = container;
        this.buttons = new ArrayList<>();
    }
    public abstract void render();
    public GameContainer getContainer() {
        return container;
    }
    protected void addButton(MouseOverArea button){
        buttons.add(button);
    }
    protected void renderButtons(){
        for(MouseOverArea button: buttons){
            button.render(container, container.getGraphics());
        }
    }
}

