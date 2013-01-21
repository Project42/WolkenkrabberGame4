import greenfoot.*;
import java.lang.Object;

public abstract class Player extends Actor {
    private int speed;
    private int initialSpeed;
    // "physics" variables
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpStrength = 4;
    // Important game state info
    private boolean jumping = false;
    private boolean dead = false;
    // Bottom of screen, used for death
    private int floor;
    // Variables for reducing CPU load on enemy collision detection
    private int reduceCollisionDetection = 3;
    private int currCollDetection = 0;

    protected GreenfootImage image1;
    protected GreenfootImage image2;
    protected GreenfootImage image3;
    protected GreenfootImage image4;
    protected GreenfootImage image5;
    protected GreenfootImage image6;
    protected GreenfootImage image7;
    protected GreenfootImage image8;

    public enum PlayerType {
        CITIZEN, POLICE, GENIUS
    };

    protected Player(int speed) {
        this.initialSpeed = speed;
        this.speed = speed;
        //setBagType(Bag.BagType.SANDBAG);
    }

    static public Player createPlayer(PlayerType type) {
        switch (type) {
            case CITIZEN: return new Citizen();
            case POLICE: return new Police();
            case GENIUS: return new Genius();
        }
        assert false;
        return null;
    }

    @Override
    public void act() {
        getImage().scale(40, 40);
        checkFall();
        if (speed / 4 <= 0) speed = 4;

        if (Greenfoot.isKeyDown("a")) {
            move(-speed / 4, 0);
            switchImageLeft();
        }

        if (Greenfoot.isKeyDown("d")) {
            move(+speed / 4, 0);
            switchImageRight();
        }
        
        if (Greenfoot.isKeyDown("space")&& jumping==false )
        {
            jump();
        }
        
 
       /* if(Greenfoot.mouseClicked(null)) {
            getX();
            getY();
            //die shit hieronder faalt en moet op een andere manier gemaakt worden -> niet in act!
            /*if((getX() == 78) &&(getY() == 72)||(getX() == 77) &&(getY() == 72)){
               Greenfoot.stop();
            }
            
            else if(Greenfoot(getX() == 78) &&(getY() == 72)||(getX() == 77) &&(getY() == 72)){
               Greenfoot.start();
            }
            */
            /*
            if(((FloodWorld)getWorld()).backgroundMusic.getVolume() == 100 &&(getX() == 78) &&(getY() == 75)||(getX() == 77) &&(getY() == 75)){
               ((FloodWorld)getWorld()).backgroundMusic.setVolume(0);
            }
           
            else if(((FloodWorld)getWorld()).backgroundMusic.getVolume() == 0 &&(getX() == 78) &&(getY() == 75)||(getX() == 77) &&(getY() == 75)){
               ((FloodWorld)getWorld()).backgroundMusic.setVolume(100);
            }
            
            else if((getX() == 78) &&(getY() == 78)||(getX() == 77) &&(getY() == 78)){
               Greenfoot.stop();
            }
            
            else{
            Bag bag = Bag.createBag(bagType);
            if(bag.getCost() <= ((FloodWorld)getWorld()).getCoinCounter().coinValue) {
                  MouseInfo mouse = Greenfoot.getMouseInfo();
                 ((FloodWorld)getWorld()).getCoinCounter().remove(bag.getCost());
                 getWorld().addObject(bag, mouse.getX(), (mouse.getY()));
                 Greenfoot.playSound("sandbag.wav");
            }
        }
            
        }*/
        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null) {
            //Greenfoot.playSound("Coin.wav");
            getWorld().removeObject(coin);
            ((SkyscraperWorld)getWorld()).getCoinCounter().add(10);
        }
    }

    private void move(int dx, int dy) {
        if(getY() > 69) {
            setLocation(getX() + dx, 69);
            return;
        }

        setLocation(getX() + dx, getY() + dy);
    }
    
    public void jump()
    {
        jumping = true;
        vSpeed = -jumpStrength;
        fall();
    }
    
    public void fall()
    {
        setLocation ( getX(), getY() + vSpeed);
        vSpeed = vSpeed + acceleration;
        if ( atBottom() )
            gameEnd();
    }

    private void checkFall()
    {
        if (onGround()) {
            vSpeed = 0;
            jumping = false;
        }
        else {
            fall();
        }
    }
    
     public boolean onGround()
    {
        Object under = getOneObjectAtOffset(0, 2, Surface.class);
        return under != null;
    }
   
    public Enemy getEnemy()
    {
        // This loop avoids checking for close enemies on every act() to avoid performance issues
        // Instead, it does it once every reduceCollisionDetection times (3 at time of writing)
        if (currCollDetection == reduceCollisionDetection)
        {
            Actor temp = getOneIntersectingObject(Enemy.class);
            Enemy enemy = (Enemy)temp;
            currCollDetection = 0;
            return enemy;
        }
        else
        {
            currCollDetection++;
        }
        return null;
    }
    
   private boolean atBottom()
    {
        return getY() >= getWorld().getHeight() - 2;
    }
    
    private void gameEnd()
    {
        Greenfoot.stop();
    }
    
    protected void switchImageLeft()
    {
        if (getImage() == image1)
        {
            setImage(image2);
        }
        else
        {
            setImage(image1);
        }
    }

    protected void switchImageRight()
    {
        if (getImage() == image3)
        {
            setImage(image4);
        }
        else
        {
            setImage(image3);
        }
    }

    protected void switchImageStraight()
    {
        if (getImage() == image5)
        {
            setImage(image6);
        }
        else
        {
            setImage(image5);
        }
    }

    protected void switchImageBack()
    {
        if (getImage() == image7)
        {
            setImage(image8);
        }
        else
        {
            setImage(image7);
        }
    }
}
