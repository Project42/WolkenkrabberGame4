import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SkyscraperMovingBrickRight extends SkyscraperSurface
{
    private int speed = 1;
    private int leftTurn;
    private int rightTurn;

    /**
     * Move in the direction we are currently moving in. Turn if we reach a turning point.
     */
    public SkyscraperMovingBrickRight(int leftT, int rightT) {
        leftTurn = leftT;
        rightTurn = rightT;
    }
    
    public void act() 
    {
        setLocation ( getX() + speed, getY() );
        
        if (atTurningPoint()) {
            speed = -speed;
        }
    }
    
    /**
     * Test if we are at one of the turning points.
     */
    public boolean atTurningPoint()
    {
       return (getX() <= leftTurn || getX() >= rightTurn);
    }
    
    public int getMovingBrickRightSpeed(){
        return speed;
    }
}
