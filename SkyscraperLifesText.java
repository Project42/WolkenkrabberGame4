import greenfoot.*;

import java.awt.Color;
import java.awt.Graphics;

public class SkyscraperLifesText extends Actor {
    private static final Color textColor = new Color(255, 255, 255);

    public int value = 0;
    public int target = 0;
    private String text;
    private int stringLength;

    public SkyscraperLifesText() {
        this("");
    }

    public SkyscraperLifesText(String prefix) {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);
        image.drawString(text, 1, 12);
    }
}
