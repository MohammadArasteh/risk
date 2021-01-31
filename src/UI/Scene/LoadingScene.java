package UI.Scene;

import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.*;

public class LoadingScene extends JPanel {

    public JLabel ProgressValue;
    public JLabel LoadingAnimation;
    public Icon LoadingGif;
    public JLabel BackGround;
    public Icon BackGroundIcon;
    public Thread thread;

    public LoadingScene(JPanel nextPanel, boolean startingGame) {
        setLayout(new BorderLayout());
        Cursor.Loading(frame);
        ProgressValue = new JLabel();
        ProgressValue.setText("0");
        ProgressValue.setFont(new Font("Tahoma", Font.BOLD, 50));
        ProgressValue.setForeground(Color.decode("#cdcdcd"));
        ProgressValue.setSize(200, 100);
        ProgressValue.setLocation(Width / 2 - 20, Height / 2 + 200);

        BackGroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Resource\\Gifs\\backgroundTest.png")));
        BackGround = new JLabel(BackGroundIcon);
        Avatars.changeLabelSize(BackGround, Width, Height);
        BackGround.setSize(Width, Height);

        LoadingGif = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Resource\\Gifs\\logo.gif")));
        LoadingAnimation = new JLabel(LoadingGif);
        LoadingAnimation.setSize(500, 500);
        LoadingAnimation.setLocation(Width / 2 - 250, Height / 2 - 250);

        add(ProgressValue);
        add(LoadingAnimation);
        add(BackGround);

        RunProgress(nextPanel, startingGame);
    }

    public void RunProgress(JPanel nextPanel, boolean startingGame) {
        thread = new Thread(() -> {

            for (int index = 1; index < 101; index++) {

                ProgressValue.setText("" + index + "%");

                try {
                    Thread.sleep(1500 / index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (index >= 100) {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    frame.add(nextPanel);
                    frame.setVisible(true);
                    Cursor.Idle(frame);
                    if (startingGame) {
                        loop = false;
                        CurrentSound = "GamePlay";
                        BackgroundMusic("src\\Resource\\Sounds\\BackGroundSound\\GamePlay0.mp3");
                    }
                }
            }
        });
        thread.start();
    }
}

