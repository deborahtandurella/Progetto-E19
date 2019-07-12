package states.test;

import GameScore.ScoreBoard;
import Main.GiocoAStati;
import game.DifficultySettings;
import game.LocalGame;
import game.gameEvents.GameEventListener;
import game.gameEvents.GameEventType;
import game.itemGeneration.obstacle.ObstacleGeneratorType;
import game.SoundPlayer;
import graphics.Canvas;
import graphics.HUD.SinglePlayerHud;
import graphics.Screen;
import logic.SinglePlayer.Result;
import logic.SinglePlayer.SingleModePlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SingleplayerState extends BasicGameState implements GameEventListener {
    private LocalGame game;
    private Canvas gameCanvas;
    private DifficultySettings difficulty = new DifficultySettings(1, ObstacleGeneratorType.MEDIUM);
    private ScoreBoard scoreBoard;
    private GiocoAStati stateGame;

    @Override
    public int getID() {
        return 10;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        stateGame=(GiocoAStati) stateBasedGame;
        this.scoreBoard = stateGame.getScoreBoard();
        Screen screen= new Screen(gameContainer.getWidth()/2, gameContainer.getHeight(), gameContainer.getWidth()/4, 0);
        gameCanvas= new Canvas(screen, gameContainer.getGraphics());
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        SingleModePlayer player = new SingleModePlayer(((GiocoAStati) game).getPlayerInfo());
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
        if (key==Input.KEY_SPACE){
            game.playerJump();
        }
    }
    public void setDifficulty(DifficultySettings difficulty){
        this.difficulty=difficulty;
    }

    @Override
    public void gameEvent(GameEventType event) {
        if(event==GameEventType.GAMEOVER){
            scoreBoard.compareScore(new Result(game.getPlayer()));
            stateGame.enterState(GiocoAStati.SINGLE_REPLAY_MENU);
        }
    }
}
