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

    public MenuGUI(GameContainer container, Menu state, Screen screen) throws SlickException {
        super(container);
        this.state = state;
        Image single = new Image("res/play.png");
        singleButton = new Button(container,screen, single,  0.3, this);
        Image multi = new Image("res/play.png");
        multiButton = new Button(container,screen, multi,  0.5, this);
    }

    public void render(){
        singleButton.render(container,container.getGraphics());
        multiButton.render(container,container.getGraphics());
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
