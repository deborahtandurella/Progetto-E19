package states.menu;

import Main.FlappyStateGame;
import game.DifficultySettings;
import game.itemGeneration.obstacle.ObstacleGeneratorType;
import graphics.GUI.DifficultyMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import states.game.SingleplayerState;

public class DifficultyMenu extends AbstractMenuState {
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return FlappyStateGame.DIFFICULTY_MENU;
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

    private void startGame(DifficultySettings settings){
        ((SingleplayerState) stateBasedGame.getState(FlappyStateGame.SINGLEPLAYER)).setDifficulty(settings);
        stateBasedGame.enterState(FlappyStateGame.SINGLEPLAYER, new FadeOutTransition(), new FadeInTransition());
    }
    public void startEasyGame(){
        DifficultySettings settings=new DifficultySettings(0.7, ObstacleGeneratorType.EASY);
        startGame(settings);
    }
    public void startNormalGame(){
        DifficultySettings settings= new DifficultySettings(1, ObstacleGeneratorType.MEDIUM);
        startGame(settings);
    }
    public void startHardGame(){
        DifficultySettings settings= new DifficultySettings(1.3, ObstacleGeneratorType.HARD);
        startGame(settings);
    }
    public void back(){
        stateBasedGame.enterState(FlappyStateGame.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

}
