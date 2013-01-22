import greenfoot.*;
import java.lang.Object;
import java.util.List;

public abstract class Player extends Actor {
    private int speed;
    private int initialSpeed;
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpStrength = 4;
    private boolean jumping = false;
    private boolean falling = false;
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
        
        if (Greenfoot.isKeyDown("space") && jumping == false) {
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
        
        int waterOffset = 70 - ((SkyscraperWorld)getWorld()).getWaterLevel() / 2 / 10;
        if (waterOffset <= getY()) {
            getWorld().removeObject(this);
            return;
        }
        
        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null) {
            Greenfoot.playSound("Coin.wav");
            getWorld().removeObject(coin);
            ((SkyscraperWorld)getWorld()).getScoreCounter().add(10);
        } 
    }

    private void move(int dx, int dy) {
        if(getY() > 69) {
            setLocation(getX() + dx, 69);
            return;
        }

        setLocation(getX() + dx, getY() + dy);
    }
    
    public void jump() {
        if (vSpeed <= 0 && !jumping) {
            jumping = true;
            vSpeed = -jumpStrength;
        }
    }
    
    public void fall() {
        setLocation(getX(), getY() + vSpeed);
        if (vSpeed > 4) {
            vSpeed = 4;
        } else {
            vSpeed = vSpeed + acceleration;
        }
        
        boolean moveUp = false;
        Actor obj = null;
        for (int i = 1; i <= 4; ++i) {
            obj = getOneObjectAtOffset(0, i, Surface.class);
            if (obj != null) {
                moveUp = true;
                break;
            }
        }
        if (moveUp) {
            setLocation(getX(), obj.getY() - 4);
        }
        
        if (inWater()) {
            ((SkyscraperWorld)getWorld()).endGame();
        }
    }

    private void checkFall() {
        if (jumping || !onGround()) {
            fall();
        }
        
        if (onGround()) {
            vSpeed = 0;
            jumping = false;
        }
    }
    
    public boolean onGround()
    {
         return getOneObjectAtOffset(-1, 4, Surface.class) != null
             || getOneObjectAtOffset(0, 4, Surface.class) != null
             || getOneObjectAtOffset(1, 4, Surface.class) != null;
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
    
    private boolean inWater()
    {
        Actor Water = getOneIntersectingObject(Water.class);
        return Water != null;
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
