package graphics.GUI;

import graphics.Screen;
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

    public SingleplayerReplayMenuGUI(GameContainer container, SingleplayerReplayMenuState state, Screen screen) throws SlickException {
        super(container);
        this.state=state;
        Image yesImage = new Image("res/replaybutton.png");
        yesButton = new Button(container,screen, yesImage,  0.3, this);
        Image noImage = new Image("res/back2menubutton.png");
        noButton = new Button(container,screen, noImage,  0.5, this);
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
