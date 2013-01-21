import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingBrick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingBrick extends Surface
{
    private int speed = 1;
    private int leftTurn = 5;
    private int rightTurn = 36;

    /**
     * Move in the direction we are currently moving in. Turn if we reach a turning point.
     */
    public void act() 
    {
        setLocation ( getX() + speed, getY() );
        
        Actor actor = getOneIntersectingObject(Player.class);
        if(actor != null) {
            actor.setLocation ( actor.getX() + speed, actor.getY() );
        }
        
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
}
