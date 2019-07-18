package states.menu;

import Main.FlappyStateGame;
import graphics.GUI.MenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import sounds.MusicPlayer;

/**
 * GeneralMenu è il menu principale di gioco, da qui è possibile accedere a ScoreBoardMenu, CustomizationMenu,
 * MultiplayerMenu e DifficultyMenu per la modalità singleplayer.
 */

public class GeneralMenu extends AbstractMenuState{

    private GameContainer container;
    private StateBasedGame stateBasedGame;

    public GeneralMenu(){
        super();
    }
    @Override
    public int getID() {
        return FlappyStateGame.GENERAL_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        MusicPlayer musicPlayer = new MusicPlayer();
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new MenuGUI(gameContainer, screen, this));
        musicPlayer.playBackgroundMusic();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    public void single(){
        stateBasedGame.enterState(FlappyStateGame.DIFFICULTY_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void multi(){
        stateBasedGame.enterState(FlappyStateGame.MULTI_MENU, new FadeOutTransition(), new FadeInTransition());
    }


    public void leaderBoard(){
        try {
            stateBasedGame.getState(FlappyStateGame.SCORE_BOARD_MENU).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(FlappyStateGame.SCORE_BOARD_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void custom(){
        stateBasedGame.enterState(FlappyStateGame.CUSTOMIZATION_MENU, new FadeOutTransition(), new FadeInTransition());
    }

}
