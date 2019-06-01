package graphics.GUI;

import graphics.Screen;
import logic.SinglePlayer.Record;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.Menu;

public class MenuGUI extends AbstractMenuGUI {
    private Menu state;
    private MouseOverArea singleButton;
    private MouseOverArea multiButton;
    private Record record;
    private int buttonWidth;
    private int buttonHeight;
    private MouseOverArea leaderboardButton;

    public MenuGUI(GameContainer container, Screen screen, Menu state, Record record) throws SlickException {
        super(container, screen);
        this.state = state;
        this.record = record;
        buttonHeight = container.getHeight()/6;
        buttonWidth = container.getWidth()/3;
        Image single = new Image("res/sprites/buttons/single.png");
        singleButton = new Button(container,screen, single,  0.5, this);
        Image multi = new Image("res/sprites/buttons/multi.png");
        multiButton = new Button(container,screen, multi,  0.3, this);

        Image leaderBoard = new Image("res/sprites/buttons/leaderboard.png").getScaledCopy(buttonWidth/2, buttonHeight);
        leaderboardButton = new MouseOverArea(container, leaderBoard, container.getWidth()/2-buttonWidth/4, 11*container.getHeight()/100, buttonWidth/2, buttonHeight, this);

    }

    public void render(){
        singleButton.render(getContainer(),getContainer().getGraphics());
        multiButton.render(getContainer(),getContainer().getGraphics());
        leaderboardButton.render(getContainer(), getContainer().getGraphics());
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == singleButton ) {
                state.single();
            }
        if(source == multiButton){
            state.multi();
        }
        if (source == leaderboardButton ) {
            state.leaderBoard();
        }
    }
}
