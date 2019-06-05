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
    private Image pergamena;


    public ScoreBoardMenuGUI(GameContainer container, Screen screen, ScoreInterface state) throws SlickException {
        super(container, screen);
        this.state=state;
        pergamena = new Image("res/sprites/backgrounds/pergamena.png").getScaledCopy(container.getWidth()/100*43, container.getHeight()/100*80);
        Font font = new Font("Comic Sans MS", Font.BOLD, 27*getContainer().getWidth()/1000);
        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.black));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        scoreName = state.getScoreBoard().printName();
        points = state.getScoreBoard().printPoint();

    }

    @Override
    public void render() {
        pergamena.draw(getContainer().getWidth()/2f-pergamena.getWidth()/2f, 5*getContainer().getHeight()/100f);

        uniFontMessage.drawString(37*getContainer().getWidth()/100f, 16*getContainer().getHeight()/100f, scoreName);
        uniFontMessage.drawString(61*getContainer().getWidth()/100f, 16*getContainer().getHeight()/100f, points);

    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
    }
}

