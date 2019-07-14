package Main;

import GameScore.ScoreBoard;
import logic.player.PlayerInfo;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;
import states.MultiplayerState;
import states.SingleplayerState;

import java.io.IOException;

public class GiocoAStati extends StateBasedGame {

    public static final int LOGIN = 0;
    public static final int GENERAL_MENU = 1;
    public static final int SCORE_BOARD_MENU = 2;
    public static final int CUSTOMIZATION_MENU = 3;
    public static final int DIFFICULTY_MENU = 4;
    public static final int SINGLEPLAYER = 5;
    public static final int SINGLE_REPLAY_MENU = 6;
    public static final int MULTI_MENU = 7;
    public static final int MULTI_LOADING = 8;
    public static final int MULTIPLAYER = 9;
    public static final int MULTI_END_MENU = 10;
    public static final int CONNECTION_ERROR_MENU = 11;
    private PlayerInfo playerInfo;
    private ScoreBoard scoreBoard;

    public GiocoAStati() {
        super("Flappy Bird");
        try {
            scoreBoard = new ScoreBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    @Override
    public void initStatesList(GameContainer gameContainer)  {
        this.addState(new Login());
        this.addState(new Menu());
        this.addState(new ScoreBoardState());
        this.addState(new CustomizationMenu());
        this.addState(new DifficultyMenu());
        this.addState(new SingleplayerState());
        this.addState(new SingleplayerReplayMenuState());
        this.addState(new MultiplayerMenu());
        this.addState(new MultiplayerLoading());
        this.addState(new MultiplayerState());
        this.addState(new MultiplayerEndMenu());
        this.addState(new ConnectionErrorState());
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
            container.setUpdateOnlyWhenVisible(false);
            container.setSmoothDeltas(false);
            container.setTargetFrameRate(200);
            container.setVSync(false);
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            DisplayMode bestMode = DisplayModeManager.getBiggestWithRatio(4, 3);
            container.setDisplayMode(bestMode.getWidth(),bestMode.getHeight(),false);
            container.start();
        } catch (SlickException | LWJGLException e) {
            e.printStackTrace();
        }
    }
}
