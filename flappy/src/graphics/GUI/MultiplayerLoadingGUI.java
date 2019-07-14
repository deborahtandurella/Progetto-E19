package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import states.MultiplayerLoading;

public class MultiplayerLoadingGUI extends AbstractMenuGUI{
    private Animation loadingAnimation;
    private Animation threeTwoOneAnimation;
    private MultiplayerLoading state;
    private int animationDimension;
    private boolean connected;
    private MouseOverArea backButton;


    public MultiplayerLoadingGUI(GameContainer container, Screen screen, MultiplayerLoading state) throws SlickException {
        super(container, screen);
        this.state=state;

        connected = false;

        animationDimension = container.getWidth()/5;
        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        Image backImage = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        backButton = new MouseOverArea(container, backImage, (container.getWidth() - buttonWidth) / 2, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);
        addButton(backButton);

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
            loadingAnimation.draw((getContainer().getWidth() - animationDimension) / 2f, (getContainer().getHeight() - animationDimension) / 2f, animationDimension, animationDimension);
            renderButtons();
        }else{
            threeTwoOneAnimation.draw((getContainer().getWidth() - animationDimension) / 2f, (getContainer().getHeight() - animationDimension) / 2f, animationDimension, animationDimension);
        }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == backButton){
            if(connected)
                state.back();
        }
    }

    public void connected(){
        connected=true;
    }
}
