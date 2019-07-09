package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;
import states.MultiplayerMenu;

import java.awt.Font;

public class MultiplayerMenuGUI extends AbstractMenuGUI {

    private MouseOverArea backButton;
    private MouseOverArea hostButton;
    private MouseOverArea joinButton;
    private MultiplayerMenu state;
    private UnicodeFont uniFontMessage;
    private TextGUI ipField;
    private TextGUI clientPortField;
    private TextGUI hostPortField;
    private String ipString;
    private String clientPortString;
    private String hostPortString;
    private String clientErrorMessage;
    private String hostErrorMessage;
    private boolean hostError = false;
    private boolean clientError = false;

    public MultiplayerMenuGUI(GameContainer container, Screen screen, MultiplayerMenu state) throws SlickException {
        super(container, screen);
        this.state = state;

        setBackground();

        Font font = new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 3*getContainer().getWidth()/100);

        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        Image backImage = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.BACKTOMENUBUTTON1)).getScaledCopy(buttonWidth, buttonHeight);
        Image hostImage = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.HOSTBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        Image joinImage = new Image(PathHandler.getInstance().getPath(FileKeys.BUTTON, PathKeys.JOINBUTTON)).getScaledCopy(buttonWidth, buttonHeight);

        hostButton = new MouseOverArea(container, hostImage, 50*(container.getWidth()-buttonWidth)/100, 66*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);
        joinButton = new MouseOverArea(container, joinImage, 50*(container.getWidth()-buttonWidth)/100, 42*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);
        backButton = new MouseOverArea(container, backImage, 50*(container.getWidth()-buttonWidth)/100, 90*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);

        ipField = new TextGUI(20, 15, 29, 5);
        clientPortField = new TextGUI(20, 25,10, 5);
        hostPortField = new TextGUI(20, 48, 10, 5);

        ipField.init(getContainer());
        hostPortField.init(getContainer());
        clientPortField.init(getContainer());

        addButton(backButton);
        addButton(hostButton);
        addButton(joinButton);

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.black));
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.red));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();

        ipString = "IP Address";
        clientPortString ="Port";
        hostPortString = clientPortString;
        deactivate();

    }

    @Override
    public void render() {
        renderBackground();
        renderButtons();

        uniFontMessage.drawString(20 * getContainer().getWidth() /100f, 9 * getContainer().getHeight() / 100f, ipString, Color.black);
        uniFontMessage.drawString(20 * getContainer().getWidth() / 100f, 20 * getContainer().getHeight() / 100f, clientPortString, Color.black);
        uniFontMessage.drawString(20 * getContainer().getWidth() / 100f, 43 * getContainer().getHeight() / 100f, hostPortString, Color.black);
        if (clientError){
            uniFontMessage.drawString(44 * (getContainer().getWidth() - uniFontMessage.getWidth(clientErrorMessage)) / 100f, 33 * getContainer().getHeight() / 100f, clientErrorMessage, Color.red);
        }
        if(hostError){
            uniFontMessage.drawString(34 * (getContainer().getWidth() - uniFontMessage.getWidth(hostErrorMessage)) / 100f, 57 * getContainer().getHeight() / 100f, hostErrorMessage, Color.red);
        }

        try {
            ipField.render(getContainer(), getContainer().getGraphics());
            clientPortField.render(getContainer(), getContainer().getGraphics());
            hostPortField.render(getContainer(), getContainer().getGraphics());
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
    public void activate(){
        ipField.setConsumeEvents(true);
        clientPortField.setConsumeEvents(true);
        hostPortField.setConsumeEvents(false);
    }
    public void deactivate(){
        ipField.setConsumeEvents(false);
        clientPortField.setConsumeEvents(false);
        hostPortField.setConsumeEvents(false);
    }
    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == hostButton) {
            if (hostPortField.getString().equals("")) {
                hostError = true;
                hostErrorMessage = "Inserisci la port, stupido host!";
            }  else {
                clientError = false;
                hostError = false;
                state.host(Integer.parseInt(hostPortField.getString()));
            }
        }

        if(source == joinButton) {
            if (ipField.getString().equals("") || clientPortField.getString().equals("")) {
                clientError = true;
                clientErrorMessage = "Inserisci l'IP e la Port, stupido rassista";
            }
            else {
                clientError = false;
                hostError = false;
                state.join(ipField.getString(),Integer.parseInt(clientPortField.getString()));
            }
        }

        if (source == backButton) {
            clientError = false;
            hostError = false;
            state.backToMenu();
        }
    }
    public void update() throws SlickException {
        ipField.update();
        clientPortField.update();
        hostPortField.update();
    }
}
