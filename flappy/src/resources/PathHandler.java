package resources;

import java.io.IOException;
import java.util.HashMap;

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

    public void changeSprites(ResourcePack newTheme) {
        try {
            spritePaths= PathReader.readResourcePack(resourcePackIndex.get(newTheme));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPath(ResourcePack mapKey, Resource pathKey){
        String resourcePath= resourcePacks.get(mapKey).get(pathKey);
        if (resourcePath!=null)
            return resourcePath;
        else
            throw new InvalidResourceKeyException(mapKey, pathKey);

    }
    public HashMap<Resource,String> getResourcePackPaths(ResourcePack pack){
        return resourcePacks.get(pack);
    }
}
