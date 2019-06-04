package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.FileKeys;
import resources.PathHandler;
import states.CustomizationMenu;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

public class CustomizationMenuGUI extends AbstractMenuGUI {
    private CustomizationMenu state;
    private MouseOverArea batmanButton;
    private MouseOverArea seaButton;
    private MouseOverArea classicButton;
    private MouseOverArea skyButton;
    private MouseOverArea dogoButton;
    private MouseOverArea returnButton;
    private Shape bordo;
    private String title;
    //private UnicodeFont uniFontMessage;
    private UnicodeFont versionFont;
    private boolean chosen = false;
    //private Font font = new Font("Verdana", Font.BOLD, getContainer().getHeight()/10);


    private int buttonWidth;
    private int buttonHeight;

    public CustomizationMenuGUI(GameContainer container, Screen screen, CustomizationMenu state) throws SlickException, IOException, FontFormatException {
        super(container, screen);
        this.state = state;
        buttonHeight = container.getHeight()/9;
        buttonWidth = container.getWidth()/9;


        Image dogo = new Image("res/sprites/customTextures/dogo/dogo.png").getScaledCopy(buttonWidth,buttonHeight);
        dogoButton = new MouseOverArea(container, dogo, 2*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);
        Image bird = new Image("res/sprites/player/bird.png").getScaledCopy(buttonWidth,buttonHeight);
        classicButton = new MouseOverArea(container, bird, 3*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);
        Image fish = new Image("res/sprites/customTextures/sea/fish.png").getScaledCopy(buttonWidth,buttonHeight);
        seaButton = new MouseOverArea(container, fish, 4*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);
        Image blueBird = new Image("res/sprites/customTextures/sky/bird.png").getScaledCopy(buttonWidth,buttonHeight);
        skyButton = new MouseOverArea(container, blueBird, 5*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);
        Image batman = new Image("res/sprites/customTextures/batman/batman.png").getScaledCopy(buttonWidth,buttonHeight);
        batmanButton = new MouseOverArea(container, batman, 6*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);
        Image goBack = new Image("res/sprites/buttons/back.png").getScaledCopy(buttonWidth*3,buttonHeight);
        returnButton = new MouseOverArea(container, goBack, container.getWidth()/2 - buttonWidth/2, 75*container.getHeight()/100, buttonWidth, buttonHeight, this);

        /*uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.darkGray));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();*/

        bordo = new Rectangle(0, 28*container.getHeight()/100,
                14*container.getWidth()/100, 15*container.getHeight()/100);

        title = "CHOOSE YOUR THEME";
        versionFont = new UnicodeFont("res/font/FlappyBirdy.ttf", getContainer().getHeight()/5, false, false);
        versionFont.addAsciiGlyphs();
        versionFont.getEffects().add(new ColorEffect(Color.BLACK));
        versionFont.loadGlyphs();
    }

    @Override
    public void render() {
        batmanButton.render(getContainer(),getContainer().getGraphics());
        classicButton.render(getContainer(),getContainer().getGraphics());
        dogoButton.render(getContainer(),getContainer().getGraphics());
        skyButton.render(getContainer(),getContainer().getGraphics());
        seaButton.render(getContainer(),getContainer().getGraphics());
        returnButton.render(getContainer(),getContainer().getGraphics());

        getContainer().getGraphics().setFont(versionFont);
        getContainer().getGraphics().drawString(title, 14*getContainer().getWidth()/100 , 10 * getContainer().getHeight() / 100);

       // uniFontMessage.drawString(getContainer().getWidth() / 2 - uniFontMessage.getWidth(title) / 2, 10 * getContainer().getHeight() / 100f, title);

       if(chosen) {
           getContainer().getGraphics().draw(bordo);

       }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == batmanButton){
            try {
                chosen = true;
                bordo.setX(batmanButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.BATMAN);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == dogoButton){
            try {
                chosen = true;
                bordo.setX(dogoButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.DOGO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == classicButton){
            try {
                chosen = true;
                bordo.setX(classicButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.CLASSIC);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == seaButton){
            try {
                chosen = true;
                bordo.setX(seaButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.SEA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == skyButton){
            try {
                chosen = true;
                bordo.setX(skyButton.getX()- 10*bordo.getWidth()/100);
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
