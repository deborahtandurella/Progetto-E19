package GameScore;

import logic.SinglePlayer.Record;

import java.io.*;

public class ScoreFacade {

    public void readScoreBoard(Record[] records, int nPlayers) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("res/record.txt"));
        for(int i = 0; i <nPlayers; i++){
            String riga = in.readLine();
            String[] result = riga.split("\\t");
            records[i] = new Record();
            records[i].setName(result[1]);
            records[i].setScore(Integer.parseInt(result[2]));
        }
        in.close();
    }

    public void writePlayers(Record[] records, int nPlayers) throws IOException {
        PrintWriter f = new PrintWriter(new FileWriter("res/record.txt"));
        for(int i = 0; i < nPlayers; i++){
            String line = i+1 +")\t"+ records[i].getName() + "\t" + records[i].getScore();
            f.println(line);
        }
        f.close();
    }




}
