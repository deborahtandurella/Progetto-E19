package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PathReader {

    public static HashMap<Resource,String> readResourcePack(String pathOfResourcePack) throws IOException {
        HashMap<Resource,String> destination = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathOfResourcePack));
        String line;
        while(( line = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line,",");
            destination.put(Resource.valueOf(st.nextToken()),st.nextToken());
        }
        reader.close();
        return destination;
    }
    public static HashMap<ResourcePack, String> readResourcePackIndex(String pathOfPackIndex) throws IOException {
        HashMap<ResourcePack, String> result = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathOfPackIndex));
        String line;
        while((line = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line,",");
            result.put(ResourcePack.valueOf(st.nextToken()),st.nextToken());
        }
        reader.close();
        return result;
    }
}
