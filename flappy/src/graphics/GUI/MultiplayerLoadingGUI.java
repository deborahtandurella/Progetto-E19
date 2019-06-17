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
    private SpriteSheet threeTwoOneSheet;
    private Animation threeTwoOneAnimation;
    private Image background;
    private int buttonDimension;
    private boolean connected;


    public MultiplayerLoadingGUI(GameContainer container, Screen screen, MultiplayerLoading state) throws SlickException {
        super(container, screen);
        this.state = state;

        connected = false;

        buttonDimension = container.getWidth()/5;

        background = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES,PathKeys.BACKGROUND)).getScaledCopy(screen.getWidth(), screen.getHeight());

        loadingSheet = new SpriteSheet(PathHandler.getInstance().getPath(FileKeys.VARIOUS, PathKeys.LOADINGSHEET),64,64);
        loadingAnimation = new Animation(loadingSheet,100);

        threeTwoOneSheet = new SpriteSheet(PathHandler.getInstance().getPath(FileKeys.VARIOUS, PathKeys.THREETWOONE),288,288);
        threeTwoOneAnimation = new Animation(threeTwoOneSheet,50);
    }

    @Override
    public void render() throws SlickException {
        background.draw();
        if(!connected) {
            loadingAnimation.draw(getContainer().getWidth() / 2 - buttonDimension / 2, getContainer().getHeight() / 2 - buttonDimension / 2, buttonDimension, buttonDimension);
        }else{
            threeTwoOneAnimation.draw(getContainer().getWidth() / 2 - buttonDimension / 2, getContainer().getHeight() / 2 - buttonDimension / 2, buttonDimension, buttonDimension);
        }
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }

    public void connected(){
        connected=true;
    }
}

//HEY, TI VA UNA SCHWEPPES SOLO IO E TE?