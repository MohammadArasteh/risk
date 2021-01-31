package Game.GUI;

import HelpingClasses.Cursor;
import UI.Menu.MainMenu;
import UI.Menu.OptionsScene;
import UI.Scene.SaveGameScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Objects;

import static Main.Main.frame;
import static UI.Effects.Sounds.*;
import static UI.Menu.OptionsScene.isCustom;

public class Menu {

    private JDialog window;

    private JPanel panel;
    private JLabel menuTitle;
    private JButton resume, save, options, mainMenu;
    private Icon resumeIdle, resumeFocus, saveIdle, saveFocus, optionsIdle, optionsFocus, mainMenuIdle, mainMenuFocus;


    public Menu(GameGUI gui) {
        gui.getTimer().stop();

        window = new JDialog(frame);
        window.setLayout(null);
        window.setSize(frame.getBounds().width, frame.getBounds().height);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);

        window.setBackground(new Color(0, 0, 0, 200));

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(frame.getBounds().width / 3, frame.getBounds().height - frame.getBounds().height / 5);
        panel.setLocation(frame.getBounds().width / 2 - panel.getWidth() / 2,
                frame.getBounds().height / 2 - panel.getHeight() / 2);
        panel.setBackground(Color.decode("#f6b36c"));


        menuTitle = new JLabel();
        menuTitle.setText("MENU");
        menuTitle.setForeground(Color.decode("#3c1f0f"));
        menuTitle.setFont(new Font("Sitka Small", Font.PLAIN, 50));
        menuTitle.setAlignmentX(.5f);

        resumeIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\ContinueIdle.png")));
        resumeFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\ContinueFocus.png")));
        resume = new JButton(resumeFocus);
        resume.setBorderPainted(false);
        resume.setFocusPainted(false);
        resume.setContentAreaFilled(false);
        resume.setSize(344, 128);
        resume.setIcon(resumeIdle);
        resume.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(window);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                resume.setIcon(resumeFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(window);
                resume.setIcon(resumeIdle);
            }
        });
        resume.addActionListener(e ->
                {
                    window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                    gui.getTimer().start();
                }
        );
        resume.setAlignmentX(.5f);

        saveIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SaveGameIdle.png")));
        saveFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SaveGameFocus.png")));
        save = new JButton(saveFocus);
        save.setBorderPainted(false);
        save.setFocusPainted(false);
        save.setContentAreaFilled(false);
        save.setSize(344, 128);
        save.setIcon(saveIdle);
        save.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(window);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                save.setIcon(saveFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(window);
                save.setIcon(saveIdle);
            }
        });
        save.addActionListener(e -> new SaveGameScene(gui));
        save.setAlignmentX(.5f);

        optionsIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\Options.png")));
        optionsFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\OptionsFocus.png")));
        options = new JButton(optionsFocus);
        options.setBorderPainted(false);
        options.setFocusPainted(false);
        options.setContentAreaFilled(false);
        options.setSize(344, 128);
        options.setIcon(optionsIdle);
        options.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(window);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                options.setIcon(optionsFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(window);
                options.setIcon(optionsIdle);
            }
        });
        options.addActionListener(e -> new OptionsScene(false));
        options.setAlignmentX(.5f);

        mainMenuIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\QuitIdle.png")));
        mainMenuFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\QuitFocus.png")));
        mainMenu = new JButton(mainMenuFocus);
        mainMenu.setBorderPainted(false);
        mainMenu.setFocusPainted(false);
        mainMenu.setContentAreaFilled(false);
        mainMenu.setSize(344, 128);
        mainMenu.setIcon(mainMenuIdle);
        mainMenu.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(window);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                mainMenu.setIcon(mainMenuFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(window);
                mainMenu.setIcon(mainMenuIdle);
            }
        });
        mainMenu.addActionListener(e -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\12.wav");
            if (JOptionPane.showConfirmDialog(
                    null, "Are You Sure Want to QUIT to The Main Menu?",
                    "NOTICE", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                gui.stopGame();
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                frame.getContentPane().removeAll();
                frame.repaint();
                MainMenu menu = new MainMenu();
                frame.add(menu);
                frame.setVisible(true);

                isCustom = false;
                loop = false;
                CurrentSound = "backGroundMusic";
                BackgroundMusic("src\\Resource\\Sounds\\BackGroundSound\\backGroundMusic0.mp3");
            }
        });
        mainMenu.setAlignmentX(.5f);

        panel.add(Box.createRigidArea(new Dimension(10, 20)));
        panel.add(menuTitle);
        panel.add(Box.createRigidArea(new Dimension(10, 80)));
        panel.add(resume);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(save);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(options);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(mainMenu);
        panel.add(Box.createRigidArea(new Dimension(10, 80)));

        window.add(panel);
        window.setVisible(true);
        Cursor.Idle(window);
        Cursor.Idle(frame);
    }
}
