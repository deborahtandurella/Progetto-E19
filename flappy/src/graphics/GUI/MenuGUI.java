package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.Menu;

public class MenuGUI extends AbstractMenuGUI {
    private Menu state;
    private MouseOverArea singleButton;
    private MouseOverArea multiButton;

    public MenuGUI(GameContainer container, Screen screen, Menu state) throws SlickException {
        super(container, screen);
        this.state = state;
        Image single = new Image("res/singleplayerbutton.png");
        singleButton = new Button(container,screen, single,  0.3, this);
        Image multi = new Image("res/multiplayerbutton.png");
        multiButton = new Button(container,screen, multi,  0.5, this);
    }

    public void render(){
        singleButton.render(getContainer(),getContainer().getGraphics());
        multiButton.render(getContainer(),getContainer().getGraphics());
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == singleButton ) {
            state.single();
        }
        if(source == multiButton){
            state.multi();
        }
    }
}
