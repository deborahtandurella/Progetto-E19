package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.FileKeys;
import resources.PathHandler;
import states.CustomizationMenu;

import java.io.IOException;

public class CustomizationMenuGUI extends AbstractMenuGUI {
    private CustomizationMenu state;
    private MouseOverArea batmanButton;
    private MouseOverArea seaButton;
    private MouseOverArea classicButton;
    private MouseOverArea skyButton;
    private MouseOverArea dogoButton;
    private MouseOverArea returnButton;
    private int buttonWidth;
    private int buttonHeight;
    public CustomizationMenuGUI(GameContainer container, Screen screen, CustomizationMenu state) throws SlickException {
        super(container, screen);
        this.state = state;
        buttonHeight = container.getHeight()/7;
        buttonWidth = container.getWidth()/7;
        Image batman = new Image("res/sprites/customTextures/batman/batman.png").getScaledCopy(buttonWidth,buttonHeight);
        batmanButton = new MouseOverArea(container, batman, container.getWidth()/2 - 3*buttonWidth/2, container.getHeight()/2, buttonWidth, buttonHeight, this);
        Image dogo = new Image("res/sprites/customTextures/dogo/dogo.png").getScaledCopy(buttonWidth,buttonHeight);
        dogoButton = new MouseOverArea(container, dogo, container.getWidth()/2 - buttonWidth/2, container.getHeight()/2, buttonWidth, buttonHeight, this);
        Image bird = new Image("res/sprites/player/bird.png").getScaledCopy(buttonWidth,buttonHeight);
        classicButton = new MouseOverArea(container, bird, container.getWidth()/2 - 5*buttonWidth/2, container.getHeight()/2, buttonWidth, buttonHeight, this);
        Image fish = new Image("res/sprites/customTextures/sea/fish.png").getScaledCopy(buttonWidth,buttonHeight);
        seaButton = new MouseOverArea(container, fish, container.getWidth()/2 + 3*buttonWidth/2, container.getHeight()/2, buttonWidth, buttonHeight, this);
        Image blueBird = new Image("res/sprites/customTextures/sky/bird.png").getScaledCopy(buttonWidth,buttonHeight);
        skyButton = new MouseOverArea(container, blueBird, container.getWidth()/2 + 5*buttonWidth/2, container.getHeight()/2, buttonWidth, buttonHeight, this);
        Image goBack = new Image("res/sprites/buttons/back.png").getScaledCopy(buttonWidth,buttonHeight);
        returnButton = new MouseOverArea(container, goBack, container.getWidth()/2 - buttonWidth/2, container.getHeight()/4, buttonWidth, buttonHeight, this);

    }

    @Override
    public void render() {
        batmanButton.render(getContainer(),getContainer().getGraphics());
        classicButton.render(getContainer(),getContainer().getGraphics());
        dogoButton.render(getContainer(),getContainer().getGraphics());
        skyButton.render(getContainer(),getContainer().getGraphics());
        seaButton.render(getContainer(),getContainer().getGraphics());
        returnButton.render(getContainer(),getContainer().getGraphics());
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == batmanButton){
            try {
                PathHandler.getInstance().changeSprites(FileKeys.BATMAN);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == dogoButton){
            try {
                PathHandler.getInstance().changeSprites(FileKeys.DOGO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == classicButton){
            try {
                PathHandler.getInstance().changeSprites(FileKeys.CLASSIC);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == seaButton){
            try {
                PathHandler.getInstance().changeSprites(FileKeys.SEA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == skyButton){
            try {
                PathHandler.getInstance().changeSprites(FileKeys.SKY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == returnButton){
            state.goBack();
        }
    }
}
