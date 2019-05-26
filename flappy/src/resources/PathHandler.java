package resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PathHandler {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private static String path= "res/path_list.txt";
    private static PathHandler instance;

    private PathHandler() throws FileNotFoundException {
        fileReader = new FileReader(path);
        bufferedReader =new BufferedReader(fileReader);
    }
    public static synchronized PathHandler getInstance(){
        if (instance == null)
            try {
                instance= new PathHandler();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return instance;
    }

    private void reload() throws IOException{
        fileReader.reset();
        bufferedReader = new BufferedReader(fileReader);
    }
    public String getPath(PathKeys key) throws IOException {
        reload();
        String line;
        StringTokenizer st;
        while(true) {
            line=bufferedReader.readLine();
            st = new StringTokenizer(line,",");

            if(st.nextElement().equals(key.toString())){
                path = st.nextToken();
                return path;
            }
            if(line==null)
                throw new InvalidKeyException();
        }

    }
}
