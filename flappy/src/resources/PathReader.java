package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PathReader {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String path;

    public void read (HashMap<ResourcePacks,String> source, ResourcePacks key, HashMap<Resources,String> destination) throws IOException {
        path = source.get(key).toString();
        fileReader = new FileReader(path);
        bufferedReader = new BufferedReader(fileReader);
        String line;
        StringTokenizer st;
        while((line = bufferedReader.readLine()) != null) {
            st = new StringTokenizer(line,",");
            destination.put(Resources.valueOf(st.nextToken()),st.nextToken());

        }
    }
}
