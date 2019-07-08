package states;

import Main.GiocoAStati;
import graphics.GUI.LoginGUI;
import logic.player.PlayerInfo;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import gameMusic.MusicPlayer;
import graphics.Screen;
import logic.SinglePlayer.Record;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Login extends AbstractMenuState {

    private static final int ID = 0;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private MusicPlayer musicPlayer;
    private Screen screen;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container = gameContainer;
        this.stateBasedGame = stateBasedGame;
        this.musicPlayer = new MusicPlayer();
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new LoginGUI(container, screen, this));
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
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException { }

    public void menu(){
        stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
    }

    public void setName(String name) {
        ((GiocoAStati) stateBasedGame).setPlayerInfo( new PlayerInfo(name));
    }
}
