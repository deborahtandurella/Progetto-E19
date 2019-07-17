package states.menu;

import Main.FlappyGameState;
import graphics.GUI.CustomizationMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import resources.PathHandler;
import resources.ResourcePack;

import java.awt.*;
import java.io.IOException;

public class CustomizationMenu extends AbstractMenuState {
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return FlappyGameState.CUSTOMIZATION_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame= stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        try {
            setGui(new CustomizationMenuGUI(gameContainer, screen,this));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    public void goBack(){
        initStates();
        stateBasedGame.enterState(FlappyGameState.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void themeSelected(ResourcePack theme) {
        PathHandler.getInstance().changeSprites(theme);
        initStates();
    }

    private void initStates() {
        try{
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.GENERAL_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.DIFFICULTY_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.SINGLE_REPLAY_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.MULTI_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.MULTI_LOADING)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.SCORE_BOARD_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.CONNECTION_ERROR_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyGameState.MULTI_END_MENU)).reloadTheme();
        } catch (SlickException e ){
            e.printStackTrace();
        }

    }


}
