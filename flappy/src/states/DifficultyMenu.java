package states;

import game.DifficultySettings;
import game.itemGeneration.obstacle.ObstacleGeneratorType;
import graphics.GUI.DifficultyMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.test.SingleplayerState;

public class DifficultyMenu extends AbstractMenuState {
    private static final int ID = 2;
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame= stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new DifficultyMenuGUI(gameContainer, screen,this));
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

    private void startGame(/*ci sarà da passare un enum o qualcosa per la difficoltà*/){
        stateBasedGame.enterState(10, new FadeOutTransition(), new FadeInTransition());
    }
    public void startEasyGame(){
        ((SingleplayerState) stateBasedGame.getState(10)).setDifficulty(new DifficultySettings(0.7, ObstacleGeneratorType.EASY));
        startGame();
    }
    public void startNormalGame(){
        ((SingleplayerState) stateBasedGame.getState(10)).setDifficulty(new DifficultySettings(1, ObstacleGeneratorType.MEDIUM));
        startGame();
    }
    public void startHardGame(){
        ((SingleplayerState) stateBasedGame.getState(10)).setDifficulty(new DifficultySettings(1.3, ObstacleGeneratorType.HARD));
        startGame();
    }
    public void back(){
        stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
    }

}
