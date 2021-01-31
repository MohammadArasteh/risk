package HelpingClasses;

import javax.swing.*;
import java.awt.*;

public class FadingText extends JLabel {

    private int alpha = 255;
    private int increment;

    public FadingText(String text, int speed , Color color) {
        increment = -speed;
        setText(text);
        new Timer(50, e -> {
            alpha += increment;
            if (alpha >= 255) {
                alpha = 255;
                increment = -increment;
            }
            if (alpha <= 0) {
                alpha = 0;
                increment = -increment;
            }
            setForeground(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
        }).start();
    }
}
