package states;

import graphics.GUI.CustomizationMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import resources.FileKeys;
import resources.PathHandler;

import java.awt.*;
import java.io.IOException;

public class CustomizationMenu extends AbstractMenuState {
    private static final int ID = 11;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private Screen screen;
    //private Thread initializerThread;
    //da far vedere al prof

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);

        try {
            setGui(new CustomizationMenuGUI(container,screen,this));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

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
        stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
    }

    public void themeSelected(FileKeys theme) {
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
                    ((AbstractMenuState)stateBasedGame.getState(1)).reload();
                    ((AbstractMenuState)stateBasedGame.getState(2)).reload();
                    stateBasedGame.getState(3).init(container,stateBasedGame);
                    ((AbstractMenuState)stateBasedGame.getState(4)).reload();
                    ((AbstractMenuState)stateBasedGame.getState(5)).reload();
                    ((AbstractMenuState)stateBasedGame.getState(6)).reload();
                    stateBasedGame.getState(7).init(container,stateBasedGame);
                    stateBasedGame.getState(8).init(container,stateBasedGame);
                    //((AbstractMenuState)stateBasedGame.getState(8)).reload();
                    //non esiste ancora la GUI per lo stato 8, finché non è definita, reload dà exception
                    //per gli stati 3 e 7, che non estendono AbstractMenuState, bisogna fare il metodo reload apposta
                    ((AbstractMenuState)stateBasedGame.getState(9)).reload();
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
