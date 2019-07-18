package states.menu;

import states.FlappyStateGame;
import game.player.Result;
import graphics.GUI.MultiplayerEndMenuGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Menu post-partita Multiplayer, da qui vengono passati alla gui i risultati della partita, la gui si occupa poi di
 * mostrare l'esito agli utenti
 */

public class MultiplayerEndMenu extends AbstractMenuState {
    private StateBasedGame stateBasedGame;
    private Result localPlayerResult;
    private Result remotePlayerResult;

    public MultiplayerEndMenu(){
    }

    @Override
    public int getID() {
        return FlappyStateGame.MULTI_END_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new MultiplayerEndMenuGUI(gameContainer, screen, this));
        gameContainer.getGraphics().clearWorldClip();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.clearWorldClip();
        renderGui();
    }


    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        getGui().reload();
    }

    public void backToMenu(){
        stateBasedGame.enterState(FlappyStateGame.GENERAL_MENU,new FadeOutTransition(),new FadeInTransition());
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
