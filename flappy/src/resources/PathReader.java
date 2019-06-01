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

    public void read (HashMap source,FileKeys key,HashMap destination) throws IOException {
        path = source.get(key).toString();
        String line;
        StringTokenizer st;
        while(true) {
            line=bufferedReader.readLine();
            st = new StringTokenizer(line,",");
            destination.put(FileKeys.valueOf(st.nextToken()),st.nextToken());
            if(line == null)
                throw new InvalidKeyException();
        }
    }
}
