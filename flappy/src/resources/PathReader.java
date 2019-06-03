package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PathReader {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private static String path;

    public void read (HashMap<FileKeys,String> source,FileKeys key,HashMap<PathKeys,String> destination) throws IOException {
        path = source.get(key).toString();
        String line;
        StringTokenizer st;
        while((line = bufferedReader.readLine()) != null) {
            st = new StringTokenizer(line,",");
            destination.put(PathKeys.valueOf(st.nextToken()),st.nextToken());

        }
    }
}
