import greenfoot.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HighScoreWorld extends World {
    private Game game;
    
    public HighScoreWorld(Game game) {
        super(600, 400, 1);
        this.game = game;
        
        List<HighScore> highScores;
        try {
            highScores = HighScore.readHighScores(HighScore.defaultFilenameForGame(game));
        } catch (IOException e) {
            highScores = new ArrayList<HighScore>();
        }
        
        String highScoresString = "High Scores\n";
        for (HighScore highScore : highScores) {
            highScoresString += highScore.getName() + ": " + highScore.getScore() + "\n";
        }
        
        GreenfootImage textImage = new GreenfootImage(600, 400);
        textImage.drawString(highScoresString, 10, 20);
        setBackground(textImage);
    }
}
