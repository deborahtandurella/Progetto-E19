package graphics.GUI;

import graphics.Screen;

import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import states.Menu;

public class MenuGUI extends AbstractMenuGUI {
    private Menu state;
    private MouseOverArea singleButton;
    private MouseOverArea multiButton;
    private MouseOverArea customButton;
   // private Record record;
    private MouseOverArea leaderboardButton;

    public MenuGUI(GameContainer container, Screen screen, Menu state) throws SlickException {
        super(container, screen);
        this.state = state;
       // this.record = record;
        int buttonHeight = container.getHeight()/6;
        int buttonWidth = container.getWidth()/3;

        Image single = new Image("res/sprites/buttons/single.png").getScaledCopy(3*buttonWidth/2, 80*buttonHeight/100);
        singleButton = new MouseOverArea(container, single, 25*container.getWidth()/100, 50*container.getHeight()/100 , this);

        Image multi = new Image("res/sprites/buttons/multi.png").getScaledCopy(3*buttonWidth/2, 80*buttonHeight/100);
        multiButton = new MouseOverArea(container, multi, 25*container.getWidth()/100, 30*container.getHeight()/100, this);

        Image leaderBoard = new Image("res/sprites/buttons/leaderboard.png").getScaledCopy(buttonWidth/2, buttonHeight);
        leaderboardButton = new MouseOverArea(container, leaderBoard, container.getWidth()/2-buttonWidth/4, 10*container.getHeight()/100, buttonWidth/2, buttonHeight, this);

        Image custom = new Image("res/sprites/buttons/customthemes.png").getScaledCopy(buttonWidth, buttonHeight/2);
        customButton = new MouseOverArea(container, custom, container.getWidth()/2-buttonWidth/2, 80*container.getHeight()/100, buttonWidth, buttonHeight/2, this);

        addButton(singleButton);
        addButton(multiButton);
        addButton(leaderboardButton);
        addButton(customButton);
    }

    public void render(){
        renderButtons();
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
        if (source == customButton ) {
            state.custom();
        }
    }
}
