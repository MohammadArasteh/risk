package Game.Objects;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Dice extends JLabel implements Serializable {

    private Random random = new Random();
    private JLabel crossOut;
    private int value = 0;
    public Dice() {
        setLayout(new BorderLayout());
        crossOut = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Dice\\crossOut.png"))));
        crossOut.setSize(140,155);
        setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Dice\\1.png"))));
        crossOut.setVisible(false);
        add(crossOut);
        setSize(140,155);
    }

    public void roll() {
        value = random.nextInt(6) + 1;
    }
    public void rollAnimation() {
        setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Dice\\DiceGif.gif"))));
        revalidate();
        repaint();
    }
    public void rollStop() {
        setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Dice\\" + value + ".png"))));
        revalidate();
        repaint();
    }
    public int getValue() {
        return value;
    }
    public String toString() {
        return String.valueOf(value);
    }
    public void resetImage() {
        setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Dice\\1.png"))));
    }
    public void lost() {
        crossOut.setVisible(true);
    }
}
