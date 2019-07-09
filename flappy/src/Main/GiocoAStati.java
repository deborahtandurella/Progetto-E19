package Main;

import GameScore.ScoreBoard;
import game.DifficultySettings;
import game.itemGeneration.obstacle.ObstacleGeneratorType;
import logic.SinglePlayer.Record;
import logic.player.PlayerInfo;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;
import states.test.LocalMultiplayerState;
import states.test.MultiplayerState;
import states.test.SingleplayerState;

import java.io.IOException;

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
    private PlayerInfo playerInfo;

    public GiocoAStati() {
        super("Flappy Bird");
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    @Override
    public void initStatesList(GameContainer gameContainer)  {
        Record record = new Record();
        DifficultySettings settings = new DifficultySettings(1, ObstacleGeneratorType.MEDIUM);
        try {
            ScoreBoard scoreBoard = new ScoreBoard();
            this.addState(new Login());
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
            this.addState(new SingleplayerState());
            this.addState(new LocalMultiplayerState());
            this.addState(new MultiplayerState());
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
