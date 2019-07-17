package states.menu;

import Main.FlappyGameState;
import game.player.PlayerInfo;
import graphics.GUI.LoginGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import sounds.MusicPlayer;

public class LoginMenu extends AbstractMenuState {

    private StateBasedGame stateBasedGame;


    @Override
    public int getID() {
        return FlappyGameState.LOGIN;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        MusicPlayer musicPlayer = new MusicPlayer();
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new LoginGUI(gameContainer, screen, this));
        musicPlayer.playBackgroundMusic();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        ((LoginGUI) getGui()).activate();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        super.leave(container, game);
        ((LoginGUI) getGui()).deactivate();
    }

    public void menu(){
        stateBasedGame.enterState(FlappyGameState.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void setName(String name) {
        ((FlappyGameState) stateBasedGame).setPlayerInfo( new PlayerInfo(name));
    }
}
