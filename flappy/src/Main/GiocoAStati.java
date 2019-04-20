package Main;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;

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
        this.addState(new Menu());
        this.addState(new DifficultyMenu());
        this.addState(new Singleplayer());
        this.addState(new SingleplayerReplayMenu());
        this.addState(new MultiplayerMenu());
        this.addState(new MultiplayerLoading());
        this.addState(new Multiplayer());
        this.addState(new MultiplayerReplayMenu());
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
