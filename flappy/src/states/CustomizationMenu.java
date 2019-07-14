package states;

import Main.GiocoAStati;
import graphics.GUI.CustomizationMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
        return GiocoAStati.CUSTOMIZATION_MENU;
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

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
    }
    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

    public void goBack(){
        initStates();
        stateBasedGame.enterState(GiocoAStati.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void themeSelected(ResourcePack theme) {
        PathHandler.getInstance().changeSprites(theme);
        initStates();
    }

    private void initStates() {
        try{
            ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.GENERAL_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.DIFFICULTY_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.SINGLE_REPLAY_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.MULTI_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.MULTI_LOADING)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.SCORE_BOARD_MENU)).reloadTheme();
        } catch (SlickException e ){
            e.printStackTrace();
        }

    }


}
