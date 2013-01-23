import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingBrick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingBrickUp extends Surface
{
    private int speed = 1;
    private int topTurn;
    private int bottomTurn;

    /**
     * Move in the direction we are currently moving in. Turn if we reach a turning point.
     */
    public MovingBrickUp(int topT, int bottomT) {
        topTurn = topT;
        bottomTurn = bottomT;
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
       return (getY() <= topTurn || getY() >= bottomTurn);
    }
    
    public int getMovingBrickSpeed(){
        return speed;
    }
}
