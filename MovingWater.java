import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingWater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingWater extends Obstacles
{
    private int acts;
    private int level;

    public MovingWater() {
        getImage().scale(820, 20);
        getImage().setTransparency(100);
        level = 10;
    }
    
    @Override
    public void act() {
        setLocation(40, 70);
        
        ++acts;
        if (acts == 4) {
            getImage().scale(820, getImage().getHeight() + 1);
            acts = 0;
            ++level;
        }
    }
    
    public int getLevel() {
        return level;
    }    
}
