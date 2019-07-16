package utilities;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;

public class FontUtility {
    public static UnicodeFont makeFont(int size){
        UnicodeFont font = new UnicodeFont(new Font("Comic Sans MS", Font.BOLD, size));
        font.getEffects().add(new ColorEffect(Color.WHITE));
        font.addAsciiGlyphs();
        try {
            font.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return font;
    }
}
