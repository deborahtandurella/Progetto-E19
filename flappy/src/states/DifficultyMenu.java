package states;

import Main.GiocoAStati;
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

public class DifficultyMenu extends AbstractMenuState {
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return GiocoAStati.DIFFICULTY_MENU;
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

    private void startGame(DifficultySettings settings){
        ((SingleplayerState) stateBasedGame.getState(GiocoAStati.SINGLEPLAYER)).setDifficulty(settings);
        stateBasedGame.enterState(GiocoAStati.SINGLEPLAYER, new FadeOutTransition(), new FadeInTransition());
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
        stateBasedGame.enterState(GiocoAStati.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

}
