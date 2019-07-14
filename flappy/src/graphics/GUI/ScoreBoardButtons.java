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
import states.ScoreBoardState;

import java.io.IOException;

public class ScoreBoardButtons  extends AbstractMenuGUI {

    private MouseOverArea deleteButton;
    private MouseOverArea backButton;
    private ScoreBoardState state;

    public ScoreBoardButtons(GameContainer container, Screen screen, ScoreBoardState state) throws SlickException {
        super(container, screen);
        this.state=state;

        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        Image deleteImage = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.RESETRANKBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        deleteButton = new MouseOverArea(container, deleteImage, container.getWidth()-3*buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        Image backImage = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        backButton = new MouseOverArea(container, backImage, container.getWidth()-buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        addButton(deleteButton);
        addButton(backButton);
    }

    public void render(){
        renderButtons();
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == backButton)
            state.backToMenu();

        else if (source == deleteButton){
       try {
            state.deleteLeaderBoard();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SlickException e) {
           e.printStackTrace();
       }}
    }
}

