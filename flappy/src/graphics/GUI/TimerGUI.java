package graphics.GUI;

import graphics.Canvas;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.awt.*;

public class TimerGUI {

    private Canvas canvas;
    private Image background;
    private String timer;
    private UnicodeFont uniFontMessage;
    private float imageWidth;
    private float imageHeight;


    public TimerGUI(Canvas canvas) throws SlickException {
        this.canvas = canvas;

        imageWidth = 0.125f;
        imageHeight = 0.07f;

        background = new Image(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.BUTTON_BG));

        Font font = new Font("Comic Sans MS", Font.BOLD, 3*canvas.getScreen().getWidth()/100 /*46*/);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.green));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();

    }

    public void render(int time){
        timer = Integer.toString(time);
        canvas.drawImage(background,0.5f - imageWidth/2f, 0,imageWidth,imageHeight);
        canvas.drawString(timer,uniFontMessage,0.5f - ((float)uniFontMessage.getWidth(timer)/canvas.getScreen().getWidth())/2f, 0.03f);
    }
}
