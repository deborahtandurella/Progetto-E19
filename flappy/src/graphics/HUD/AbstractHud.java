package graphics.HUD;

import graphics.Canvas;
import logic.player.Player;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class AbstractHud {
    private Player player;
    private Canvas canvas;
    private UnicodeFont font;

    AbstractHud(Player player, Canvas canvas) throws SlickException {
        this.player = player;
        this.canvas = canvas;
        font = new UnicodeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, canvas.getScreen().getWidth()/10 /*46*/));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();
        font.loadGlyphs();
    }
    public void render(){
        canvas.drawString(String.valueOf(player.getScore()),font,  0.5f, 0.05f);
    }

    protected Player getPlayer() {
        return player;
    }

    protected Canvas getCanvas() {
        return canvas;
    }

    protected UnicodeFont getFont() {
        return font;
    }
}
