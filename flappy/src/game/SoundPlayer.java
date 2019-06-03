package game;

import game.gameEvents.GameEventListener;
import game.gameEvents.GameEventType;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import java.util.*;

public class SoundPlayer implements GameEventListener {
    private HashMap<GameEventType, Sound> sounds;
    private Music music;
    public SoundPlayer(){
        sounds=new HashMap<GameEventType, Sound>();
        HashMap<PathKeys, String> soundPaths = PathHandler.getInstance().getSoundPaths();
        try{
            music= new Music(soundPaths.get(PathKeys.BG_MUSIC));
        } catch (SlickException e){
            e.printStackTrace();
        }
        Set<GameEventType> gameEvents= new HashSet<GameEventType>(Arrays.asList(GameEventType.values()));
        Set<String> gameEventsStrings= new HashSet<String>();
        for (GameEventType event: gameEvents) {
            gameEventsStrings.add(event.toString());
        }
        for(PathKeys pathkey: soundPaths.keySet()){
            if(gameEventsStrings.contains(pathkey.toString())){
                try{
                    Sound sound =new Sound(soundPaths.get(pathkey));
                    sounds.put(GameEventType.valueOf(pathkey.toString()), sound);
                }catch (SlickException e){
                    e.printStackTrace();
                }
            }
        }
        if(music!=null)
            music.play();
    }
    @Override
    public void gameEvent(GameEventType event) {
        if(sounds.containsKey(event)){
            sounds.get(event).play();
        }
    }
}
