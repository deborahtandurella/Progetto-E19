package gameMusic;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class MusicPlayer {
    private Music flap;
    private Music gameOverTheme;
    private Music backgroundTheme;

    public MusicPlayer() {
        try {
            this.flap = new Music("res/flap.ogg");
            this.gameOverTheme = new Music("res/gameOver.ogg");
            this.backgroundTheme = new Music("res/menu.ogg");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void flapMusic(){
        flap.play(1.0f,1.0f);
    }

    public void gameOverMusic(){
        gameOverTheme.play(1.0f,1.0f);
    }

    public void backgroudMusic(){
        backgroundTheme.loop(1.0f,1.0f);
    }
}
