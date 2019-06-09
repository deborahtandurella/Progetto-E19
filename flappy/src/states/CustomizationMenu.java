package states;

import graphics.GUI.CustomizationMenuGUI;
import graphics.Screen;
import graphics.SpriteDrawer;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;
import java.awt.*;
import java.io.IOException;

public class CustomizationMenu extends BasicGameState {
    private static final int ID = 11;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private CustomizationMenuGUI gui;
    private Screen screen;
    private SpriteDrawer drawer;
    private Thread initializerThread;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        drawer = new SpriteDrawer(new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0));
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        try {
            gui = new CustomizationMenuGUI(container,screen,this);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        gui.render();
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
       /* try {
            stateBasedGame.getState(1).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }*/
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

    public void initStates() throws SlickException {
       /* initializerThread = new Thread(new Runnable(){

            @Override
            public void run() {
                try{*/
                    stateBasedGame.getState(0).init(container,stateBasedGame);
                    stateBasedGame.getState(1).init(container,stateBasedGame);
                    stateBasedGame.getState(2).init(container,stateBasedGame);
                    stateBasedGame.getState(3).init(container,stateBasedGame);
                    stateBasedGame.getState(4).init(container,stateBasedGame);
                    stateBasedGame.getState(5).init(container,stateBasedGame);
                    stateBasedGame.getState(6).init(container,stateBasedGame);
                    stateBasedGame.getState(7).init(container,stateBasedGame);
                    stateBasedGame.getState(8).init(container,stateBasedGame);
                    stateBasedGame.getState(9).init(container,stateBasedGame);
              /*  } catch (SlickException e ){
                    e.printStackTrace();
                }
            }
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
