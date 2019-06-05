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
    private Font font;
    private String title;
    private UnicodeFont versionFont;

    public ScoreBoardMenuGUI(GameContainer container, Screen screen, ScoreInterface state) throws SlickException {
        super(container, screen);
        this.state=state;
        font = new Font("Comic Sans MS", Font.BOLD, 3*getContainer().getWidth()/100);

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.black));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        scoreName = state.getScoreBoard().printName();
        points = state.getScoreBoard().printPoint();

        title = "TOP TEN";
        versionFont = new UnicodeFont("res/font/FlappyBirdy.ttf", getContainer().getHeight()/5, false, false);
        versionFont.addAsciiGlyphs();
        versionFont.getEffects().add(new ColorEffect(Color.BLACK));
        versionFont.loadGlyphs();


    }

    @Override
    public void render() {

        uniFontMessage.drawString(36*getContainer().getWidth()/100, 24*getContainer().getHeight()/100f, scoreName);
        uniFontMessage.drawString(60*getContainer().getWidth()/100, 24*getContainer().getHeight()/100f, points);

        getContainer().getGraphics().setFont(versionFont);
        getContainer().getGraphics().drawString(title, 35*getContainer().getWidth()/100 , 8 * getContainer().getHeight() / 100);


    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }
}

