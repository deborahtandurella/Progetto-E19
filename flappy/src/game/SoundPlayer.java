package game;

import game.gameEvents.GameEventListener;
import game.gameEvents.GameEventType;
import org.newdawn.slick.Sound;

import java.util.HashMap;

public class SoundPlayer implements GameEventListener {
    private HashMap<GameEventType, Sound> sounds;
    public SoundPlayer(){
        sounds=new HashMap<GameEventType, Sound>();

    }
    @Override
    public void gameEvent(GameEventType event) {
        if(sounds.containsKey(event)){
            sounds.get(event).play();
        }
    }
}
