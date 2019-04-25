package graphics.GUI;

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

    public MenuGUI(GameContainer container, Menu state) throws SlickException {
        super(container);
        this.state = state;
        Image single = new Image("res/play.png").getScaledCopy(150, 70);
        singleButton = new MouseOverArea(container, single,  container.getWidth()/2 -100, 150, 200, 70, this);
        Image multi = new Image("res/play.png").getScaledCopy(150, 70);
        multiButton = new MouseOverArea(container, multi,  container.getWidth()/2 -100, 350, 200, 70, this);
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
