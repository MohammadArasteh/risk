package Resource.Images.Buttons.StateButtons;

import javax.swing.*;
import java.util.Objects;

public class StateButtons {
    public static ImageIcon RedIdle() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\redIdle.png")));
    }
    public static ImageIcon RedSelected() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\redSelected.png")));
    }
    public static ImageIcon BlueIdle() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\blueIdle.png")));
    }
    public static ImageIcon BlueSelected() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\blueSelected.png")));
    }
    public static ImageIcon GreenIdle() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\greenIdle.png")));
    }
    public static ImageIcon GreenSelected() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\greenSelected.png")));
    }
    public static ImageIcon YellowIdle() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\yellowIdle.png")));
    }
    public static ImageIcon YellowSelected() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Buttons\\StateButtons\\yellowSelected.png")));
    }

    public static ImageIcon Land(int id) {
        switch (id) {
            case 1 : return Land1();
            case 2 : return Land2();
            case 3 : return Land3();
            case 4 : return Land4();
            default: return Sea();
        }
    }
    public static ImageIcon Land1() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Maps\\1.png")));
    }
    public static ImageIcon Land2() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Maps\\2.png")));
    }
    public static ImageIcon Land3() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Maps\\3.png")));
    }
    public static ImageIcon Land4() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Maps\\4.png")));
    }
    public static ImageIcon Sea() {
        return new ImageIcon(Objects.requireNonNull(StateButtons.class.getClassLoader().getResource("Resource\\Images\\Maps\\0.png")));
    }



}
