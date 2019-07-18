package graphics.HUD;

import graphics.Canvas;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.awt.*;

/**
 *  Mostra un timer
 */
public class TimerHud {

    private Canvas canvas;
    private Image background;
    private UnicodeFont uniFontMessage;
    private float imageWidth;
    private float imageHeight;


    public TimerHud(Canvas canvas) throws SlickException {
        this.canvas = canvas;

        imageWidth = 0.125f;
        imageHeight = 0.09f;

        background = new Image(PathHandler.getInstance().getPath(ResourcePack.VARIOUS, Resource.TIMER));

        Font font = new Font("Comic Sans MS", Font.BOLD, (int) (canvas.getScreen().getWidth()*0.04) /*46*/);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.white));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();

    }

    public void render(int time){

        canvas.drawImage(background,0.5f - imageWidth/2f, 0.003f,imageWidth,imageHeight);
        canvas.drawStringCentered(Integer.toString(time),uniFontMessage,0.52f , 0.04f);
    }
}
