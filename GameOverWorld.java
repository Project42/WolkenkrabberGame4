import greenfoot.*;
import java.io.IOException;

public class GameOverWorld extends World {
    private int actsPassed;
    private Game game;
    private int score;
    
    public GameOverWorld(Game game, int score) {
        super(600, 400, 1); 
        this.game = game;
        this.score = score;
    }
    
    @Override
    public void act() {
        if (++actsPassed == 20) {
            HighScore highScore = HighScore.askName(score);
            try {
                highScore.save(HighScore.defaultFilenameForGame(game));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Greenfoot.setWorld(new HighScoreWorld(game));
        }
    }
}
