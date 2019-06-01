package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.SingleplayerReplayMenuState;
import java.awt.Color;
import java.awt.Font;

public class SingleplayerReplayMenuGUI extends AbstractMenuGUI {

    private MouseOverArea replayButton;
    private MouseOverArea backButton;
    private SingleplayerReplayMenuState state;
    private String scoreName;
    private String points;
    private UnicodeFont uniFontMessage;
    private int buttonWidth;
    private int buttonHeight;
    private Font font = new Font("Nadeem", Font.CENTER_BASELINE, 45);


    public SingleplayerReplayMenuGUI(GameContainer container, SingleplayerReplayMenuState state, Screen screen) throws SlickException {
        super(container, screen);
        this.state=state;
        buttonHeight = container.getHeight()/10;
        buttonWidth = container.getWidth()/3;

        Image replayImage = new Image("res/sprites/buttons/replay.png").getScaledCopy(buttonWidth, buttonHeight);

        replayButton = new MouseOverArea(container, replayImage, container.getWidth()-3*buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

        Image backImage = new Image("res/sprites/buttons/back.png").getScaledCopy(buttonWidth, buttonHeight);

        backButton = new MouseOverArea(container, backImage, container.getWidth()-buttonWidth, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);

      /*  uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(Color.black));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        scoreName = state.getScoreBoard().printName();
        points = state.getScoreBoard().printPoint();*/
    }

    public void render(){
        replayButton.render(getContainer(),getContainer().getGraphics());
        backButton.render(getContainer(),getContainer().getGraphics());
       // uniFontMessage.drawString(getContainer().getWidth()/3-uniFontMessage.getWidth(scoreName)/4, 16*getContainer().getHeight()/100f, scoreName);
        //uniFontMessage.drawString(3*getContainer().getWidth()/4-uniFontMessage.getWidth(scoreName)/2, 16*getContainer().getHeight()/100f, points);

    }
    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == backButton)
            state.noRematch();
        else if (source == replayButton)
            state.rematch();
    }
}
