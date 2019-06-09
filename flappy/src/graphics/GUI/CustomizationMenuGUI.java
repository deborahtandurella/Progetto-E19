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
    private String title;
    private UnicodeFont versionFont;
    private String name;
    private UnicodeFont uniFontMessage;
    private Image pergamena;
    private Image background;
    private Image buttonBg;
    private int buttonHeight;
    private int buttonWidth;
    private int buttonBgX;

    public CustomizationMenuGUI(GameContainer container, Screen screen, CustomizationMenu state) throws SlickException, IOException, FontFormatException {
        super(container, screen);
        this.state = state;

        pergamena = new Image(PathHandler.getInstance().getPath(FileKeys.VARIOUS, PathKeys.RANKBACKGROUND)).getScaledCopy(container.getWidth()/100*86, container.getHeight()/100*30);
        background = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.BACKGROUND)).getScaledCopy(screen.getWidth(), screen.getHeight());

        title = "CHOOSE YOUR THEME";
        Font font = new Font("Comic Sans MS", Font.BOLD, 27*getContainer().getWidth()/1000);
        versionFont = new UnicodeFont("res/font/FlappyBirdy.ttf", getContainer().getHeight()/100*25, false, false);

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.LIGHT_GRAY));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        versionFont.addAsciiGlyphs();
        versionFont.getEffects().add(new ColorEffect(java.awt.Color.BLACK));
        versionFont.loadGlyphs();

        buttonHeight = container.getHeight()/9;
        buttonWidth = container.getWidth()/9;

        Image dogo = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.DOGOBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        dogoButton = new MouseOverArea(container, dogo, 2*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image bird = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BIRDBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        classicButton = new MouseOverArea(container, bird, 3*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image fish = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.FISHBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        seaButton = new MouseOverArea(container, fish, 4*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image blueBird = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BLUEBIRDBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        skyButton = new MouseOverArea(container, blueBird, 5*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image batman = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BATMANBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        batmanButton = new MouseOverArea(container, batman, 6*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image goBack = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth*3,buttonHeight);
        returnButton = new MouseOverArea(container, goBack, container.getWidth()/2 - buttonWidth/2, 75*container.getHeight()/100, buttonWidth, buttonHeight, this);

        buttonBg = new Image(PathHandler.getInstance().getPath(FileKeys.VARIOUS, PathKeys.BUTTON_BG)).getScaledCopy(buttonWidth,buttonHeight);

        buttonBgX = classicButton.getX()-10*buttonWidth/100;

        addButton(dogoButton);
        addButton(classicButton);
        addButton(seaButton);
        addButton(skyButton);
        addButton(batmanButton);
        addButton(returnButton);

        name = "Classic";

    }

    @Override
    public void render() {
        background.draw();
        pergamena.draw(getContainer().getWidth()/2f-pergamena.getWidth()/2f, 26*getContainer().getHeight()/100f);
        buttonBg.draw(buttonBgX, 30*getContainer().getHeight()/100f, 14*getContainer().getWidth()/100f, 15*getContainer().getHeight()/100f);
        getContainer().getGraphics().setFont(versionFont);
        getContainer().getGraphics().drawString(title, 7*getContainer().getWidth()/100f , 9 * getContainer().getHeight() / 100f);

        uniFontMessage.drawString(getContainer().getWidth()/2f-uniFontMessage.getWidth(name)/2f, 46*getContainer().getHeight()/100f, name, org.newdawn.slick.Color.black);

        renderButtons();
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == batmanButton){
            name = "Batman";
            buttonBgX = batmanButton.getX()-10*buttonWidth/100;
            changeTheme(FileKeys.DOGO);
        }
        if(source == dogoButton){
            name = "Dogo";
            buttonBgX = dogoButton.getX()-10*buttonWidth/100;
            changeTheme(FileKeys.DOGO);
        }
        if(source == classicButton){
            name = "Classic";
            buttonBgX = classicButton.getX()-10*buttonWidth/100;
            changeTheme(FileKeys.CLASSIC);

        }
        if(source == seaButton) {
            name = "Sea";
            buttonBgX = seaButton.getX() - 10 * buttonWidth / 100;
            changeTheme(FileKeys.SEA);
        }
        if(source == skyButton){
            name = "Sky";
            buttonBgX = skyButton.getX()-10*buttonWidth/100;
            changeTheme(FileKeys.SKY);
        }
        if(source == returnButton){
            state.goBack();
        }
    }

    private void changeTheme(FileKeys theme){
        state.themeSelected(theme);
        try{
            background = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.BACKGROUND)).getScaledCopy(getContainer().getWidth(), getContainer().getHeight());
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

}
