package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import states.ScoreBoardState;
import java.awt.Font;


public class ScoreBoardMenuGUI extends AbstractMenuGUI {

    private ScoreBoardState state;
    private MouseOverArea okButton;
    private TextField nameField;
    private String nameString;
    private Font fontTitle;
    private Font fontMessage;
    private UnicodeFont uniFontMessage;
    private Font font = new Font("Verdana", Font.BOLD, 32);
    private TrueTypeFont ttf = new TrueTypeFont(font, true);


    public ScoreBoardMenuGUI(GameContainer container, ScoreBoardState state, Screen screen) throws SlickException {
        super(container, screen);
        this.state = state;
        Image ok = new Image("res/Images/okbutton.png").getScaledCopy(30,30);
        okButton = new Button(container, screen, ok, 0.3, this);
        nameField = new TextField(container, ttf, 40*container.getWidth()/100, 25*container.getHeight()/100, 300,40);
        nameField.setBackgroundColor(Color.white);
        nameField.setTextColor(Color.black);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.white));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        nameString = "NAME";

    }

    @Override
    public void render() {
        okButton.render(getContainer(), getContainer().getGraphics());
        nameField.render(getContainer(), getContainer().getGraphics());
        uniFontMessage.drawString(15*getContainer().getWidth()/100f, 25*getContainer().getHeight()/100f, nameString);

    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == okButton){
            //aggiungo il nome alla classifica
        }
    }
}
