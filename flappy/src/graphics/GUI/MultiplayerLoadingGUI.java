package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.AbstractComponent;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import states.MultiplayerLoading;

public class MultiplayerLoadingGUI extends AbstractMenuGUI{
    private Animation loadingAnimation;
    private Animation threeTwoOneAnimation;
    private MultiplayerLoading state;
    private int buttonDimension;
    private boolean connected;


    public MultiplayerLoadingGUI(GameContainer container, Screen screen, MultiplayerLoading state) throws SlickException {
        super(container, screen);
        setBackground();
        this.state=state;


        connected = false;

        buttonDimension = container.getWidth()/5;


        SpriteSheet loadingSheet = new SpriteSheet(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.LOADINGSHEET),64,64);
        loadingAnimation = new Animation(loadingSheet,100);
        SpriteSheet threeTwoOneSheet = new SpriteSheet(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.THREETWOONE),288,288);
        threeTwoOneAnimation = new Animation(threeTwoOneSheet,50);
        loadingAnimation.stopAt(threeTwoOneAnimation.getFrameCount()-1);

    }

    @Override
    public void reload() {
        connected=false;
        loadingAnimation.restart();
        loadingAnimation.stopAt(threeTwoOneAnimation.getFrameCount()-1);
    }

    @Override
    public void render() {
        renderBackground();
        connected= state.isConnecting();
        if(!connected) {
            loadingAnimation.draw((getContainer().getWidth() - buttonDimension) / 2f, (getContainer().getHeight() - buttonDimension)/2f, buttonDimension, buttonDimension);
        }else{
            threeTwoOneAnimation.draw((getContainer().getWidth() - buttonDimension)/2f, (getContainer().getHeight() - buttonDimension) / 2f, buttonDimension, buttonDimension);
        }
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }

    public void connected(){
        connected=true;
    }
}
