package graphics.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;

import java.awt.*;

public class TextGUI {
    private TextField text;
    private UnicodeFont font;
    private int x;
    private int y;
    private int width;
    private int height;

    public TextGUI(int x, int y, int width, int height){
        this.x = x;
        this.y =y;
        this.width = width;
        this.height = height;
    }

    public void init(GameContainer gc) throws SlickException
    {
        font = getNewFont("Verdana" ,  3*gc.getWidth()/100);
        text = new TextField(gc , font , x*gc.getWidth()/100 , y*gc.getHeight()/100 , width*gc.getWidth()/100 , height*gc.getHeight()/100);
        text.setBackgroundColor(org.newdawn.slick.Color.white);
    }


    private UnicodeFont getNewFont(String fontName , int fontSize)
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
    public void setConsumeEvents(boolean focus){
        text.setConsumeEvents(focus);
    }
    public void update() throws SlickException {
        font.loadGlyphs();
    }


    public String getString(){
        return text.getText();
    }
}
