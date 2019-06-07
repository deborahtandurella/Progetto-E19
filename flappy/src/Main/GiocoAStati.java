package Main;

import GameScore.ScoreBoard;
import logic.SinglePlayer.Record;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class GiocoAStati extends StateBasedGame {

    private static final int LOGIN = 0;
    private static final int MENU = 1;
    private static final int DIFFICULTY_MENU = 2;
    private static final int SINGLEPLAYER = 3;
    private static final int SINGLE_REPLAY_MENU = 4;
    private static final int MULTI_MENU = 5;
    private static final int MULTI_LOADING = 6;
    private static final int MULTIPLAYER = 7;
    private static final int MULTI_REPLAY_MENU = 8;
    private static final int SCORE_BOARD_MENU = 9;

    public GiocoAStati() {
        super("Flappy Bird");
    }

    @Override
    public void initStatesList(GameContainer gameContainer)  {
        Record record = new Record();
        try {
            ScoreBoard scoreBoard = new ScoreBoard();
            this.addState(new Login(record));
            this.addState(new Menu());
            this.addState(new DifficultyMenu());
            this.addState(new Singleplayer(record, scoreBoard));
            this.addState(new SingleplayerReplayMenuState(scoreBoard));
            this.addState(new MultiplayerMenu());
            this.addState(new MultiplayerLoading());
            this.addState(new Multiplayer());
            this.addState(new MultiplayerReplayMenu());
            this.addState(new ScoreBoardState(record, scoreBoard));
            this.addState(new CustomizationMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void main(String[] argv) {
     /*   System.setProperty("java.library.path", "natives" );
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        try{
            Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
            fieldSysPath.setAccessible( true );
            fieldSysPath.set( null, null );
        } catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }*/
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
