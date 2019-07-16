package sounds;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

/**
 *  Controlla la musica di sottofondo
 */
public class MusicPlayer {
    private Music backgroundTheme;

    public MusicPlayer() {
        try {
            this.backgroundTheme = new Music(PathHandler.getInstance().getPath(ResourcePack.SOUND, Resource.BG_MUSIC));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Avvia la musica di sottofondo in loop
     */
    public void playBackgroundMusic(){
        backgroundTheme.loop(1.0f,1.0f);
    }

    /**
     * Ferma la musica di sottofondo
     */
    public void stopBackgroundMusic(){
        backgroundTheme.stop();
    }

}
