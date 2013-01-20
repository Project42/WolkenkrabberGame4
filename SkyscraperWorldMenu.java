import greenfoot.*;

public class SkyscraperWorldMenu extends World {
    public SkyscraperWorldMenu() {
        super(80, 80, 10);
    }

    @Override
    public void act() {
        if (Greenfoot.isKeyDown("1")) {
            Greenfoot.setWorld(new SkyscraperWorld(Player.PlayerType.CITIZEN));
            return;
        }

        if (Greenfoot.isKeyDown("2")) {
            Greenfoot.setWorld(new SkyscraperWorld(Player.PlayerType.POLICE));
            return;
        }

        if (Greenfoot.isKeyDown("3")) {
            Greenfoot.setWorld(new SkyscraperWorld(Player.PlayerType.GENIUS));
            return;
        }
    }
}