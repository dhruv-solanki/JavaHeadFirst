import java.io.*;
import java.util.*;

public class JukeBox1 {
    ArrayList<String> songList = new ArrayList<String>();

    public static void main(String[] args) {
        JukeBox1 jb1 = new JukeBox1();
        jb1.go();
    }

    public void go() {
        getSongs();
        System.out.println(songList);

        Collections.sort(songList);
        System.out.println(songList);
    }

    public void getSongs() {
        try {
            File file = new File("SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = null;
            while((line = reader.readLine()) != null) {
                addSong(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addSong(String line) {
        String[] tokens = line.split("/");

        songList.add(tokens[0]);
    }
}