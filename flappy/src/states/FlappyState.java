package states;

import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;

/**
 * keyPressed è un metodo comune a tutti gli stati di Flappy Bird
 */

public abstract class FlappyState extends BasicGameState {
    @Override
    public void keyPressed(int key, char c) {
        if (key== Input.KEY_ESCAPE)
            System.exit(0);
    }
}
