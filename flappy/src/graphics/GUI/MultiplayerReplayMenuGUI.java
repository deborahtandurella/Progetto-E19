package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.PathHandler;
import resources.ResourcePacks;
import resources.Resources;
import states.MultiplayerReplayMenu;

public class MultiplayerReplayMenuGUI extends AbstractMenuGUI {

    private MouseOverArea backButton;
    private MultiplayerReplayMenu state;
    private String winString = "Sei grande, hai vinto!";
    private String loseString = "Peccato, hai perso!";
    private String pareggioString = "La partita è finita in pareggio";
    private UnicodeFont unicodeMessage;
    private String localPlayer;
    private String remotePlayer;
    private String localPlayerScore;
    private String remotePlayerScore;

    public MultiplayerReplayMenuGUI(GameContainer container, Screen screen, MultiplayerReplayMenu state) throws SlickException {
        super(container, screen);
        this.state = state;
        setBackground();

        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        java.awt.Font font = new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 3*getContainer().getWidth()/100);

        unicodeMessage= new UnicodeFont(font);
        unicodeMessage.getEffects().add(new ColorEffect(java.awt.Color.black));
        unicodeMessage.getEffects().add(new ColorEffect(java.awt.Color.red));
        unicodeMessage.addAsciiGlyphs();
        unicodeMessage.loadGlyphs();
        Image backImage = new Image(PathHandler.getInstance().getPath(ResourcePacks.BUTTON, Resources.BACKTOMENUBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        backButton = new MouseOverArea(container, backImage, (container.getWidth() - buttonWidth) / 2, container.getHeight()-2*buttonHeight, buttonWidth, buttonHeight, this);
        //singleButton = new MouseOverArea(container, single, 25 * container.getWidth() / 100, 50 * container.getHeight() / 100 , this);

        addButton(backButton);
    }

    @Override
    public void reload() {
        super.reload();

        localPlayer = state.getLocalPlayerResult().getName();
        remotePlayer = state.getRemotePlayerResult().getName();
        localPlayerScore = String.valueOf(state.getLocalPlayerResult().getScore());
        remotePlayerScore = String.valueOf(state.getRemotePlayerResult().getScore());
    }

    @Override
    public void render() throws SlickException {
        renderBackground();
        renderButtons();
        if(state.getLocalPlayerResult().getScore() > state.getRemotePlayerResult().getScore()){
            unicodeMessage.drawString(20 * getContainer().getWidth() /100f, 10 * getContainer().getHeight() / 100f, winString, Color.black);
        }
        else if(state.getLocalPlayerResult().getScore() < state.getRemotePlayerResult().getScore()){
            unicodeMessage.drawString(20 * getContainer().getWidth() /100f, 20 * getContainer().getHeight() / 100f, loseString, Color.black);
        }
        else{
            unicodeMessage.drawString(20 * getContainer().getWidth() /100f, 40 * getContainer().getHeight() / 100f, pareggioString, Color.black);
        }
        unicodeMessage.drawString(20 * getContainer().getWidth() /100f, 60 * getContainer().getHeight() / 100f, localPlayer, Color.black);
        unicodeMessage.drawString(20 * getContainer().getWidth() /100f, 70 * getContainer().getHeight() / 100f, remotePlayer, Color.black);
        unicodeMessage.drawString(35 * getContainer().getWidth() /100f, 60 * getContainer().getHeight() / 100f, localPlayerScore, Color.black);
        unicodeMessage.drawString(35 * getContainer().getWidth() /100f, 70 * getContainer().getHeight() / 100f, remotePlayerScore, Color.black);
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == backButton){
            state.backToMenu();
        }

    }
}
