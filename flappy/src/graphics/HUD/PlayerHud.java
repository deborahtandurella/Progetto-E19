package graphics.HUD;

import game.player.Player;
import graphics.Canvas;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 *  Hud del giocatore, mostra il punteggio del giocatore
 */
public abstract class PlayerHud {
    private Player player;
    private Canvas canvas;
    private UnicodeFont font;

    PlayerHud(Player player, Canvas canvas) throws SlickException {
        this.player = player;
        this.canvas = canvas;
        font = new UnicodeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, canvas.getScreen().getWidth()/10 /*46*/));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();
        font.loadGlyphs();
    }
    public void render(){
        canvas.drawStringCentered(String.valueOf(player.getScore()),font,  0.5f, 0.05f);
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
