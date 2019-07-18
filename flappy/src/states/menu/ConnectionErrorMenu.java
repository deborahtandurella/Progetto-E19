package states.menu;

import Main.FlappyStateGame;
import graphics.GUI.ConnectionErrorGUI;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * ConnectionErrorMenu si occupa di mostrare all'utente la schermata relativa ad un eventuale errore di connessione e
 * dà la possibilità di tornare al GeneralMenu
 */

public class ConnectionErrorMenu extends AbstractMenuState{
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return FlappyStateGame.CONNECTION_ERROR_MENU;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame= stateBasedGame;
        Screen screen = new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
        setGui(new ConnectionErrorGUI(gameContainer, screen,this));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renderGui();
    }

    public void backToMenu(){
        stateBasedGame.enterState(FlappyStateGame.MULTI_MENU, new FadeOutTransition(), new FadeInTransition());
    }
}
