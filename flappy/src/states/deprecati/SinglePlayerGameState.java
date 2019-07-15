package states.deprecati;

import Main.GiocoAStati;
import game.DifficultySettings;
import game.LocalGame;
import game.SoundPlayer;
import graphics.Canvas;
import graphics.Screen;
import logic.SinglePlayer.SingleModePlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SinglePlayerGameState extends BasicGameState {
    private LocalGame game;
    private SoundPlayer soundPlayer;
    private Canvas canvas;
    private DifficultySettings settings;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        soundPlayer=new SoundPlayer();
        canvas = new Canvas( new Screen(gameContainer.getWidth()/2, gameContainer.getHeight(), gameContainer.getWidth()/4, 0 ),
                gameContainer.getGraphics());
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        this.game =new LocalGame(canvas, settings, new SingleModePlayer(((GiocoAStati)game).getPlayerInfo()));
        this.game.addListener(soundPlayer);

    }
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        game.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        game.update(i);
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_SPACE);
    }
}
