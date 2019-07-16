package resources;

import java.io.IOException;
import java.util.HashMap;

/**
 *  Classe Singleton utilizzata per accedere ai path delle risorse
 */
public class PathHandler {

    private static PathHandler instance;

    private HashMap<ResourcePack,String> resourcePackIndex;
    private HashMap<Resource,String> spritePaths;
    private HashMap<Resource,String> buttonPaths;
    private HashMap<Resource,String> variousPaths;
    private HashMap<Resource,String> soundPaths;
    private HashMap<ResourcePack,HashMap<Resource, String>> resourcePacks;

    private PathHandler() throws IOException {

        resourcePackIndex = new HashMap<>();
        resourcePacks = new HashMap<>();

        resourcePackIndex =PathReader.readResourcePackIndex("res/path/fileList.txt");

        spritePaths= PathReader.readResourcePack(resourcePackIndex.get(ResourcePack.CLASSIC));
        buttonPaths= PathReader.readResourcePack(resourcePackIndex.get(ResourcePack.BUTTON));
        soundPaths= PathReader.readResourcePack(resourcePackIndex.get(ResourcePack.SOUND));
        variousPaths= PathReader.readResourcePack(resourcePackIndex.get(ResourcePack.VARIOUS));

        resourcePacks.put(ResourcePack.SPRITES,spritePaths);
        resourcePacks.put(ResourcePack.BUTTON,buttonPaths);
        resourcePacks.put(ResourcePack.SOUND,soundPaths);
        resourcePacks.put(ResourcePack.VARIOUS,variousPaths);
    }
    public static synchronized PathHandler getInstance(){
        if (instance == null)
            try {
                instance = new PathHandler();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return instance;
    }

    /**
     * Cambia il tema del ResourcePack delle Sprites
     * @param newTheme il nuovo tema
     */
    public void changeSprites(ResourcePack newTheme) {
        try {
            spritePaths= PathReader.readResourcePack(resourcePackIndex.get(newTheme));
            resourcePacks.remove(ResourcePack.SPRITES);
            resourcePacks.put(ResourcePack.SPRITES, spritePaths);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param pack il pacchetto di risorse
     * @param resource la risorsa contenuta in quel pacchetto
     * @return il path della risorsa
     */
    public String getPath(ResourcePack pack, Resource resource){
        String resourcePath= resourcePacks.get(pack).get(resource);
        if (resourcePath!=null)
            return resourcePath;
        else
            throw new InvalidResourceKeyException(pack, resource);

    }

    /**
     * @param pack il ResourcePack del quale si vogliono conoscere i Path
     * @return le coppie chiave valore delle Resource del ResourcePack specificato con il relativo path
     */
    public HashMap<Resource,String> getResourcePackPaths(ResourcePack pack){
        return resourcePacks.get(pack);
    }
}
