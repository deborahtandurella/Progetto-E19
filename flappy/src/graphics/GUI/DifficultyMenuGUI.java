package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.DifficultyMenu;

public class DifficultyMenuGUI extends AbstractMenuGUI {
    private DifficultyMenu state;
    private MouseOverArea easyButton;
    private MouseOverArea mediumButton;
    private MouseOverArea hardButton;

    public DifficultyMenuGUI(GameContainer container, Screen screen, DifficultyMenu state) throws SlickException {
        super(container, screen);
        this.state = state;
        int buttonHeight = container.getHeight()/7;
        int buttonWidth = container.getWidth()/3;
        Image easy = new Image("res/sprites/buttons/easy.png").getScaledCopy(buttonWidth,buttonHeight);
        easyButton = new MouseOverArea(container, easy, container.getWidth()/2 - buttonWidth/2, container.getHeight() - 5*buttonHeight, buttonWidth, buttonHeight, this);
        Image medium = new Image("res/sprites/buttons/medium.png").getScaledCopy(buttonWidth,buttonHeight);
        mediumButton = new MouseOverArea(container, medium, (container.getWidth()-buttonWidth)/2, container.getHeight() - 35*buttonHeight/10, buttonWidth, buttonHeight, this);
        Image hard = new Image("res/sprites/buttons/hard.png").getScaledCopy(buttonWidth,buttonHeight);
        hardButton = new MouseOverArea(container, hard, (container.getWidth()-buttonWidth)/2, container.getHeight() - 2*buttonHeight, buttonWidth, buttonHeight, this);
        addButton(easyButton);
        addButton(mediumButton);
        addButton(hardButton);
    }

    @Override
    public void render() {
        renderButtons();
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == easyButton){
            state.easy();
        }
        if(source == mediumButton){
            state.medium();
        }
        if(source == hardButton){
            state.hard();
        }
    }
}
