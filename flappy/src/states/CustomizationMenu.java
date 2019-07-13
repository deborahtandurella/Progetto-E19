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
import resources.ResourcePacks;

import java.awt.*;
import java.io.IOException;

public class CustomizationMenu extends AbstractMenuState {
    private GameContainer container;
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return GiocoAStati.CUSTOMIZATION_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);

        try {
            setGui(new CustomizationMenuGUI(container, screen,this));
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
        try {
            initStates();
        } catch (Exception e ){
            e.printStackTrace();
        }
        stateBasedGame.enterState(GiocoAStati.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void themeSelected(ResourcePacks theme) {
        try {
            PathHandler.getInstance().changeSprites(theme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStates() throws SlickException {

       // initializerThread = new Thread(new Runnable(){


            //@Override
           // public void run() {
                try{
                    ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.GENERAL_MENU)).reloadTheme();
                    ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.DIFFICULTY_MENU)).reloadTheme();
                    stateBasedGame.getState(GiocoAStati.SINGLEPLAYER).init(container,stateBasedGame);
                    ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.SINGLE_REPLAY_MENU)).reloadTheme();
                    ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.MULTI_MENU)).reloadTheme();
                    ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.MULTI_LOADING)).reloadTheme();
                    stateBasedGame.getState(GiocoAStati.SINGLEPLAYER).init(container,stateBasedGame);
                    stateBasedGame.getState(GiocoAStati.MULTI_REPLAY_MENU).init(container,stateBasedGame);
                    //((AbstractMenuState)stateBasedGame.getState(8)).reloadTheme();
                    //non esiste ancora la GUI per lo stato 8, finché non è definita, reloadTheme dà exception
                    //per gli stati 3 e 7, che non estendono AbstractMenuState, bisogna fare il metodo reloadTheme apposta
                    ((AbstractMenuState)stateBasedGame.getState(GiocoAStati.SCORE_BOARD_MENU)).reloadTheme();
                } catch (SlickException e ){
                    e.printStackTrace();
                }
        /*    }
        });

        initializerThread.start();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        super.leave(container, game);
        try {
            initializerThread.join();
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
        i thread non funzionano, eseguiamo in serie
        */
    }
}
