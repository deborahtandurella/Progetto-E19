package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;
import states.CustomizationMenu;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.Color;

import java.awt.*;

import java.awt.Font;
import java.io.IOException;


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
    private UnicodeFont versionFont;
    private boolean chosen = false;
    private String name;
    private UnicodeFont uniFontMessage;
    private Image pergamena;

    public CustomizationMenuGUI(GameContainer container, Screen screen, CustomizationMenu state) throws SlickException, IOException, FontFormatException {
        super(container, screen);
        this.state = state;
        pergamena = new Image(PathHandler.getInstance().getPath(FileKeys.VARIOUS, PathKeys.RANKBACKGROUND)).getScaledCopy(container.getWidth()/100*86, container.getHeight()/100*30);
        title = "CHOOSE YOUR THEME";
        Font font = new Font("Comic Sans MS", Font.BOLD, 27*getContainer().getWidth()/1000);
        versionFont = new UnicodeFont("res/font/FlappyBirdy.ttf", getContainer().getHeight()/5, false, false);

        int buttonHeight = container.getHeight()/9;
        int buttonWidth = container.getWidth()/9;
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.LIGHT_GRAY));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        versionFont.addAsciiGlyphs();
        versionFont.getEffects().add(new ColorEffect(java.awt.Color.BLACK));
        versionFont.loadGlyphs();

        bordo = new Rectangle(0, 28*container.getHeight()/100f, 14*container.getWidth()/100f, 15*container.getHeight()/100f);

        Image dogo = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.DOGOBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        dogoButton = new MouseOverArea(container, dogo, 2*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image bird = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BIRDBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        classicButton = new MouseOverArea(container, bird, 3*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image fish = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.FISHBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        seaButton = new MouseOverArea(container, fish, 4*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image blueBird = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BLUEBIRDBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        skyButton = new MouseOverArea(container, blueBird, 5*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image batman = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BATMANBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        batmanButton = new MouseOverArea(container, batman, 6*container.getWidth()/7-buttonWidth, 30*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image goBack = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth*3,buttonHeight);
        returnButton = new MouseOverArea(container, goBack, container.getWidth()/2 - buttonWidth/2, 75*container.getHeight()/100, buttonWidth, buttonHeight, this);

        addButton(dogoButton);
        addButton(classicButton);
        addButton(seaButton);
        addButton(skyButton);
        addButton(batmanButton);
        addButton(returnButton);

    }

    @Override
    public void render() {
        pergamena.draw(getContainer().getWidth()/2f-pergamena.getWidth()/2f, 24*getContainer().getHeight()/100f);



        getContainer().getGraphics().setFont(versionFont);
        getContainer().getGraphics().drawString(title, 14*getContainer().getWidth()/100f , 10 * getContainer().getHeight() / 100f);

       if(chosen) {
           getContainer().getGraphics().setColor(Color.orange);
           getContainer().getGraphics().fill(bordo);
           getContainer().getGraphics().draw(bordo);
           uniFontMessage.drawString(getContainer().getWidth()/2f-uniFontMessage.getWidth(name)/2f, 44*getContainer().getHeight()/100f, name, org.newdawn.slick.Color.black);

       }

        renderButtons();
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == batmanButton){
            try {
                name = "Batman";
                chosen = true;
                bordo.setX(batmanButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.BATMAN);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == dogoButton){
            try {
                name = "Dogo";
                chosen = true;
                bordo.setX(dogoButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.DOGO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == classicButton){
            try {
                name = "Classic";
                chosen = true;
                bordo.setX(classicButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.CLASSIC);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == seaButton){
            try {
                name = "Sea";
                chosen = true;
                bordo.setX(seaButton.getX()- 10*bordo.getWidth()/100);
                PathHandler.getInstance().changeSprites(FileKeys.SEA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(source == skyButton){
            try {
                name = "Sky";
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
