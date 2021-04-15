import java.io.*;
import java.util.*;

class JukeBox2 {
    ArrayList<Song> songList = new ArrayList<Song>();

    public static void main(String[] args) {
        JukeBox2 jb2 = new JukeBox2();
        jb2.go();
    }

    class ArtistCompare implements Comparator<Song> {
        public int compare(Song one, Song two) {
            return one.getArtist().compareTo(two.getArtist());
        }
    } 

    public void go() {
        getSongs();
        System.out.println(songList);

        Collections.sort(songList);
        System.out.println(songList);

        ArtistCompare artistCompare = new ArtistCompare();
        Collections.sort(songList, artistCompare);
        System.out.println(songList);

        HashSet<Song> songSet = new HashSet<Song>();
        songSet.addAll(songList);
        System.out.println(songSet);

        TreeSet<Song> songTreeSet = new TreeSet<Song>();
        songTreeSet.addAll(songList);
        System.out.println(songTreeSet);
    }

    public void getSongs() {
        try {
            File file = new File("SongListMore.txt");
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

        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }
}