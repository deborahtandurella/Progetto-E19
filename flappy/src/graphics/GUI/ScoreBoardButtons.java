package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.ScoreBoardState;
import java.io.IOException;

public class ScoreBoardButtons extends AbstractMenuGUI {

    private MouseOverArea deleteButton;
    private MouseOverArea backButton;
    private ScoreBoardState state;
    private int buttonWidth;
    private int buttonHeight;


    public ScoreBoardButtons(GameContainer container, Screen screen, ScoreBoardState state) throws SlickException {
        super(container, screen);
        this.state=state;
        buttonHeight = container.getHeight()/10;
        buttonWidth = container.getWidth()/3;

        Image deleteImage = new Image("res/sprites/buttons/delete.png").getScaledCopy(buttonWidth, buttonHeight);
        deleteButton = new MouseOverArea(container, deleteImage, container.getWidth()-3*buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        Image backImage = new Image("res/sprites/buttons/back.png").getScaledCopy(buttonWidth, buttonHeight);
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

