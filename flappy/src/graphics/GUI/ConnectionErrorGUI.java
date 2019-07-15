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
import states.menu.ConnectionErrorMenu;

import java.awt.*;

public class ConnectionErrorGUI extends AbstractMenuGUI {
    private ConnectionErrorMenu state;
    private MouseOverArea returnButton;
    private Image error;
    private float imageDimension;
    private String errorMessage;
    private UnicodeFont uniFontMessage;

    public ConnectionErrorGUI(GameContainer container, Screen screen, ConnectionErrorMenu state) throws SlickException {
        super(container, screen);
        this.state = state;

        imageDimension = container.getWidth()/5f;

        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        Font font = new Font("Comic Sans MS", Font.BOLD, 5*getContainer().getWidth()/100 /*46*/);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.red));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();

        errorMessage = "CONNECTION ERROR !";

        error = new Image(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.CONNECTIONERROR)).getScaledCopy((int) imageDimension,(int) imageDimension);

        Image returnImage = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth,8*buttonHeight/10);
        returnButton = new MouseOverArea(container, returnImage, container.getWidth()/2 - buttonWidth/2, container.getHeight() - 20*buttonHeight/10, buttonWidth, buttonHeight, this);

        addButton(returnButton);

    }

    @Override
    public void render()  {
        super.render();
        uniFontMessage.drawString((getContainer().getWidth() - uniFontMessage.getWidth(errorMessage)) / 2f, 33 * getContainer().getHeight() / 100f, errorMessage);
        error.draw(getContainer().getWidth()/2f-imageDimension/2f, 3*getContainer().getHeight()/100f);
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == returnButton){
            state.backToMenu();
        }
    }
}
