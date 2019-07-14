package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import states.DifficultyMenu;

public class DifficultyMenuGUI extends AbstractMenuGUI {
    private DifficultyMenu state;
    private MouseOverArea easyButton;
    private MouseOverArea mediumButton;
    private MouseOverArea hardButton;
    private MouseOverArea backButton;

    public DifficultyMenuGUI(GameContainer container, Screen screen, DifficultyMenu state) throws SlickException {
        super(container, screen);
        this.state = state;

        int buttonHeight = container.getHeight()/7;
        int buttonWidth = container.getWidth()/3;

        Image easy = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.EASYBUTTON)).getScaledCopy(buttonWidth,8*buttonHeight/10);
        easyButton = new MouseOverArea(container, easy, container.getWidth()/2 - buttonWidth/2, container.getHeight() - 60*buttonHeight/10, buttonWidth, buttonHeight, this);

        Image medium = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.MEDIUMBUTTON)).getScaledCopy(buttonWidth, 8*buttonHeight/10);
        mediumButton = new MouseOverArea(container, medium, (container.getWidth()-buttonWidth)/2, container.getHeight() - 50*buttonHeight/10, buttonWidth, buttonHeight, this);

        Image hard = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.HARDBUTTON)).getScaledCopy(buttonWidth,8*buttonHeight/10);
        hardButton = new MouseOverArea(container, hard, (container.getWidth()-buttonWidth)/2, container.getHeight() - 40*buttonHeight/10, buttonWidth, buttonHeight, this);

        Image back = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BACKTOMENUBUTTON)).getScaledCopy(12*buttonWidth/10, 8*buttonHeight/10);
        backButton = new MouseOverArea(container, back, (container.getWidth()-buttonWidth)/2, container.getHeight() - 20*buttonHeight/10, buttonWidth, buttonHeight, this);

        addButton(easyButton);
        addButton(mediumButton);
        addButton(hardButton);
        addButton(backButton);

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == easyButton){
            state.startEasyGame();
        }
        if(source == mediumButton){
            state.startNormalGame();
        }
        if(source == hardButton){
            state.startHardGame();
        }
        if(source == backButton){
            state.back();
        }
    }
}
