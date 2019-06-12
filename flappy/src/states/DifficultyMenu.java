package states;

import graphics.GUI.DifficultyMenuGUI;
import graphics.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class DifficultyMenu extends AbstractMenuState {
    private static final int ID = 2;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private static float gameSpeed;
    private Screen screen;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new DifficultyMenuGUI(container,screen,this));
        gameSpeed=0.7f;
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

    public void startGame(/*ci sarà da passare un enum o qualcosa per la difficoltà*/){
       try {
            stateBasedGame.getState(3).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(3, new FadeOutTransition(), new FadeInTransition());
    }

    public void back(){
        stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
    }

}
