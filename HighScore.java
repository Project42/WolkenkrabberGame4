import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class HighScore implements Comparable<HighScore> {
    private String name;
    private int score;
    
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() { return name; }
    public int getScore() { return score; }
    
    public static List<HighScore> readHighScores(String filename) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        List<HighScore> highScores = new ArrayList<HighScore>();
        String string;
        while ((string = reader.readLine()) != null) {
            String strings[] = string.split(": ");
            if (strings.length != 2) continue;
            try {
                highScores.add(new HighScore(strings[0], Integer.parseInt(strings[1])));
            } catch (NumberFormatException e) {
                continue;
            }
        }
        Collections.sort(highScores);
        Collections.reverse(highScores);
        return highScores;
    }
    
    public void save(String filename) throws IOException {
        Writer writer = new FileWriter(filename, true);
        writer.write(getName() + ": " + getScore() + "\n");
        writer.close();
    }
    
    public static HighScore askName(int score) {
        // FIXME: Read prompt from localization file?
        String name = JOptionPane.showInputDialog("Vul je naam in:");
        if (name == null) return null;
        return new HighScore(name, score);
    }
    
    public static String defaultFilenameForGame(Game game) {
        String homeDirectory = System.getProperty("user.home");
        String filename = null;
        switch (game) {
            case SANDBAG_GAME: filename = ".fc2015-high-scores-sandbag"; break;
            case CONTROL_ROOM_GAME: filename = ".fc2015-high-scores-control-room"; break;
            case HELICOPTER_GAME: filename = ".fc2015-high-scores-helicopter"; break;
            case SKYSCRAPER_GAME: filename = ".fc2015-high-scores-skyscraper"; break;
        }
        return new File(homeDirectory, filename).getAbsolutePath();
    }
    
    public int compareTo(HighScore other) {
        return score - other.score;
    }
}
