package states;

import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;

public abstract class FlappyGameState extends BasicGameState {
    @Override
    public void keyPressed(int key, char c) {
        if (key== Input.KEY_ESCAPE)
            System.exit(0);
    }
}
