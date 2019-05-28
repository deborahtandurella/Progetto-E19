package Main;

import logic.SinglePlayer.Record;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
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
    private static final int SCORE_BOARD_MENU = 8;

    public GiocoAStati() {
        super("Flappy Bird");
    }

    @Override
    public void initStatesList(GameContainer gameContainer)  {
        Record record = new Record();

        this.addState(new Menu());
        this.addState(new DifficultyMenu());
        this.addState(new Singleplayer(record));
        this.addState(new SingleplayerReplayMenuState());
        this.addState(new MultiplayerMenu());
        this.addState(new MultiplayerLoading());
        this.addState(new Multiplayer());
        this.addState(new MultiplayerReplayMenu());
        this.addState(new ScoreBoardState(record));
    }

    public static void main(String[] argv) {

        try {
            AppGameContainer container = new AppGameContainer(new GiocoAStati());
            container.setSmoothDeltas(false);
            container.setTargetFrameRate(125);
            container.setVSync(false);
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            DisplayMode bestMode = DisplayModeManager.getBiggestWithRatio(4, 3);
            container.setDisplayMode(bestMode.getWidth(),bestMode.getHeight(),false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
