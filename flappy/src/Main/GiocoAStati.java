package Main;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import stati.DifficultyMenu;
import stati.GameOver;
import stati.MainGame;
import stati.StartMenu;

import java.io.File;

public class GiocoAStati extends StateBasedGame {

    private static final int MENU = 0;
    private static final int DIFFICULTY_MENU = 1;
    private static final int SINGLEPLAYER = 2;
    private static final int SINGLE_REPLAY_MENU = 3;
    private static final int MULTI_MENU = 4;
    private static final int MULTI_LOADING = 5;
    private static final int MULTIPLAYER = 6;
    private static final int MULTI_REPLAY_MENU = 7;

    public GiocoAStati() {
        super("Flappy Bird");
    }

    @Override
    public void initStatesList(GameContainer gameContainer)  {
        this.addState(new StartMenu());
        this.addState(new MainGame());
        this.addState(new GameOver());
        this.addState(new DifficultyMenu());
    }

    public static void main(String[] argv) {

        try {
            AppGameContainer container = new AppGameContainer(new GiocoAStati());
            container.setTargetFrameRate(250);
            //              container.setDisplayMode(Display.getDisplayMode().getWidth(),Display.getDisplayMode().getHeight(),false);
            container.setDisplayMode(450,650,false);
            System.err.println(Display.getDisplayMode().getHeight());
            System.err.println(Display.getDisplayMode().getWidth());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
