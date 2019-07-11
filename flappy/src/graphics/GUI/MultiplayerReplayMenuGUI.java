package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;
import states.MultiplayerReplayMenu;

public class MultiplayerReplayMenuGUI extends AbstractMenuGUI {

    private MouseOverArea backButton;
    private MultiplayerReplayMenu state;

    public MultiplayerReplayMenuGUI(GameContainer container, Screen screen, MultiplayerReplayMenu state) throws SlickException {
        super(container, screen);
        this.state = state;
        setBackground();

        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        Image backImage = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        backButton = new MouseOverArea(container, backImage, container.getWidth()-buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);
        addButton(backButton);
    }

    @Override
    public void render() throws SlickException {
        renderBackground();
        renderButtons();
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == backButton){
            state.backToMenu();
        }

    }
}
