package states.menu;

import Main.GiocoAStati;
import graphics.GUI.MenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import sounds.MusicPlayer;

public class GeneralMenu extends AbstractMenuState{

    private GameContainer container;
    private StateBasedGame stateBasedGame;

    public GeneralMenu(){
        super();
    }
    @Override
    public int getID() {
        return GiocoAStati.GENERAL_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        MusicPlayer musicPlayer = new MusicPlayer();
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new MenuGUI(gameContainer, screen, this));
        musicPlayer.backgroundMusic();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) { }

    public void single(){
        stateBasedGame.enterState(GiocoAStati.DIFFICULTY_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void multi(){
        stateBasedGame.enterState(GiocoAStati.MULTI_MENU, new FadeOutTransition(), new FadeInTransition());
    }


    public void leaderBoard(){
        try {
            stateBasedGame.getState(GiocoAStati.SCORE_BOARD_MENU).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(GiocoAStati.SCORE_BOARD_MENU, new FadeOutTransition(), new FadeInTransition());
    }


    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

    public void custom(){
        stateBasedGame.enterState(GiocoAStati.CUSTOMIZATION_MENU, new FadeOutTransition(), new FadeInTransition());
    }

}
