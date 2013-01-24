import greenfoot.*;

public class InstructionsWorld extends World {
    public InstructionsWorld(){
        super(80, 80, 10);
        setBackground("game-4-menu.png");
    }

    @Override
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new SkyscraperWorld());
            return;
        }
    }
}
