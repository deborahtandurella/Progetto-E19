package states;

import graphics.GUI.MultiplayerReplayMenuGUI;
import graphics.Screen;
import logic.SinglePlayer.Result;
import logic.player.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MultiplayerReplayMenu extends AbstractMenuState {
    private static final int ID = 8;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private Result localPlayerResult;
    private Result remotePlayerResult;

    public MultiplayerReplayMenu(){
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container = gameContainer;
        this.stateBasedGame = stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new MultiplayerReplayMenuGUI(gameContainer, screen, this));
        container.getGraphics().clearWorldClip();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.clearWorldClip();
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


    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        getGui().reload();
    }

    public void backToMenu(){
        try {
            stateBasedGame.getState(1).init(container,stateBasedGame);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        stateBasedGame.enterState(1,new FadeOutTransition(),new FadeInTransition());
    }
    public void setResults(Result localPlayerResult, Result remotePlayerResult){
        this.localPlayerResult= localPlayerResult;
        this.remotePlayerResult = remotePlayerResult;
    }

    public Result getLocalPlayerResult() {
        return localPlayerResult;
    }

    public Result getRemotePlayerResult() {
        return remotePlayerResult;
    }
}
