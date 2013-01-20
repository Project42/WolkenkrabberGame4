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
        
        addObject(player = Player.createPlayer(playerType), 40, 67);
        
        addObject(new MenuBar(), 39, 75);

        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 6, 74);

        coinCounter = new Coins("Coins: ");
        addObject(coinCounter, 6, 76);
    }
    
    public void act(){
        int randomNumber = Greenfoot.getRandomNumber(30);
        if(randomNumber == 0) {
              int randomX = Greenfoot.getRandomNumber(80);
              int randomY = 50 + Greenfoot.getRandomNumber(70 - 50);
              addObject(new Coin(), randomX, randomY);
        }

    }
    
    
    
    public Coins getCoinCounter() {
        return coinCounter;
    }

    public Player getPlayer() {
        return player;
    }
}