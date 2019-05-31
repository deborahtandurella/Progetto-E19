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
    private int buttonWidth;
    private int buttonHeight;

    public DifficultyMenuGUI(GameContainer container, Screen screen, DifficultyMenu state) throws SlickException {
        super(container, screen);
        this.state = state;

        buttonHeight = container.getHeight()/7;
        buttonWidth = container.getWidth()/3;
        Image easy = new Image("res/sprites/buttons/easy.png").getScaledCopy(buttonWidth,buttonHeight);
        easyButton = new MouseOverArea(container, easy, container.getWidth()/2 - buttonWidth/2, container.getHeight() - 5*buttonHeight, buttonWidth, buttonHeight, this);
        Image medium = new Image("res/sprites/buttons/medium.png").getScaledCopy(buttonWidth,buttonHeight);
        mediumButton = new MouseOverArea(container, medium, (container.getWidth()-buttonWidth)/2, container.getHeight() - 35*buttonHeight/10, buttonWidth, buttonHeight, this);
        Image hard = new Image("res/sprites/buttons/hard.png").getScaledCopy(buttonWidth,buttonHeight);
        hardButton = new MouseOverArea(container, hard, (container.getWidth()-buttonWidth)/2, container.getHeight() - 2*buttonHeight, buttonWidth, buttonHeight, this);

        /*Image easy = new Image("res/Images/easy.png").getScaledCopy(250, 50);
        easyButton = new Button(container, screen, easy,  0.2, this);
        Image medium = new Image("res/Images/medium.png").getScaledCopy(250, 50);
        mediumButton = new Button(container,screen, medium,  0.4, this);
        Image hard = new Image("res/Images/hard.png").getScaledCopy(250, 50);
        hardButton = new Button(container,screen, hard,  0.6, this);*/
    }

    @Override
    public void render() {
        easyButton.render(getContainer(),getContainer().getGraphics());
        mediumButton.render(getContainer(),getContainer().getGraphics());
        hardButton.render(getContainer(),getContainer().getGraphics());
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
