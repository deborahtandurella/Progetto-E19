package sounds;

import game.gameEvents.GameEventListener;
import game.gameEvents.GameEventType;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SoundPlayer implements GameEventListener {
    private HashMap<GameEventType, Sound> sounds;
    private Music music;
    public SoundPlayer(){
        sounds=new HashMap<GameEventType, Sound>();
        HashMap<Resource, String> soundPaths = PathHandler.getInstance().getResourcePackPaths(ResourcePack.SOUND);
        Set<GameEventType> gameEvents= new HashSet<GameEventType>(Arrays.asList(GameEventType.values()));
        Set<String> gameEventsStrings= new HashSet<String>();
        for (GameEventType event: gameEvents) {
            gameEventsStrings.add(event.toString());
        }
        for(Resource pathkey: soundPaths.keySet()){
            if(gameEventsStrings.contains(pathkey.toString())){
                try{
                    Sound sound =new Sound(soundPaths.get(pathkey));
                    sounds.put(GameEventType.valueOf(pathkey.toString()), sound);
                }catch (SlickException e){
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void gameEvent(GameEventType event) {
        if(sounds.containsKey(event)){
            sounds.get(event).play();
        }
    }
}
