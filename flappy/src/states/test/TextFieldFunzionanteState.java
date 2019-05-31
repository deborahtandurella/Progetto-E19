package states.test;

import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TextFieldFunzionanteState
        extends BasicGameState
{
    private TextField text;
    private UnicodeFont font;
    private StateBasedGame sbg;
    private GameContainer container;


    public TextFieldFunzionanteState(int state)
    {
    }

    public void init(GameContainer gc , StateBasedGame sbg)
            throws SlickException
    {
        this.sbg=sbg;
        this.container= gc;
        font = getNewFont("Verdana" ,  38);
        text = new TextField(gc , font , 150 , 270 , 200 , 35);
        text.setBackgroundColor(Color.white);
    }

    public void render(GameContainer gc , StateBasedGame sbg , Graphics g)
            throws SlickException
    {
        text.render(gc , g);
        g.setFont(font);
    }

    public void update(GameContainer gc , StateBasedGame sbg , int delta)
            throws SlickException
    {
        font.loadGlyphs();
    }

    public int getID()
    {
        return 9;
    }

    public void enter(GameContainer gc , StateBasedGame sbg)
            throws SlickException
    {
        text.setText("");
    }

    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        UnicodeFont font = new UnicodeFont(new Font(fontName , Font.BOLD , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        return font;
    }
    public void keyPressed(int key, char c){
        if( key == Input.KEY_SPACE){
                try {
                    sbg.getState(2).init(container,sbg);
                } catch (SlickException e) {
                    e.printStackTrace();
                }
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());

        }
    }
}