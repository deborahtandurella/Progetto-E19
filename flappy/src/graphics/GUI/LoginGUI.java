package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import states.menu.LoginMenu;

import java.awt.Font;

public class LoginGUI extends AbstractMenuGUI {

    private Image title;
    private LoginMenu state;
    private TextField nameField;
    private MouseOverArea startButton;
    private UnicodeFont uniFontMessage;
    private String errorMessage;
    private String nameString;
    private boolean error = false;

    public LoginGUI(GameContainer container, Screen screen, LoginMenu state) throws SlickException {
        super(container, screen);
        this.state = state;
        Font font = new Font("Comic Sans MS", Font.BOLD, 3*getContainer().getWidth()/100 /*46*/);
        TrueTypeFont ttf = new TrueTypeFont(font, true);

        int buttonHeight = container.getHeight() / 7;
        int buttonWidth = container.getWidth() / 4;

        title = new Image(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.TITLE)).getScaledCopy(buttonWidth*3, buttonHeight*2);

        Image confirm = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.STARTBUTTON)).getScaledCopy(buttonWidth/2, buttonHeight/2);
        startButton = new MouseOverArea(container, confirm, container.getWidth() / 2 - buttonWidth / 4, 50 * getContainer().getHeight() / 100, buttonWidth/2, buttonHeight/2, this);
        addButton(startButton);

        nameField = new TextField(container, ttf, container.getWidth() / 2 - container.getWidth() / 6, 40 * getContainer().getHeight() / 100, container.getWidth()/3, buttonHeight/2);
        nameField.setBackgroundColor(Color.white);
        nameField.setTextColor(Color.black);
        deactivate();
        nameString = "INSERISCI IL NICKNAME";

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.white));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
    }
    public void activate(){
        nameField.setConsumeEvents(true);
    }
    public void deactivate(){
        nameField.setConsumeEvents(false);

    }

    @Override
    public void render() {
        super.render();
        title.draw((getContainer().getWidth()-title.getWidth()) / 2f,5 * getContainer().getHeight() / 100f);
        uniFontMessage.drawString((getContainer().getWidth() - uniFontMessage.getWidth(nameString)) / 2f, 33 * getContainer().getHeight() / 100f, nameString);
        nameField.render(getContainer(), getContainer().getGraphics());
        if (error) {
            uniFontMessage.drawString((getContainer().getWidth() - uniFontMessage.getWidth(errorMessage)) / 2f, 60 * getContainer().getHeight() / 100f, errorMessage, Color.red);
        }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == startButton) {

            if (nameField.getText().replace(" ", "").equals("")) {
                error = true;
                errorMessage = "Inserisci il tuo nickname!";
            } else
            if (nameField.getText().length() > 10) {
                error = true;
                errorMessage = "Lunghezza massima 10 caratteri!";
            } else
                {
                state.setName(nameField.getText());
                nameField.setFocus(false);
                nameField.deactivate();
                state.menu();
            }
        }
    }
}
