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
import states.Menu;

import java.awt.*;
import java.awt.Font;

public class MenuGUI extends AbstractMenuGUI {
    private Menu state;
    private MouseOverArea singleButton;
    private MouseOverArea multiButton;
    private TextField nameField;
    private String nameString;
    private UnicodeFont uniFontMessage;
    private Font font = new Font("Verdana", Font.BOLD, 38);
    private TrueTypeFont ttf = new TrueTypeFont(font, true);
    private String errorMessage;
    private boolean error = false;
    private Record record;

    public MenuGUI(GameContainer container, Screen screen, Menu state, Record record) throws SlickException {
        super(container, screen);
        this.state = state;
        this.record = record;
        Image single = new Image("res/Images/single.png");
        singleButton = new Button(container,screen, single,  0.3, this);
        Image multi = new Image("res/Images/multi.png");
        multiButton = new Button(container,screen, multi,  0.5, this);
        nameField = new TextField(container, ttf, 34*container.getWidth()/100, 14*container.getHeight()/100, 450, 60);
        nameField.setBackgroundColor(Color.white);
        nameField.setTextColor(Color.black);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.white));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        nameString = "NICKNAME:";
    }

    public void render(){
        singleButton.render(getContainer(),getContainer().getGraphics());
        multiButton.render(getContainer(),getContainer().getGraphics());
        if(record.getLogin()){
        nameField.render(getContainer(), getContainer().getGraphics());
        uniFontMessage.drawString(41*getContainer().getWidth()/100f, 7*getContainer().getHeight()/100f, nameString);
        if (error) {
            uniFontMessage.drawString((getContainer().getWidth() - uniFontMessage.getWidth(errorMessage))/2f,
                    20*getContainer().getHeight()/100f, errorMessage, Color.red);
        }}
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == singleButton ) {

            if(nameField.getText().equals("")){
                error = true;
                errorMessage = "Inserisci il tuo nickname!";

            }  else{
                record.setLogin(false);
                state.single();
            }



        }
        if(source == multiButton){
            state.multi();
        }
    }
}
