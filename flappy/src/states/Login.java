package states;

import graphics.GUI.LoginGUI;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import gameMusic.MusicPlayer;
import graphics.Screen;
import graphics.SpriteDrawer;
import logic.SinglePlayer.Record;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Login extends BasicGameState {

    private static final int ID = 0;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private SpriteDrawer drawer;
    private MusicPlayer musicPlayer;
    private LoginGUI gui;
    private Screen screen;
    private Record record;

    public Login(Record record){
        super();
        this.record = record;
    }

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
        drawer = new SpriteDrawer(screen);
        gui = new LoginGUI(container, screen, this);
        musicPlayer.backgroundMusic();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);
        gui.render();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        gui.activate();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        super.leave(container, game);
        gui.deactivate();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException { }

    public void menu(){
        /*try {

            stateBasedGame.getState(1).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }*/
        stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
    }

    public void setRecordName(String name) {
        record.setName(name);
    }
}
