package Resource.Images.Avatars;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Avatars {

    private static final int NUMBER_OF_AVATARS = 21;
    //index : 0 is default

    /*
    Get Avatar Image with real width and height (png size)
     */
    public static JLabel get(int index){

        if(index >= NUMBER_OF_AVATARS){
            throw new Error("index out of range");
        }

        JLabel avatarLabel = new JLabel();
        ImageIcon avatarIcon;

        switch (index) {
            case 1 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\1.png")));
                break;
            case 2 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\2.png")));
                break;
            case 3 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\3.png")));
                break;
            case 4 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\4.png")));
                break;
            case 5 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\5.png")));
                break;
            case 6 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\6.png")));
                break;
            case 7 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\7.png")));
                break;
            case 8 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\8.png")));
                break;
            case 9 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\9.png")));
                break;
            case 10 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\10.png")));
                break;
            case 11 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\11.png")));
                break;
            case 12 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\12.png")));
                break;
            case 13 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\13.png")));
                break;
            case 14 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\14.png")));
                break;
            case 15 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\15.png")));
                break;
            case 16 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\16.png")));
                break;
            case 17 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\17.png")));
                break;
            case 18 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\18.png")));
                break;
            case 19 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\19.png")));
                break;
            case 20 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\20.png")));
                break;
            case -1 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\unknown.png")));
                break;
            default :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\default.png")));
        }
        avatarLabel.setIcon(avatarIcon);
        return avatarLabel;
    }
    /*
    Get Avatar Image with custom width and height
     */
    public static JLabel get(int index, int width, int height){

        if(index >= NUMBER_OF_AVATARS){
            throw new Error("index out of range");
        }

        JLabel avatarLabel = new JLabel();
        ImageIcon avatarIcon;

        switch (index) {
            case 1 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\1.png")));
                break;
            case 2 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\2.png")));
                break;
            case 3 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\3.png")));
                break;
            case 4 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\4.png")));
                break;
            case 5 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\5.png")));
                break;
            case 6 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\6.png")));
                break;
            case 7 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\7.png")));
                break;
            case 8 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\8.png")));
                break;
            case 9 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\9.png")));
                break;
            case 10 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\10.png")));
                break;
            case 11 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\11.png")));
                break;
            case 12 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\12.png")));
                break;
            case 13 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\13.png")));
                break;
            case 14 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\14.png")));
                break;
            case 15 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\15.png")));
                break;
            case 16 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\16.png")));
                break;
            case 17 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\17.png")));
                break;
            case 18 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\18.png")));
                break;
            case 19 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\19.png")));
                break;
            case 20 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\20.png")));
                break;
            case -1 :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\unknown.png")));
                break;
            default :
                avatarIcon = new ImageIcon(Objects.requireNonNull(Avatars.class.getClassLoader().getResource("Resource\\Images\\Avatars\\default.png")));
        }
        Image img = avatarIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        avatarIcon.setImage(img);
        avatarLabel.setIcon(avatarIcon);
        return avatarLabel;
    }
    public static void changeLabelSize(JLabel label, int width, int height) {
        ImageIcon imageIcon = (ImageIcon)label.getIcon();
        Image img = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        imageIcon.setImage(img);
        label.setIcon(imageIcon);
    }
    public static void changeButtonSize(JButton button, int width, int height) {
        ImageIcon imageIcon = (ImageIcon)button.getIcon();
        Image img = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        imageIcon.setImage(img);
        button.setIcon(imageIcon);
    }
    public static int toID(JLabel label) {
        if(label == get(0)) return 0;
        if(label == get(1)) return 1;
        if(label == get(2)) return 2;
        if(label == get(3)) return 3;
        if(label == get(4)) return 4;
        if(label == get(5)) return 5;
        if(label == get(6)) return 6;
        if(label == get(7)) return 7;
        if(label == get(8)) return 8;
        if(label == get(9)) return 9;
        if(label == get(10)) return 10;
        if(label == get(11)) return 11;
        if(label == get(12)) return 12;
        return -1;
    }
}
