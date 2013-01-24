import greenfoot.*;

import java.awt.Color;
import java.awt.Graphics;

public class TimeCounter extends Actor {
    private static final Color textColor = new Color(255, 255, 255);
    private String text;
    private int timeCount = 0;
    private int timeActs = 0;
    private int timeScore = 400;
    private int stringLength;

    public TimeCounter() {
        this("");
    }

    public TimeCounter(String prefix) {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);

        updateImage();
    }

    public void act() {
        timeActs++;
        if(timeActs == 10){
            timeCount++;
            timeActs = 0;
            updateImage();
            updateScore();
        }
    }

     public int getTime() {
        return timeCount;
    }
    
    public void updateScore(){
            timeScore -= 1;
    }
    
    public int getTimeScore(){
        return timeScore;
    }
    
    private void updateImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + timeCount, 1, 12);
    }
}
