package states.game;

import Main.FlappyGameState;
import game.DifficultySettings;
import game.gameEvents.GameEventListener;
import game.gameEvents.GameEventType;
import game.player.Result;
import game.player.SingleModePlayer;
import game.singleplayer.LocalGame;
import graphics.Canvas;
import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import scoreboard.ScoreBoard;
import sounds.SoundPlayer;
import states.menu.SingleplayerReplayMenu;

public class SingleplayerState extends states.FlappyGameState implements GameEventListener {
    private LocalGame game;
    private Canvas gameCanvas;
    private DifficultySettings difficulty;
    private ScoreBoard scoreBoard;
    private FlappyGameState stateGame;

    @Override
    public int getID() {
        return FlappyGameState.SINGLEPLAYER;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        stateGame=(FlappyGameState) stateBasedGame;
        this.scoreBoard = stateGame.getScoreBoard();
        Screen screen= new Screen(gameContainer.getWidth()/2, gameContainer.getHeight(), gameContainer.getWidth()/4, 0);
        gameCanvas= new Canvas(screen, gameContainer.getGraphics());
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        SingleModePlayer player = new SingleModePlayer(((FlappyGameState) game).getPlayerInfo());
        this.game= new LocalGame(gameCanvas, difficulty, player);
        this.game.addListener(new SoundPlayer());
        this.game.addListener(this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        game.render();
        gameCanvas.clipScreen();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        game.update(i);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if(key== Input.KEY_SPACE){
            game.playerJump();
        }
    }
    public void setDifficulty(DifficultySettings difficulty){
        this.difficulty=difficulty;
    }

    @Override
    public void gameEvent(GameEventType event) {
        if(event==GameEventType.GAMEOVER){
            if (scoreBoard.addResult(new Result(game.getPlayer()))){
                ((SingleplayerReplayMenu)stateGame.getState(FlappyGameState.SINGLE_REPLAY_MENU)).newRecord();
            }
            stateGame.enterState(FlappyGameState.SINGLE_REPLAY_MENU);
        }
    }
}
