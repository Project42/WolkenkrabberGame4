import greenfoot.*;

public class GameOverWorld extends World {
    private int actsPassed;
    private Game game;
    
    public GameOverWorld() {
        super(600, 400, 1);
        game = Game.SANDBAG_GAME;
    }
    
    public GameOverWorld(Game game) {
        super(600, 400, 1); 
        this.game = game;
    }
    
    @Override
    public void act() {
        if (++actsPassed == 20) {
            Greenfoot.setWorld(new HighScoreWorld(game));
        }
    }
}
