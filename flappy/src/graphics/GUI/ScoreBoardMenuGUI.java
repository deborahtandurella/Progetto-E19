package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import states.test.ScoreInterface;

import java.awt.Color;
import java.awt.Font;


public class ScoreBoardMenuGUI extends AbstractMenuGUI {

    private ScoreInterface state;
    private String scoreName;
    private String points;
    private UnicodeFont uniFontMessage;

    private Font font = new Font("Nadeem", Font.CENTER_BASELINE, 45);

    public ScoreBoardMenuGUI(GameContainer container, ScoreInterface state, Screen screen) throws SlickException {
        super(container, screen);
        this.state=state;
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.black));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        scoreName = state.getScoreBoard().printName();
        points = state.getScoreBoard().printPoint();

    }

    @Override
    public void render() {
        uniFontMessage.drawString(getContainer().getWidth()/3-uniFontMessage.getWidth(scoreName)/4, 16*getContainer().getHeight()/100f, scoreName);
        uniFontMessage.drawString(3*getContainer().getWidth()/4-uniFontMessage.getWidth(scoreName)/2, 16*getContainer().getHeight()/100f, points);
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }
}

