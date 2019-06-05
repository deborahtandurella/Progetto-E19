package graphics.GUI;

import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import states.MultiplayerMenu;

public class MultiplayerMenuGUI extends AbstractMenuGUI {

    private MouseOverArea backButton;
    private MouseOverArea hostButton;
    private MouseOverArea joinButton;
    private MultiplayerMenu state;
    private int buttonWidth;
    private int buttonHeight;
    private java.awt.Font font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 3*getContainer().getWidth()/100 /*46*/);
    private UnicodeFont uniFontMessage;
    private TextGUI ipField;
    private TextGUI portField;
    private String ipString;
    private String portString;
    private String errorMessage;
    private boolean error = false;

    public MultiplayerMenuGUI(GameContainer container, Screen screen, MultiplayerMenu state) throws SlickException {
        super(container, screen);
        this.state = state;

        font = new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 3*getContainer().getWidth()/100);

        buttonHeight = container.getHeight()/10;
        buttonWidth = container.getWidth()/3;
        Image backImage = new Image("res/sprites/buttons/back1.png").getScaledCopy(buttonWidth, buttonHeight);
        backButton = new MouseOverArea(container, backImage, 65*container.getWidth()/100-buttonWidth, 90*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);
        Image hostImage = new Image("res/sprites/buttons/host.png").getScaledCopy(buttonWidth, buttonHeight);
        hostButton = new MouseOverArea(container, hostImage, 85*container.getWidth()/100-buttonWidth, 60*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);
        Image joinImage = new Image("res/sprites/buttons/join.png").getScaledCopy(buttonWidth, buttonHeight);
        joinButton = new MouseOverArea(container, joinImage, 45*container.getWidth()/100-buttonWidth, 60*container.getHeight()/100-2*buttonHeight, buttonWidth, buttonHeight, this);
        ipField = new TextGUI(28, 15, 40, 5);
        ipField.init(getContainer());
        portField = new TextGUI(28, 25,20, 5);
        portField.init(getContainer());

        uniFontMessage = new UnicodeFont(font);
        uniFontMessage.getEffects().add(new ColorEffect(java.awt.Color.black));
        uniFontMessage.addAsciiGlyphs();
        uniFontMessage.loadGlyphs();
        ipString = "IP Address";
        portString ="Port";
    }

    @Override
    public void render() {
        backButton.render(getContainer(), getContainer().getGraphics());
        hostButton.render(getContainer(), getContainer().getGraphics());
        joinButton.render(getContainer(), getContainer().getGraphics());
        uniFontMessage.drawString(30*getContainer().getWidth() /100, 9 * getContainer().getHeight() / 100f, ipString);
        uniFontMessage.drawString(30*getContainer().getWidth() / 100, 20 * getContainer().getHeight() / 100f, portString);
        if (error) {
            uniFontMessage.drawString(getContainer().getWidth() / 2 - uniFontMessage.getWidth(errorMessage) / 2,
                    60 * getContainer().getHeight() / 100f, errorMessage, Color.red);
        }

        try {
            ipField.render(getContainer(), getContainer().getGraphics());
            portField.render(getContainer(), getContainer().getGraphics());
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == joinButton || source == hostButton) {
            if (ipField.getString().equals("") || portField.getString().equals("")) {
                error = true;
                errorMessage = "Inserisci l'IP e la Port, stupido razzista celiaco";
            } else if (ipField.getString().length() != 15) {
                error = true;
                errorMessage = "boh ce, non sai scrivere un ip, sei un fra";
            } else if (portField.getString().length() != 4) {
                error = true;
                errorMessage = "chi è quel mona che sbatte la porta!?";
            }
            //c'è da fare il cambiamento di stato
        }

        if (source == backButton)
            state.backToMenu();
    }
    public void update() throws SlickException {
        ipField.update();
        portField.update();
    }


}
