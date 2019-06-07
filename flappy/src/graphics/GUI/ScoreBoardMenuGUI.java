package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;
import states.test.ScoreInterface;
import java.awt.Color;
import java.awt.Font;

public class ScoreBoardMenuGUI extends AbstractMenuGUI {

    private ScoreInterface state;
    private Image pergamena;
    private String scoreName;
    private String newRecord;
    private String points;
    private UnicodeFont uniFontMessage;
    private UnicodeFont versionFont;


    public ScoreBoardMenuGUI(GameContainer container, Screen screen, ScoreInterface state) throws SlickException {
        super(container, screen);
        this.state=state;

        pergamena = new Image(PathHandler.getInstance().getPath(FileKeys.VARIOUS, PathKeys.RANKBACKGROUND)).getScaledCopy(container.getWidth()/100*43, container.getHeight()/100*80);
        Font font = new Font("Comic Sans MS", Font.BOLD, 27*getContainer().getWidth()/1000);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.black));
        uniFontMessage.getEffects().add(new ColorEffect(Color.red));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        scoreName = state.getScoreBoard().printName();
        points = state.getScoreBoard().printPoint();
        newRecord = "NEW RECORD";

        versionFont = new UnicodeFont("res/font/FlappyBirdy.ttf", getContainer().getHeight()/10, false, false);
        versionFont.addAsciiGlyphs();
        versionFont.getEffects().add(new ColorEffect(Color.RED));
        versionFont.loadGlyphs();

    }

    @Override
    public void render() {

        pergamena.draw(getContainer().getWidth()/2f-pergamena.getWidth()/2f, 3*getContainer().getHeight()/100f);
        uniFontMessage.drawString(37*getContainer().getWidth()/100f, 18*getContainer().getHeight()/100f, scoreName, org.newdawn.slick.Color.black);
        uniFontMessage.drawString(61*getContainer().getWidth()/100f, 18*getContainer().getHeight()/100f, points, org.newdawn.slick.Color.black);
        if(state.getScoreBoard().getnewRecord()){

            getContainer().getGraphics().setFont(versionFont);
            getContainer().getGraphics().drawString(newRecord, 37*getContainer().getWidth()/100f , 11 * getContainer().getHeight() / 100f);
        }
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
    }
}

