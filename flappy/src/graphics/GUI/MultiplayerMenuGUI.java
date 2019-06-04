package graphics.GUI;

import org.newdawn.slick.gui.AbstractComponent;
import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import states.MultiplayerMenu;

public class MultiplayerMenuGUI extends AbstractMenuGUI {

    private MouseOverArea backButton;
    private MouseOverArea hostButton;
    private MouseOverArea joinButton;
    private MultiplayerMenu state;
    private int buttonWidth;
    private int buttonHeight;

    public MultiplayerMenuGUI(GameContainer container, Screen screen, MultiplayerMenu state) throws SlickException {
        super(container, screen);
        this.state = state;
        buttonHeight = container.getHeight()/10;
        buttonWidth = container.getWidth()/3;
        Image backImage = new Image("res/sprites/buttons/back1.png").getScaledCopy(buttonWidth, buttonHeight);
        backButton = new MouseOverArea(container, backImage, 65*container.getWidth()/100-buttonWidth, 90*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);
        Image hostImage = new Image("res/sprites/buttons/host.png").getScaledCopy(buttonWidth, buttonHeight);
        hostButton = new MouseOverArea(container, hostImage, 85*container.getWidth()/100-buttonWidth, 60*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);
        Image joinImage = new Image("res/sprites/buttons/join.png").getScaledCopy(buttonWidth, buttonHeight);
        joinButton = new MouseOverArea(container, joinImage, 45*container.getWidth()/100-buttonWidth, 60*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);

    }

    @Override
    public void render() {
        backButton.render(getContainer(), getContainer().getGraphics());
        hostButton.render(getContainer(), getContainer().getGraphics());
        joinButton.render(getContainer(), getContainer().getGraphics());
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == backButton)
            state.backToMenu();
    }
}
