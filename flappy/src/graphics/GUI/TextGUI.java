package graphics.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

public class TextGUI {
    private TextField text;
    private UnicodeFont font;

    public void init(GameContainer gc) throws SlickException
    {
        font = getNewFont("Verdana" ,  4*gc.getWidth()/100);
        text = new TextField(gc , font , 28*gc.getWidth()/100 , 25*gc.getHeight()/100 , 40*gc.getWidth()/100 , 5*gc.getHeight()/100);
        text.setBackgroundColor(org.newdawn.slick.Color.white);
    }


    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        UnicodeFont font = new UnicodeFont(new Font(fontName , Font.BOLD , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        return font;
    }

    public void render(GameContainer gc ,  org.newdawn.slick.Graphics g) throws SlickException
    {
        text.render(gc , g);
        g.setFont(font);
    }

    public void update() throws SlickException {
        font.loadGlyphs();
        //enter();
    }

    public void enter() throws SlickException
    {
        text.setText("vgvg");
    }
}
