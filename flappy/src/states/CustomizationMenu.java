package states;

import graphics.Screen;
import graphics.SpriteDrawer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CustomizationMenu extends BasicGameState {
    private static final int ID = 2;
    private GameContainer container;
    private StateBasedGame stateBasedGame;
    private Screen screen;
    private SpriteDrawer drawer;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        drawer = new SpriteDrawer(new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0));
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        screen= new Screen(gameContainer.getWidth(), gameContainer.getHeight(), 0, 0);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        drawer.drawBackgroundSingle(graphics);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
    public void keyPressed(int key, char c){
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
    }
}
