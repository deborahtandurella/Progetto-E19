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
    private String positions;

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
        positions = "1.\n2.\n3.\n4.\n5.\n6.\n7.\n8.\n9.\n10.\n";


    }

    @Override
    public void render() {
        uniFontMessage.drawString(40*getContainer().getWidth()/100, 16*getContainer().getHeight()/100f, positions);
        uniFontMessage.drawString(45*getContainer().getWidth()/100, 16*getContainer().getHeight()/100f, scoreName);
        uniFontMessage.drawString(60*getContainer().getWidth()/100, 16*getContainer().getHeight()/100f, points);
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }
}

