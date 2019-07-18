package states;

import game.player.PlayerInfo;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;
import scoreboard.ScoreBoard;
import states.game.MultiplayerState;
import states.game.SingleplayerState;
import states.menu.*;
import utilities.DisplayModeManager;

import java.io.IOException;

public class FlappyStateGame extends StateBasedGame {

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

    public FlappyStateGame() {
        super("Flappy Bird");
        scoreBoard = new ScoreBoard();
    }

    /**
     * @return le informazioni non mutevoli del giocatore
     */
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    /**
     * @return la classifica
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    @Override
    public void initStatesList(GameContainer gameContainer)  {
        this.addState(new LoginMenu());
        this.addState(new GeneralMenu());
        this.addState(new ScoreBoardMenu());
        this.addState(new CustomizationMenu());
        this.addState(new DifficultyMenu());
        this.addState(new SingleplayerState());
        this.addState(new SingleplayerReplayMenu());
        this.addState(new MultiplayerMenu());
        this.addState(new MultiplayerLoadingMenu());
        this.addState(new MultiplayerState());
        this.addState(new MultiplayerEndMenu());
        this.addState(new ConnectionErrorMenu());
    }


}
