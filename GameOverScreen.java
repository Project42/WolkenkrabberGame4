import greenfoot.*;
import java.awt.Color;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.util.Calendar;
/**
 * Write a description of class Gameoverscreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends Actor
{
    /**
     * Act - do whatever the menuBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    }
    public static final float FONT_SIZE = 48.0f;
    /**
     * Create a score board with dummy result for testing.
     */
        /**
     * Create a score board with dummy result for testing.
     */
    public GameOverScreen(){
        
        //Counter.getValue(); + Coins.getCoinValue(); = total
    }
    
    public GameOverScreen(int score, int coins, int total){
        makeScoreImage("Game Over", "Score: ", score);
        makeCoinImage("Game Over", "Coins: ", coins);
        makeTotalImage("Game Over", "Total: ", total);
    }
    
    /**
     * Make the score board image.
     */
    private void makeScoreImage(String title, String prefix, int score) {
        GreenfootImage image = getImage();
        image.scale(800, 800);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.BLUE);
        image.drawString(prefix + score, 280, 400);
        setImage(image);
    }
    
    private void makeCoinImage(String title, String prefix, int coins) {
        GreenfootImage image = getImage();
        image.scale(800, 800);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.BLUE);
        image.drawString(prefix + coins, 280, 500);
        setImage(image);
    }
    
    private void makeTotalImage(String title, String prefix, int total) {
        GreenfootImage image = getImage();
        image.scale(800, 800);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.BLUE);
        image.drawString(prefix + total, 280, 600);
        setImage(image);
    }
}
