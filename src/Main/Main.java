package Main;

import HelpingClasses.Cursor;
import Resource.Fonts.CustomFont;
import UI.Effects.Sounds;
import UI.Scene.LoadingScene;
import UI.Scene.OpeningScene;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static UI.Effects.Sounds.*;

public class Main {

    public static JFrame frame;
    public static int Width, Height;

    public Main() {

        frame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        frame.setSize(toolkit.getScreenSize().width, toolkit.getScreenSize().height);

        updateResolution();
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Risk");

        Cursor.Idle(frame);
        URL url = ClassLoader.getSystemResource("Resource\\Images\\Icon.png");
        Image gameIcon = toolkit.createImage(url);
        frame.setIconImage(gameIcon);

        Sounds.init();
        CustomFont.init();

        LoadingScene scene = new LoadingScene(new OpeningScene(), false);
        frame.add(scene);
        frame.setVisible(true);

        BackgroundMusic("src\\Resource\\Sounds\\BackGroundSound\\backGroundMusic0.mp3");
        CurrentSound = "backGroundMusic";
    }

    public static void main(String[] args) {
        new Main();
    }

    private void updateResolution() {
        setWidth(frame.getBounds().width);
        setHeight(frame.getBounds().height);
    }

    public static void setWidth(int w) {
        Width = w;
    }

    public static void setHeight(int h) {
        Height = h;
    }

}
