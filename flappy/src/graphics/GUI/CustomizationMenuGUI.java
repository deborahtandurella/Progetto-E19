package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import states.menu.CustomizationMenu;
import java.awt.*;
import java.io.IOException;

/**
 * GUI del CustomizationMenu
 */

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
    private Image buttonBg;
    private int buttonHeight;
    private int buttonWidth;
    private int buttonBgX;

    public CustomizationMenuGUI(GameContainer container, Screen screen, CustomizationMenu state) throws SlickException, IOException, FontFormatException {
        super(container, screen);
        this.state = state;

        pergamena = new Image(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.RANKBACKGROUND)).getScaledCopy(container.getWidth()/100*86, container.getHeight()/100*30);
        title = "CHOOSE YOUR THEME";
        Font font = new Font("Comic Sans MS", Font.BOLD, 27*getContainer().getWidth()/1000);
        versionFont = new UnicodeFont(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.FLAPPYFONT), getContainer().getHeight()/100*25, false, false);

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.LIGHT_GRAY));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        versionFont.addAsciiGlyphs();
        versionFont.getEffects().add(new ColorEffect(Color.ORANGE));
        versionFont.loadGlyphs();

        buttonHeight = container.getHeight()/9;
        buttonWidth = container.getWidth()/9;

        Image dogo = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.DOGOBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        dogoButton = new MouseOverArea(container, dogo, 2*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image bird = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BIRDBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        classicButton = new MouseOverArea(container, bird, 3*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image fish = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.FISHBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        seaButton = new MouseOverArea(container, fish, 4*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image blueBird = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BLUEBIRDBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        skyButton = new MouseOverArea(container, blueBird, 5*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image batman = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BATMANBUTTON)).getScaledCopy(buttonWidth,buttonHeight);
        batmanButton = new MouseOverArea(container, batman, 6*container.getWidth()/7-buttonWidth, 32*container.getHeight()/100, buttonWidth, buttonHeight, this);

        Image goBack = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth*3,buttonHeight);
        returnButton = new MouseOverArea(container, goBack, container.getWidth()/2 - buttonWidth*3/2, 75*container.getHeight()/100, buttonWidth*3, buttonHeight, this);

        buttonBg = new Image(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.BUTTON_BG)).getScaledCopy(buttonWidth,buttonHeight);

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
        renderBackground();
        pergamena.draw(getContainer().getWidth()/2f-pergamena.getWidth()/2.1f, 26*getContainer().getHeight()/100f);
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
            changeTheme(ResourcePack.BATMAN);
        }
        if(source == dogoButton){
            name = "Dogo";
            buttonBgX = dogoButton.getX()-10*buttonWidth/100;
            changeTheme(ResourcePack.DOGO);
        }
        if(source == classicButton){
            name = "Classic";
            buttonBgX = classicButton.getX()-10*buttonWidth/100;
            changeTheme(ResourcePack.CLASSIC);

        }
        if(source == seaButton) {
            name = "Sea";
            buttonBgX = seaButton.getX() - 10 * buttonWidth / 100;
            changeTheme(ResourcePack.SEA);
        }
        if(source == skyButton){
            name = "Sky";
            buttonBgX = skyButton.getX()-10*buttonWidth/100;
            changeTheme(ResourcePack.SKY);
        }
        if(source == returnButton){
            state.goBack();
        }
    }

    private void changeTheme(ResourcePack theme){
        state.themeSelected(theme);
        try{
            setBackground();
        } catch (SlickException e){
            e.printStackTrace();
        }
    }

}
