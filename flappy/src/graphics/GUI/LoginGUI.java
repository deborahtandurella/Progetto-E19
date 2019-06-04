package graphics.GUI;

import graphics.Screen;
import logic.SinglePlayer.Record;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import java.awt.Font;
import states.Login;

public class LoginGUI extends AbstractMenuGUI {

    private Image title;
    private Login state;
    private TextField nameField;
    private MouseOverArea startButton;
    private String nameString;
    private UnicodeFont uniFontMessage;
    private Font font = new Font("Verdana", Font.BOLD, 46);
    private TrueTypeFont ttf = new TrueTypeFont(font, true);
    private String errorMessage;
    private boolean error = false;
    //private Record record;
    private int buttonWidth;
    private int buttonHeight;

    public LoginGUI(GameContainer container, Screen screen, Login state) throws SlickException {
        super(container, screen);
        this.state = state;
        buttonHeight = container.getHeight() / 7;
        buttonWidth = container.getWidth() / 4;
        title = new Image("res/sprites/backgrounds/title.jpg").getScaledCopy(buttonWidth*3, buttonHeight*2);
        Image confirm = new Image("res/sprites/buttons/startM.png").getScaledCopy(buttonWidth/2, buttonHeight/2);
        startButton = new MouseOverArea(container, confirm, container.getWidth()/2-buttonWidth/4, 50 * getContainer().getHeight() / 100, buttonWidth/2, buttonHeight/2, this);
        nameField = new TextField(container, ttf, container.getWidth() / 2 - container.getWidth()/6, 40 * getContainer().getHeight() / 100, container.getWidth()/3, buttonHeight/2);
        nameField.setBackgroundColor(Color.white);
        nameField.setTextColor(Color.black);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.white));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        nameString = "INSERISCI IL NICKNAME";
    }

    @Override
    public void render() {
        getContainer().getGraphics().drawImage(title, getContainer().getWidth()/2-title.getWidth()/2,5*getContainer().getHeight()/100);
        startButton.render(getContainer(),getContainer().getGraphics());
        nameField.render(getContainer(), getContainer().getGraphics());
        uniFontMessage.drawString(getContainer().getWidth() / 2 - uniFontMessage.getWidth(nameString) / 2, 33 * getContainer().getHeight() / 100f, nameString);
        if (error) {
            uniFontMessage.drawString(getContainer().getWidth() / 2 - uniFontMessage.getWidth(errorMessage) / 2,
                    60 * getContainer().getHeight() / 100f, errorMessage, Color.red);
        }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == startButton) {

            if (nameField.getText().equals("") || nameField.getText().charAt(0) == ' ') {
                error = true;
                errorMessage = "Inserisci il tuo nickname!";

            } else
            if (nameField.getText().length() > 10) {
                error = true;
                errorMessage = "Lunghezza massima 10 caratteri!";
            } else
                {
                state.setRecordName(nameField.getText());
                state.menu();
            }
        }
    }
}
