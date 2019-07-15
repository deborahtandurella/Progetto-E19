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
import states.menu.GeneralMenu;

public class MenuGUI extends AbstractMenuGUI {
    private GeneralMenu state;
    private MouseOverArea singleButton;
    private MouseOverArea multiButton;
    private MouseOverArea customButton;
    private MouseOverArea leaderboardButton;

    public MenuGUI(GameContainer container, Screen screen, GeneralMenu state) throws SlickException {
        super(container, screen);
        this.state = state;

        int buttonHeight = container.getHeight()/6;
        int buttonWidth = container.getWidth()/3;

        Image single = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.SINGLEPLAYERBUTTON)).getScaledCopy(3 * buttonWidth / 2, 80 * buttonHeight / 100);
        singleButton = new MouseOverArea(container, single, 25 * container.getWidth() / 100, 50 * container.getHeight() / 100 , this);

        Image multi = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.MULTIPLAYERBUTTON)).getScaledCopy(3 * buttonWidth/2, 80 * buttonHeight / 100);
        multiButton = new MouseOverArea(container, multi, 25 * container.getWidth() / 100, 30 * container.getHeight() / 100, this);

        Image leaderBoard = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.LEADERBOARDSBUTTON)).getScaledCopy(buttonWidth / 2, buttonHeight);
        leaderboardButton = new MouseOverArea(container, leaderBoard, container.getWidth() / 2 - buttonWidth / 4, 10 * container.getHeight() / 100, buttonWidth / 2, buttonHeight, this);

        Image custom = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.CUSTOMBUTTON)).getScaledCopy(buttonWidth, buttonHeight / 2);
        customButton = new MouseOverArea(container, custom, container.getWidth()/2-buttonWidth/2, 80 * container.getHeight() / 100, buttonWidth, buttonHeight / 2, this);

        addButton(singleButton);
        addButton(multiButton);
        addButton(leaderboardButton);
        addButton(customButton);
    }

    public void render(){
        super.render();
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == singleButton ) {
                state.single();
            }
        if(source == multiButton){
            state.multi();
        }
        if (source == leaderboardButton ) {
            state.leaderBoard();
        }
        if (source == customButton ) {
            state.custom();
        }
    }
}
