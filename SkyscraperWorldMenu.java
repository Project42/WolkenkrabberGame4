import greenfoot.*;

public class SkyscraperWorldMenu extends World
{
    public SkyscraperWorldMenu()
    {    
        super(80, 80, 10);
        setBackground("skyscraper_menu.png");
    }
    
    @Override
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new SkyscraperWorld());
            return;
        }
    }
}