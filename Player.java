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
        fall();
        
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
        
        if (getY() == floor)
        {
            death();
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
            Greenfoot.playSound("Coin.wav");
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
    
    public void fall(){
                move(0, +vSpeed); 
                vSpeed = vSpeed + acceleration;
    }

    public void checkFall()
    {
        if(onGround()) {
            vSpeed = 0;
            jumping = false;
        }
        else {
            fall();
        }
    }
    
    public boolean onGround()
    {
        Surface under = null;
        int counter = 1;
        int max = vSpeed;
        //int variance;
        while (counter <= max && under == null)
        {
            under = (Surface)getOneObjectAtOffset ( 0, getImage().getHeight() / 2 + counter, Surface.class);
            counter++;
        }
        // If there is a platform, correct Pengu's height so that he always lands right on the platform
        // This avoids a wierd floating effect that was present otherwise
        return under != null;
    }
    
    public Actor getSurface()
    {
        Actor under = getOneObjectAtOffset ( 0, getImage().getHeight() / 2, Surface.class);
        int counter = -1;
        int max = vSpeed + 2;
        while (counter <= max && under == null)
        {
            under = getOneObjectAtOffset ( 0, getImage().getHeight() / 2 + counter, Surface.class);
            counter++;
        }
        return under;
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
    
    
    public void setFloor(int inFloor)
    {
        this.floor = inFloor;
    }

    // Method for dying
    public void death()
    {
        setRotation(90);
        dead = true;
        Greenfoot.stop();
    }

    /**
     * Left and then Right facing collision detection.
     * Uses a loop to check for an offset object to either side up to
     * 1/2 the height of the penguin
     */
    public boolean checkLeft()
    {
        Actor bumper = null;
        int counter = 0;
        int max = (int)(getImage().getHeight() / 2);
        while (counter < max && bumper == null)
        {
            bumper = getOneObjectAtOffset (-1*( getImage().getWidth() / 2), max - counter, Surface.class);
            counter++;
        }
        return bumper != null;
    }

    public boolean checkRight()
    {
        Actor bumper = null;
        int counter = 0;
        int max = (int)(getImage().getHeight() / 2);
        while (counter < max && bumper == null)
        {
            bumper = getOneObjectAtOffset ( getImage().getWidth() / 2, max - counter , Surface.class);
            counter++;
        }
        return bumper != null;
    }

    public boolean checkDeath()
    {
        return dead;
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
