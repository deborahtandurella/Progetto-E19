package resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PathHandler {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String key;
    private String line;
    private StringTokenizer st;
    private String path;

    public PathHandler() throws FileNotFoundException {
        fileReader = new FileReader("res/path_list.txt");
        bufferedReader =new BufferedReader(fileReader);
    }

    public String getPath(PathKeys key) throws IOException {
        this.key = key.toString();
        while(true) {
            line=bufferedReader.readLine();
            st = new StringTokenizer(line,",");

            if(st.nextElement().equals(this.key)){
                path = st.nextToken();
                return path;
            }
            if(line==null)
                throw new InvalidKeyException();
        }

    }
}
