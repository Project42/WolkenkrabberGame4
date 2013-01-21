import greenfoot.*;

public class SkyscraperWorld extends World {
    private final int WINNING_LEVEL = 5;
    public boolean stopped;
    public Counter scoreCounter;
    private Player player;
    private int levelCompletePoints;
    private int timeCount = 0;
    private int currentLevel;
    private Ground[] Ground;
    private MovingBrick[] MovingBricks;
    private Brick[] Bricks;
    private Coin[] Coins;
    private Lava[] Lava;

    //GreenfootSound backgroundMusic = new GreenfootSound("zeerstoer.mp3");

    public SkyscraperWorld(Player.PlayerType playerType)  {
        super(80, 80, 10);
        currentLevel = 1;
        levelCompletePoints = loadLevel(currentLevel);
        //backgroundMusic.playLoop();
        
                setPaintOrder(GameOverScreen.class, /*Overlay.class,*/ Counter.class, MenuBar.class, Player.class, Coin.class);
        
        
        
        /**General stuff**/
        
        
        addObject(new MenuBar(), 39, 75);

        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 6, 74);
    }
    
    public void act(){

        
        // Check for death, level completion and game completion:
        if (checkDeath())
        {
            endGame();
        }
        else if (checkLevelComplete())
        {
            // If level is complete, call purge() to remove all objects
            purge();
            // Increase level
            currentLevel++;
            // Set level clear goal
            levelCompletePoints = loadLevel(currentLevel);
        }
        // Crude way to "win" the game
        else if (currentLevel == WINNING_LEVEL)
        {
            winGame();
        }
        // Increment counter
        timeCount++;
    }
    
    public boolean checkDeath()
    {
        return checkDeath();
    }

    public boolean checkLevelComplete()
    {
        if (scoreCounter.getValue() >= levelCompletePoints)
            return true;
        else
            return false;
    }

    /**
     * Triggers the losing screen - tell player how many bees they were successful
     * in eating.
     */
    public void endGame()
    {
        ScoreBoard scoreBoard = new ScoreBoard(scoreCounter.getValue());
        addObject(scoreBoard,320,200);
        Greenfoot.stop();
    }

    /**
     * Triggers the Winning screen - gives time. Time only given
     * when game is beaten! (mostly because scoreboard can't show both
     * without more significant modification).
     */
    public void winGame()
    {
        ScoreBoard winGame = new ScoreBoard(scoreCounter.getValue(), "You Win!", "Score:");
        addObject(winGame,320,200);
        Greenfoot.stop();
    }

    /**
     * Purges all of the arrays which hold the objects, thus "erasing"
     * the level.
     */
    public void purge()
    {
        if (Ground != null)
        {
            for (int i = 0; i < Ground.length; i++)
            {
                removeObject(Ground[i]);
            }
        }
        if (Bricks != null)
        {
            for (int i = 0; i < Bricks.length; i++)
            {
                removeObject(Bricks[i]);
            }
        }
        if (MovingBricks != null)
        {
            for (int i = 0; i < MovingBricks.length; i++)
            {
                removeObject(MovingBricks[i]);
            }
        }
        if (Coins != null)
        {
            for (int i = 0; i < Coins.length; i++)
            {
                removeObject(Coins[i]);
            }
        }
        if (Lava != null)
        {
            for (int i = 0; i < Lava.length; i++)
            {
                removeObject(Lava[i]);
            }
        }
        removeObject(player);
    }

    public int loadLevel (int lvl)
    {
        if (lvl == 1)
        {
            
            Ground = new Ground[4];
            MovingBricks = new MovingBrick[2];
            Bricks = new Brick[30];
            Coins = new Coin[3];
            Lava = new Lava[5];
            
            //MovingBricks distance's
            MovingBricks[0] = new MovingBrick(5, 36);
            MovingBricks[1] = new MovingBrick(17, 58);
            
        

            for (int i = 0; i < Ground.length; i++)
            {
                Ground[i] = new Ground ();
            }   
            
            for (int i = 0; i < Bricks.length; i++)
            {
                Bricks[i] = new Brick();
            }
            
            for (int i = 0; i < Coins.length; i++)
            {
                Coins[i] = new Coin();
            }
            
        //first floor
        addObject(Ground[0], 8, 68);
        addObject(Ground[1], 19, 68);
        addObject(Ground[2], 27, 68);
        addObject(Lava[0], 42, 68);
        addObject(Lava[1], 52, 68);
        addObject(Lava[2], 62, 68);
        addObject(Lava[3], 72, 68);
        addObject(Lava[4], 76, 68);
        
        //stairs
        addObject(Bricks[0], 35, 48);
        addObject(Bricks[1], 35, 52);
        addObject(Bricks[2], 35, 56);
        addObject(Bricks[3], 35, 60);
        addObject(Bricks[4], 35, 64);
        
        addObject(Bricks[5], 31, 64);
        addObject(Bricks[6], 31, 60);
        addObject(Bricks[7], 31, 56);
        addObject(Bricks[8], 31, 52);
        
        addObject(Bricks[9], 27, 64);
        addObject(Bricks[10], 27, 60);
        addObject(Bricks[11], 27, 56);
        
        addObject(Bricks[12], 23, 64);
        addObject(Bricks[13], 23, 60);
        
        addObject(Bricks[14], 19, 64);
        
        //jump parts
        addObject(Bricks[15], 45, 48);
        addObject(Bricks[16], 56, 48);
        addObject(Bricks[17], 67, 48);
        addObject(Bricks[18], 78, 48);
        addObject(Bricks[19], 78, 44);
        addObject(Bricks[20], 72, 34);
        addObject(Bricks[21], 62, 34);
        addObject(Bricks[22], 51, 32);
        addObject(Bricks[23], 40, 30);
        addObject(Bricks[24], 1, 26);
        addObject(Bricks[25], 25, 22);
        addObject(Bricks[26], 5, 22);
        addObject(Bricks[27], 1, 22);
        addObject(Bricks[28], 1, 13);
        addObject(Bricks[29], 13, 10);
        
        //Moving Bricks
        addObject(MovingBricks[0], 25, 26);
        addObject(MovingBricks[1], 50, 10);
        
        //Coins
        addObject(Coins[0], 45, 43);
        addObject(Coins[1], 62, 39);
        addObject(Coins[2], 57, 26);
        
        //Finish
        addObject(Ground[3], 70, 10);
        
        
        addObject(player = Player.createPlayer(playerType), 4, 64);
        
        if((getY() <= 10)&&(getX() >=70)) {
            scoreCounter.add(10);
        }  
        return 40;
    }
    if (lvl == 2)
       {
            addObject(player = Player.createPlayer(playerType), 40, 40);
       }
    
}
    
    
    public Counter getScoreCounter() {
        return scoreCounter;
    }

    public Player getPlayer() {
        return player;
    }
}