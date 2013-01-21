import greenfoot.*;

public class SkyscraperWorld extends World {
    public boolean stopped;
    public Counter scoreCounter;
    private Player player;
    private Coins coinCounter;

    //GreenfootSound backgroundMusic = new GreenfootSound("zeerstoer.mp3");

    public SkyscraperWorld(Player.PlayerType playerType)  {
        super(80, 80, 10);
        //backgroundMusic.playLoop();
        
                setPaintOrder(GameOverScreen.class, /*Overlay.class,*/ Counter.class, Coins.class, MenuBar.class, Player.class, Coin.class);
        
        
        
        /**Building the level**/
        
        //first floor
        addObject(new Ground(), 8, 68);
        addObject(new Ground(), 19, 68);
        addObject(new Ground(), 27, 68);

        
        //stairs
        addObject(new Brick(), 35, 48);
        addObject(new Brick(), 35, 52);
        addObject(new Brick(), 35, 56);
        addObject(new Brick(), 35, 60);
        addObject(new Brick(), 35, 64);
        
        addObject(new Brick(), 31, 64);
        addObject(new Brick(), 31, 60);
        addObject(new Brick(), 31, 56);
        addObject(new Brick(), 31, 52);
        
        addObject(new Brick(), 27, 64);
        addObject(new Brick(), 27, 60);
        addObject(new Brick(), 27, 56);
        
        addObject(new Brick(), 23, 64);
        addObject(new Brick(), 23, 60);
        
        addObject(new Brick(), 19, 64);
        
        //jump parts
        addObject(new Brick(), 45, 48);
        addObject(new Brick(), 56, 48);
        addObject(new Brick(), 67, 48);
        addObject(new Brick(), 78, 48);
        addObject(new Brick(), 78, 44);
        addObject(new Brick(), 72, 37);
        addObject(new Brick(), 62, 34);
        addObject(new Brick(), 51, 32);
        addObject(new Brick(), 40, 30);
        addObject(new Brick(), 1, 26);
        addObject(new Brick(), 25, 22);
        addObject(new Brick(), 5, 22);
        addObject(new Brick(), 1, 22);
        addObject(new Brick(), 1, 13);
        addObject(new Brick(), 13, 10);
        
        //moving parts
        addObject(new MovingBrick(), 25, 26);
        
        
        
        
        
        //finish
        addObject(new Ground(), 70, 10);
        
        //Coins
        addObject(new Coin(), 45, 43);
        addObject(new Coin(), 62, 39);
        addObject(new Coin(), 57, 26);
        
        addObject(player = Player.createPlayer(playerType), 4, 64);
        
        addObject(new MenuBar(), 39, 75);

        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 6, 74);

        coinCounter = new Coins("Coins: ");
        addObject(coinCounter, 6, 76);
    }
    
    public void act(){

    }
    
    
    
    public Coins getCoinCounter() {
        return coinCounter;
    }

    public Player getPlayer() {
        return player;
    }
}