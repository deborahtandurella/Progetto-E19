package resources;

import java.io.*;
import java.util.HashMap;

public class PathHandler {

    private static String path= "res/path/fileList.txt";
    private static PathHandler instance;
    private HashMap<FileKeys,String> filePaths;
    private HashMap<PathKeys,String> spritePaths;
    private HashMap<PathKeys,String> buttonPaths;
    private HashMap<PathKeys,String> soundPaths;
    private HashMap<FileKeys,HashMap> superMap;
    private PathReader reader;
    private SingletonInizializer inizializer;

    private PathHandler() throws IOException {

        filePaths = new HashMap<>();
        spritePaths = new HashMap<>();
        buttonPaths = new HashMap<>();
        soundPaths = new HashMap<>();
        superMap = new HashMap<>();

        inizializer = new SingletonInizializer();
        inizializer.init(path,filePaths);

        reader = new PathReader();

        reader.read(filePaths,FileKeys.CLASSIC,spritePaths);
        reader.read(filePaths,FileKeys.BUTTON,buttonPaths);
        reader.read(filePaths,FileKeys.SOUND,soundPaths);
        superMap.put(FileKeys.SPRITES,spritePaths);
        superMap.put(FileKeys.BUTTON,spritePaths);
        superMap.put(FileKeys.SOUND,spritePaths);
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

    public void changeSprites(FileKeys key) throws IOException {
        spritePaths.clear();
        reader.read(filePaths,key,spritePaths);

    }

    public String getPath(FileKeys mapKey, PathKeys pathKey){
        return superMap.get(mapKey).get(pathKey).toString();
    }
    public HashMap<PathKeys,String> getSoundPaths(){
        return soundPaths;
    }
}
