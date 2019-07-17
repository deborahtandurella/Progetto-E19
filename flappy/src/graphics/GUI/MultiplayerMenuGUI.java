package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import states.menu.MultiplayerMenu;

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
    private static final String ipString = "IP Address";
    private static final String portString = "Port";
    private static final String clientErrorMessage = "Insert valid Ip address and Port first";
    private static final String hostErrorMessage = "Insert valid Port first";
    private boolean hostError = false;
    private boolean clientError = false;

    public MultiplayerMenuGUI(GameContainer container, Screen screen, MultiplayerMenu state) throws SlickException {
        super(container, screen);
        this.state = state;
        Font font = new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 3*getContainer().getWidth()/100);

        int buttonHeight = container.getHeight()/10;
        int buttonWidth = container.getWidth()/3;

        Image backImage = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.BACKTOMENUBUTTON1)).getScaledCopy(buttonWidth, buttonHeight);
        Image hostImage = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.HOSTBUTTON)).getScaledCopy(buttonWidth, buttonHeight);
        Image joinImage = new Image(PathHandler.getInstance().getPath(ResourcePack.BUTTON, Resource.JOINBUTTON)).getScaledCopy(buttonWidth, buttonHeight);

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

        deactivate();

    }

    @Override
    public void render() {
        super.render();
        uniFontMessage.drawString(20 * getContainer().getWidth() /100f, 9 * getContainer().getHeight() / 100f, ipString, Color.black);
        uniFontMessage.drawString(20 * getContainer().getWidth() / 100f, 20 * getContainer().getHeight() / 100f, portString, Color.black);
        uniFontMessage.drawString(20 * getContainer().getWidth() / 100f, 43 * getContainer().getHeight() / 100f, portString, Color.black);
        if (clientError){
            uniFontMessage.drawString(44 * (getContainer().getWidth() - uniFontMessage.getWidth(clientErrorMessage)) / 100f, 33 * getContainer().getHeight() / 100f, clientErrorMessage, Color.red);
        }
        if(hostError){
            uniFontMessage.drawString(34 * (getContainer().getWidth() - uniFontMessage.getWidth(hostErrorMessage)) / 100f, 57 * getContainer().getHeight() / 100f, hostErrorMessage, Color.red);
        }

        ipField.render(getContainer(), getContainer().getGraphics());
        clientPortField.render(getContainer(), getContainer().getGraphics());
        hostPortField.render(getContainer(), getContainer().getGraphics());

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
            if (hostPortField.getString().replaceAll("[\\D]", "").equals("")) {
                hostError = true;
                clientError=false;
            }  else {
                clientError = false;
                hostError = false;
                state.host(Integer.parseInt(hostPortField.getString().replaceAll("[\\D]", "")));
            }
        }

        if(source == joinButton) {
            if (ipField.getString().equals("") || clientPortField.getString().replaceAll("[\\D]", "").equals("")) {
                clientError = true;
                hostError= false;
            }
            else {
                clientError = false;
                hostError = false;
                state.join(ipField.getString(),Integer.parseInt(clientPortField.getString().replaceAll("[\\D]", "")));
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
