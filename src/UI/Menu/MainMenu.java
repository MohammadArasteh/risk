package UI.Menu;

import HelpingClasses.Cursor;
import HelpingClasses.CustomMethods;
import UI.Effects.Sounds;
import UI.Scene.LoadGameScene;
import UI.Scene.OpeningScene;
import UI.Scene.SelectPlayerScene.SelectPlayerScene;

import static UI.Effects.Sounds.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import static Main.Main.*;

public class MainMenu extends JPanel {

    private JPanel buttonsPanel;

    private JButton newGame;
    private Icon newGameIdle;
    private Icon newGameFocused;

    private JButton loadGame;
    private Icon loadGameIdle;
    private Icon loadGameFocused;

    private JButton options;
    private Icon optionsIdle;
    private Icon optionsFocused;

    private JButton quit;
    private Icon quitIdle;
    private Icon quitFocused;

    // Saving Information after Changes in options
    public static boolean isMessageOn = true;
    public static boolean isSFX = true;
    public static boolean isON = true;
    public static int SliderValue = 50;

    private static Timer timer;
    private int timeLeft = 0;

    public static boolean firstJoin = false;

    public MainMenu() {
        Cursor.Idle(frame);
        if (!firstJoin) {
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\Welcome" + CustomMethods.random(1, 6) + ".mp3");
            firstJoin = true;
        }
        setLayout(new BorderLayout());
        setVisible(true);

        timer = new Timer(1000, e -> {
            timeLeft += 1;
            if (timeLeft >= 10) {
                {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    frame.add(new OpeningScene());
                    frame.setVisible(true);
                    ((Timer) (e.getSource())).stop();
                }
            }
        });
        timer.start();

        int BUTTON_X_POSITION = Width / 12;

        buttonsPanel = new JPanel(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        newGame = new JButton();
        newGame.setSize(344, 128);
        newGame.setLocation(BUTTON_X_POSITION, Height / 3 + Height / 15);
        newGame.setBorderPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusPainted(false);
        newGameIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\NewGame.png")));
        newGameFocused = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\NewGameFocus.png")));
        newGame.setIcon(newGameIdle);
        newGame.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                newGame.setIcon(newGameFocused);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                newGame.setIcon(newGameIdle);
            }
        });

        loadGame = new JButton();
        loadGame.setLocation(BUTTON_X_POSITION, Height / 3 + 3 * Height / 15);
        loadGame.setBorderPainted(false);
        loadGame.setContentAreaFilled(false);
        loadGame.setFocusPainted(false);
        loadGame.setSize(344, 128);
        loadGameIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\LoadGame.png")));
        loadGameFocused = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\LoadGameFocus.png")));
        loadGame.setIcon(loadGameIdle);
        loadGame.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Sounds.ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                loadGame.setIcon(loadGameFocused);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                loadGame.setIcon(loadGameIdle);
            }
        });

        options = new JButton();
        options.setLocation(BUTTON_X_POSITION, Height / 3 + 5 * Height / 15);
        options.setBorderPainted(false);
        options.setContentAreaFilled(false);
        options.setFocusPainted(false);
        options.setSize(344, 128);
        optionsIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\Options.png")));
        optionsFocused = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\OptionsFocus.png")));
        options.setIcon(optionsIdle);
        options.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Sounds.ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                options.setIcon(optionsFocused);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                options.setIcon(optionsIdle);
            }
        });

        quit = new JButton();
        quit.setLocation(BUTTON_X_POSITION, Height / 3 + 7 * Height / 15);
        quit.setBorderPainted(false);
        quit.setContentAreaFilled(false);
        quit.setFocusPainted(false);
        quit.setSize(344, 128);
        quitIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\Exit.png")));
        quitFocused = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\ExitFocus.png")));
        quit.setIcon(quitIdle);
        quit.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Sounds.ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                quit.setIcon(quitFocused);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                quit.setIcon(quitIdle);
            }
        });

        JLabel backGround = new JLabel();
        Icon backGroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Resource\\Images\\mainMenuBackGround.png")));
        backGround.setIcon(backGroundIcon);
        backGround.setSize(Width, Height);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                timeLeft = 0;
            }
        });

        add(newGame);
        add(loadGame);
        add(options);
        add(quit);
        add(backGround);

        ActionListeners();
    }

    private void ActionListeners() {

        newGame.addActionListener(Event -> {
            timer.stop();
            Sounds.ButtonsSound("play");
            frame.getContentPane().removeAll();
            frame.repaint();
            SelectPlayerScene scene = new SelectPlayerScene();
            frame.add(scene);
            frame.setVisible(true);
        });
        loadGame.addActionListener(Event -> {
            timer.stop();
            frame.getContentPane().removeAll();
            frame.repaint();
            LoadGameScene scene = new LoadGameScene();
            frame.add(scene);
            frame.setVisible(true);
        });

        options.addActionListener(Event -> {
            timer.stop();
            timeLeft = 0;
            new OptionsScene(true);
        });

        quit.addActionListener(Event -> System.exit(0));
    }

    public static void startTimer() {
        timer.start();
    }
}
