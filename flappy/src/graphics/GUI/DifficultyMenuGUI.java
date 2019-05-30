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

        Image easy = new Image("res/Images/easy.png").getScaledCopy(450,140);
        easyButton = new MouseOverArea(container, easy, 35*container.getWidth()/100, 20*container.getHeight()/100, 450, 140, this);
        Image medium = new Image("res/Images/medium.png").getScaledCopy(450,140);
        mediumButton = new MouseOverArea(container, medium, 35*container.getWidth()/100, 40*container.getHeight()/100, 450, 140, this);
        Image hard = new Image("res/Images/hard.png").getScaledCopy(450,140);
        hardButton = new MouseOverArea(container, hard, 35*container.getWidth()/100, 60*container.getHeight()/100, 450, 140, this);


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
