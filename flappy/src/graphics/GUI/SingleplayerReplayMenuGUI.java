package graphics.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.SingleplayerReplayMenuState;

public class SingleplayerReplayMenuGUI extends AbstractMenuGUI {
    private MouseOverArea yesButton;
    private MouseOverArea noButton;
    private SingleplayerReplayMenuState state;
    public SingleplayerReplayMenuGUI(GameContainer container, SingleplayerReplayMenuState state) throws SlickException {
        super(container);
        this.state=state;
        Image yesImage = new Image("res/play.png").getScaledCopy(150,70);
        yesButton = new MouseOverArea(container, yesImage, 300, 150, 200, 70, this);
        Image noImage = new Image("res/play.png").getScaledCopy(150,70);
        noButton = new MouseOverArea(container, noImage, 300, 350, 200, 70, this);
    }

    public void render(){
        yesButton.render(container,container.getGraphics());
        noButton.render(container,container.getGraphics());


    }
    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == noButton)
            state.noRematch();
        else if (source == yesButton)
            state.rematch();
    }
}
