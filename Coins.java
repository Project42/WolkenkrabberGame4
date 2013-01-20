import greenfoot.*;

import java.awt.Color;
import java.awt.Graphics;

public class Coins extends Actor {
    private static final Color textColor = new Color(255, 255, 255);

    public int coinValue = 0;
    private int coinTarget = 0;
    private String text;
    private int stringLength;

    public Coins() {
        this("");
    }

    public Coins(String prefix) {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);

        updateCoinsImage();
    }

    public void act() {
        if(coinValue < coinTarget) {
            coinValue++;
            updateCoinsImage();
        }
        else if(coinValue > coinTarget) {
            coinValue--;
            updateCoinsImage();
        }
    }

    public void add(int coinz) {
        coinTarget += coinz;
    }
    
    public void remove(int coinz) {
        coinTarget -= coinz;
    }

    public int getCoinValue() {
        return coinValue;
    }
    private void updateCoinsImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + coinValue, 1, 12);
    }
}
