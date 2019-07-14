package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.util.ArrayList;

public abstract class AbstractMenuGUI implements ComponentListener {
    private GameContainer container;
    private Screen screen;
    private ArrayList<MouseOverArea> buttons;
    private Image background;

   public AbstractMenuGUI(GameContainer container, Screen screen) throws SlickException {
        this.container = container;
        this.screen = screen;
        this.buttons = new ArrayList<>();
        setBackground();
    }

    public void render() {
       renderBackground();
       renderButtons();
    }

    public GameContainer getContainer() {
        return container;
    }

    void addButton(MouseOverArea button) {
        buttons.add(button);
    }

    void renderButtons() {
        for (MouseOverArea button : buttons) {
            button.render(container, container.getGraphics());
        }
    }

    public void setBackground() throws SlickException {
        background = new Image(PathHandler.getInstance().getPath(ResourcePack.SPRITES, Resource.BACKGROUND)).getScaledCopy(screen.getWidth(),screen.getHeight());

    }

    protected void renderBackground(){
        background.draw(screen.getOffsetX(), screen.getOffsetY());
    }
    public void reload(){}
}


