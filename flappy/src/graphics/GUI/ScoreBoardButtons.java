package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;
import states.ScoreBoardState;
import java.io.IOException;

public class ScoreBoardButtons extends AbstractMenuGUI {

    private MouseOverArea deleteButton;
    private MouseOverArea backButton;
    private ScoreBoardState state;

    public ScoreBoardButtons(GameContainer container, Screen screen, ScoreBoardState state) throws SlickException {
        super(container, screen);
        this.state=state;

        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        Image deleteImage = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.RESETRANKBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        deleteButton = new MouseOverArea(container, deleteImage, container.getWidth()-3*buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        Image backImage = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
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

