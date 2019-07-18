package sounds;

import game.gameEvents.GameEventListener;
import game.gameEvents.GameEventType;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import resources.PathHandler;
import resources.Resource;
import resources.ResourcePack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *  Reagisce agli eventi di gioco emettendo un suono corrispondente, se presente
 */
public class SoundPlayer implements GameEventListener {
    private HashMap<GameEventType, Sound> sounds;

    public SoundPlayer(){
        sounds=new HashMap<>();
        initializeSounds();
    }

    /**
     * Emette un suono corrispondente all'evento segnalato
     * @param event evento segnalato
     */
    @Override
    public void gameEvent(GameEventType event) {
        if(sounds.containsKey(event)){
            sounds.get(event).play();
        }
    }

    /**
     *  Il ResourcePack dei suoni viene confrontando con GameEvents per individuare i suoni che corrispondono ad aventi di gioco
     */
    private void initializeSounds(){
        HashMap<Resource, String> soundPaths = PathHandler.getInstance().getResourcePackPaths(ResourcePack.SOUND);
        Set<GameEventType> gameEvents= new HashSet<>(Arrays.asList(GameEventType.values()));
        Set<String> gameEventsStrings= new HashSet<>();
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
}
