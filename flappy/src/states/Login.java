package states;

import Main.GiocoAStati;
import gameMusic.MusicPlayer;
import graphics.GUI.LoginGUI;
import graphics.Screen;
import logic.player.PlayerInfo;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Login extends AbstractMenuState {

    private StateBasedGame stateBasedGame;


    @Override
    public int getID() {
        return GiocoAStati.LOGIN;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        MusicPlayer musicPlayer = new MusicPlayer();
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new LoginGUI(gameContainer, screen, this));
        musicPlayer.backgroundMusic();
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

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) { }

    public void menu(){
        stateBasedGame.enterState(GiocoAStati.GENERAL_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    public void setName(String name) {
        ((GiocoAStati) stateBasedGame).setPlayerInfo( new PlayerInfo(name));
    }
}
