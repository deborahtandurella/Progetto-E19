package graphics.GUI;

import GameScore.ScoreBoard;
import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.SingleplayerReplayMenuState;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;

public class SingleplayerReplayMenuGUI extends AbstractMenuGUI {

    private MouseOverArea replayButton;
    private MouseOverArea backButton;
    private SingleplayerReplayMenuState state;
    private String scoreString;
    private UnicodeFont uniFontMessage;
    private Font font = new Font("Verdana", Font.BOLD, 38);
    private TrueTypeFont ttf = new TrueTypeFont(font, true);


    public SingleplayerReplayMenuGUI(GameContainer container, SingleplayerReplayMenuState state, Screen screen) throws SlickException {
        super(container, screen);
        this.state=state;
        Image replayImage = new Image("res/sprites/buttons/replay.png");
        replayButton = new Button(container,screen, replayImage,  0.7, this);
        Image backImage = new Image("res/sprites/buttons/back.png");
        backButton = new Button(container,screen, backImage,  0.8, this);

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.BLUE));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        scoreString = state.getScoreBoard().printScore();


    }
    public void render(){
        replayButton.render(getContainer(),getContainer().getGraphics());
        backButton.render(getContainer(),getContainer().getGraphics());
        uniFontMessage.drawString(getContainer().getWidth()/2-uniFontMessage.getWidth(scoreString)/2, 7*getContainer().getHeight()/100f, scoreString);


    }
    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == backButton)
            state.noRematch();
        else if (source == replayButton)
            state.rematch();
    }
}
