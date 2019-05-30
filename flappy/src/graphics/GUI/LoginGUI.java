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

    private Login state;
    private TextField nameField;
    private MouseOverArea okButton;
    private String nameString;
    private UnicodeFont uniFontMessage;
    private Font font = new Font("Verdana", Font.BOLD, 38);
    private TrueTypeFont ttf = new TrueTypeFont(font, true);
    private String errorMessage;
    private boolean error = false;
    private Record record;
    private int buttonWidth;
    private int buttonHeight;

    public LoginGUI(GameContainer container, Screen screen, Login state, Record record) throws SlickException {
        super(container, screen);
        this.state = state;
        this.record = record;
        buttonHeight = container.getHeight() / 7;
        buttonWidth = container.getWidth() / 3;
        Image confirm = new Image("res/Images/okbutton.png");
        okButton = new Button(container, screen, confirm, 0.3, this);
        nameField = new TextField(container, ttf, container.getWidth() / 2 - buttonWidth / 2, container.getHeight() - 6 * buttonHeight, buttonWidth, buttonHeight / 2);
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
        okButton.render(getContainer(),getContainer().getGraphics());
        nameField.render(getContainer(), getContainer().getGraphics());
        uniFontMessage.drawString(getContainer().getWidth() / 2 - uniFontMessage.getWidth(nameString) / 2, 7 * getContainer().getHeight() / 100f, nameString);
        if (error) {
            uniFontMessage.drawString(getContainer().getWidth() / 2 - uniFontMessage.getWidth(errorMessage) / 2,
                    22 * getContainer().getHeight() / 100f, errorMessage, Color.red);
            //}
        }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == okButton) {

            if (nameField.getText().equals("")) {
                error = true;
                errorMessage = "Inserisci il tuo nickname!";

            } else {
                record.setLogin(false);
                state.menu();
            }


        }
    }
}
