package states.menu;

import Main.FlappyStateGame;
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

/**
 * CustomizationMenu si occupa della gestione dei temi di gioco e dell'eventuale ricarica di tutte le gui di gioco
 */

public class CustomizationMenu extends AbstractMenuState {
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return FlappyStateGame.CUSTOMIZATION_MENU;
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
        stateBasedGame.enterState(FlappyStateGame.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    /**
     * @param theme : tema grafico selezionato
     */
    public void themeSelected(ResourcePack theme) {
        PathHandler.getInstance().changeSprites(theme);
        initStates();
    }

    /**
     * initStates richiama il metodo reloadTheme() per le gui di tutti gli stati del package ad eccezione di
     * CustomizationMenu, in cui il tema grafico è già impostato, e LoginMenu, in cui è l'unico accesso si ha
     * all'esecuzione del Main
     */
    private void initStates() {
        try{
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.GENERAL_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.DIFFICULTY_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.SINGLE_REPLAY_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.MULTI_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.MULTI_LOADING)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.SCORE_BOARD_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.CONNECTION_ERROR_MENU)).reloadTheme();
            ((AbstractMenuState)stateBasedGame.getState(FlappyStateGame.MULTI_END_MENU)).reloadTheme();
        } catch (SlickException e ){
            e.printStackTrace();
        }

    }


}
