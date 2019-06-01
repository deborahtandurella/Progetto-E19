package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import states.ScoreBoardState;
import states.SingleplayerReplayMenuState;
import states.test.ScoreInterface;

import java.awt.Color;
import java.awt.Font;


public class ScoreBoardMenuGUI extends AbstractMenuGUI {

   // private MouseOverArea deleteButton;
   // private MouseOverArea backButton;
    private ScoreInterface state;

    private String scoreName;
    private String points;
    private UnicodeFont uniFontMessage;
  //  private int buttonWidth;
   // private int buttonHeight;
    private Font font = new Font("Nadeem", Font.CENTER_BASELINE, 45);

    public ScoreBoardMenuGUI(GameContainer container, ScoreInterface state, Screen screen) throws SlickException {
        super(container, screen);
        this.state=state;
       // buttonHeight = container.getHeight()/10;
        //buttonWidth = container.getWidth()/3;

       // Image deleteImage = new Image("res/sprites/buttons/replay.png").getScaledCopy(buttonWidth, buttonHeight);

      //  deleteButton = new MouseOverArea(container, deleteImage, container.getWidth()-3*buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        //Image backImage = new Image("res/sprites/buttons/back.png").getScaledCopy(buttonWidth, buttonHeight);

       // backButton = new MouseOverArea(container, backImage, container.getWidth()-buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.black));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        scoreName = state.getScoreBoard().printName();
        points = state.getScoreBoard().printPoint();

    }

    @Override
    public void render() {
        //deleteButton.render(getContainer(),getContainer().getGraphics());
        //backButton.render(getContainer(),getContainer().getGraphics());
        uniFontMessage.drawString(getContainer().getWidth()/3-uniFontMessage.getWidth(scoreName)/4, 16*getContainer().getHeight()/100f, scoreName);
        uniFontMessage.drawString(3*getContainer().getWidth()/4-uniFontMessage.getWidth(scoreName)/2, 16*getContainer().getHeight()/100f, points);

    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {

    }
}

