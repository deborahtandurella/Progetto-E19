package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.SingleplayerReplayMenuState;
public class SingleplayerReplayMenuGUI extends AbstractMenuGUI {

    private MouseOverArea replayButton;
    private MouseOverArea backButton;
    private SingleplayerReplayMenuState state;
    private int buttonWidth;
    private int buttonHeight;


    public SingleplayerReplayMenuGUI(GameContainer container, Screen screen, SingleplayerReplayMenuState state) throws SlickException {
        super(container, screen);
        this.state=state;
        buttonHeight = container.getHeight()/10;
        buttonWidth = container.getWidth()/3;

        Image replayImage = new Image("res/sprites/buttons/replay.png").getScaledCopy(buttonWidth, buttonHeight);
        replayButton = new MouseOverArea(container, replayImage, container.getWidth()-3*buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        Image backImage = new Image("res/sprites/buttons/back.png").getScaledCopy(buttonWidth, buttonHeight);
        backButton = new MouseOverArea(container, backImage, container.getWidth()-buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

    }

    public void render(){
        replayButton.render(getContainer(),getContainer().getGraphics());
        backButton.render(getContainer(),getContainer().getGraphics());
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == backButton)
            state.noRematch();
        else if (source == replayButton)
            state.rematch();
    }
}
