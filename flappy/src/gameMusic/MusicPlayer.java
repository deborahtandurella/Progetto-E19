package gameMusic;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class MusicPlayer {
    private Music flap;
    private Music gameOverTheme;
    private Music backgroundTheme;
    private Music rocketExplosion;

    public MusicPlayer() {
        try {
            this.flap = new Music("res/Sounds/flap.ogg");
            this.gameOverTheme = new Music("res/Sounds/gameOver.ogg");
            this.backgroundTheme = new Music("res/Sounds/menu.ogg");
            this.rocketExplosion = new Music("res/Sounds/explosion.ogg");
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

    public void backgroundMusic(){
        backgroundTheme.loop(1.0f,1.0f);
    }

    public void explosionMusic(){
        rocketExplosion.loop(1.0f, 1.0f);
    }

}
