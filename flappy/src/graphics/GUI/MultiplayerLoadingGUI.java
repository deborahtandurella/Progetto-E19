package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;
import states.MultiplayerLoading;

public class MultiplayerLoadingGUI extends AbstractMenuGUI{
    private MultiplayerLoading state;
    private SpriteSheet loadingSheet;
    private Animation loadingAnimation;
    private Image background;
    private int buttonHeight;
    private int buttonWidth;

    public MultiplayerLoadingGUI(GameContainer container, Screen screen, MultiplayerLoading state) throws SlickException {
        super(container, screen);
        this.state = state;

        buttonHeight = container.getHeight()/7;
        buttonWidth = container.getWidth()/7;

        background = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES,PathKeys.BACKGROUND)).getScaledCopy(screen.getWidth(), screen.getHeight());

        loadingSheet = new SpriteSheet(PathHandler.getInstance().getPath(FileKeys.VARIOUS, PathKeys.LOADINGSHEET),64,64);
        loadingAnimation = new Animation(loadingSheet,100);
    }

    @Override
    public void render() throws SlickException {
        background.draw();
        loadingAnimation.draw(getContainer().getWidth()/2-buttonWidth/2,getContainer().getHeight()/2-buttonHeight/2,buttonWidth,buttonHeight);
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }
}
