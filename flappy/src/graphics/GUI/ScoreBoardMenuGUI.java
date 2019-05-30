package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;
import states.ScoreBoardState;
import java.awt.Font;


public class ScoreBoardMenuGUI extends AbstractMenuGUI {

    private ScoreBoardState state;
    private MouseOverArea okButton;
    private TextField nameField;
    private String nameString;
    private UnicodeFont uniFontMessage;
    private Font font = new Font("Verdana", Font.BOLD, 32);
    private TrueTypeFont ttf = new TrueTypeFont(font, true);


    public ScoreBoardMenuGUI(GameContainer container, ScoreBoardState state, Screen screen) throws SlickException {
        super(container, screen);
        this.state = state;



    }

    @Override
    public void render() {


    }



    @Override
    public void componentActivated(AbstractComponent source) {

    }
}
