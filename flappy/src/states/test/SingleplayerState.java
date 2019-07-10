package states.test;

import Main.GiocoAStati;
import game.DifficultySettings;
import game.LocalGame;
import game.itemGeneration.obstacle.ObstacleGeneratorType;
import game.SoundPlayer;
import graphics.Canvas;
import graphics.HUD.SinglePlayerHud;
import graphics.Screen;
import logic.SinglePlayer.SingleModePlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SingleplayerState extends BasicGameState {
    private LocalGame game;
    private Canvas gameCanvas;
    private DifficultySettings settings = new DifficultySettings(1, ObstacleGeneratorType.MEDIUM);
    private SoundPlayer soundPlayer;
    private SinglePlayerHud hud;
    @Override
    public int getID() {
        return 10;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        Screen screen= new Screen(gameContainer.getWidth()/2, gameContainer.getHeight(), gameContainer.getWidth()/4, 0);
        gameCanvas= new Canvas(screen, gameContainer.getGraphics());
        soundPlayer= new SoundPlayer();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        SingleModePlayer player = new SingleModePlayer(((GiocoAStati) game).getPlayerInfo());
        this.game= new LocalGame(gameCanvas, settings, player);
        this.game.addListener(soundPlayer);
        hud= new SinglePlayerHud(player, gameCanvas);


    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        game.render();
        hud.render();
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
}
