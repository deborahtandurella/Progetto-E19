package resources;

import java.io.IOException;
import java.util.HashMap;

public class PathHandler {

    private static String path= "res/path/fileList.txt";
    private static PathHandler instance;
    private HashMap<ResourcePacks,String> filePaths;
    private HashMap<Resources,String> spritePaths;
    private HashMap<Resources,String> buttonPaths;
    private HashMap<Resources,String> variousPaths;
    private HashMap<Resources,String> soundPaths;
    private HashMap<ResourcePacks,HashMap> superMap;
    private PathReader reader;
    private SingletonInizializer inizializer;

    private PathHandler() throws IOException {

        filePaths = new HashMap<>();
        spritePaths = new HashMap<>();
        buttonPaths = new HashMap<>();
        variousPaths = new HashMap<>();
        soundPaths = new HashMap<>();
        superMap = new HashMap<>();

        inizializer = new SingletonInizializer();
        inizializer.init(path,filePaths);

        reader = new PathReader();

        reader.read(filePaths, ResourcePacks.CLASSIC,spritePaths);
        reader.read(filePaths, ResourcePacks.BUTTON,buttonPaths);
        reader.read(filePaths, ResourcePacks.SOUND,soundPaths);
        reader.read(filePaths, ResourcePacks.VARIOUS,variousPaths);
        superMap.put(ResourcePacks.SPRITES,spritePaths);
        superMap.put(ResourcePacks.BUTTON,buttonPaths);
        superMap.put(ResourcePacks.SOUND,soundPaths);
        superMap.put(ResourcePacks.VARIOUS,variousPaths);
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

    public void changeSprites(ResourcePacks key) throws IOException {
        spritePaths.clear();
        reader.read(filePaths,key,spritePaths);

    }

    public String getPath(ResourcePacks mapKey, Resources pathKey){
        return superMap.get(mapKey).get(pathKey).toString();
    }
    public HashMap<Resources,String> getSoundPaths(){
        return soundPaths;
    }
}
