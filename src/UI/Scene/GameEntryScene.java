package UI.Scene;

import Game.GUI.GameGUI;
import Game.Manager.Manager;
import Game.Map.CustomMap.CustomMapGUI;
import Game.Map.DefaultMap.DefaultMapGUI;
import Game.Objects.SavedInformation;
import Game.Player.Player;
import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;
import UI.Effects.Sounds;
import UI.Menu.MainMenu;
import UI.Scene.SelectPlayerScene.PlayerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.playSound;
import static UI.Menu.OptionsScene.isCustom;

public class GameEntryScene extends JPanel {

    private JButton start;
    private Icon startIdle, startFocus;
    private JButton back;
    private Icon backIconIdle, backIconFocus;

    private JLabel guideDisplay;
    private JButton nextPage, previousPage;
    private Icon nextPageIdle, nextPageFocus, previousPageIdle, previousPageFocus;
    private int pageID = 1;

    private MAP_TYPE type;
    private DefaultMapGUI defaultMapGUI;
    private CustomMapGUI customMapGUI;
    //private ProvidedMapGUI providedMapGUI;

    public GameEntryScene(DefaultMapGUI map, List<Player> players) {
        type = MAP_TYPE.DEFAULT;
        this.defaultMapGUI = map;
        setGraphic(players);
    }

    public GameEntryScene(CustomMapGUI map, List<Player> players) {
        type = MAP_TYPE.CUSTOM;
        this.customMapGUI = map;
        setGraphic(players);
    }

    private void setGraphic(List<Player> players) {
        setLayout(new BorderLayout());

        Icon backGroundIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\BackGrounds\\backGround1.jpg")));
        JLabel backGround = new JLabel(backGroundIcon);
        backGround.setSize(1920, 1080);

        backIconIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\CancelIdle.png")));

        backIconFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\CancelFocus.png")));
        back = new JButton(backIconIdle);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setLocation(20, 20);
        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Cursor.Idle(frame);
                playSound("src\\Resource\\Sounds\\SFX\\new\\12.wav");
                if (JOptionPane.showConfirmDialog(null, "Changes Will Be Lost!\nExit Anyway?",
                        "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    MainMenu mainMenu = new MainMenu();
                    frame.add(mainMenu);
                    frame.setVisible(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                back.setIcon(backIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                back.setIcon(backIconIdle);
            }
        });
        back.setSize(344, 128);

        startIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\StartGameIdle.png")));
        startFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\StartGameFocus.png")));

        start = new JButton(startIdle);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);
        start.setLocation(20, 20);
        start.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                start.setIcon(startFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                start.setIcon(startIdle);
            }
        });
        start.addActionListener(event -> {
            Cursor.Idle(frame);
            isCustom = false;

            Sounds.ButtonsSound("play");

            frame.getContentPane().removeAll();
            frame.repaint();

            if (type == MAP_TYPE.DEFAULT) {
                Manager manager = new Manager(defaultMapGUI, players);
                SavedInformation information = new SavedInformation(manager, defaultMapGUI, players);
                GameGUI gameGUI = new GameGUI(information);
                LoadingScene loadingScene = new LoadingScene(gameGUI, true);
                frame.add(loadingScene);
            } else if (type == MAP_TYPE.CUSTOM) {
                Manager manager = new Manager(customMapGUI, players);
                SavedInformation information = new SavedInformation(manager, customMapGUI, players);
                GameGUI gameGUI = new GameGUI(information);
                LoadingScene loadingScene = new LoadingScene(gameGUI, true);
                frame.add(loadingScene);
            }

            frame.setVisible(true);
        });
        start.setSize(344, 128);


        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        for (Player player : players) {
            PlayerGUI gui = player.getGUI();
            gui.showGUI(false);
            gui.showName();
            Avatars.changeLabelSize(gui.getAvatar(), 200, 309);
            playersPanel.add(gui);
        }

        playersPanel.setSize(1920 - 1920 / 3, 1080);
        playersPanel.setBackground(new Color(0, 0, 0, 150));

        JPanel rules = new JPanel();
        rules.setLayout(new BoxLayout(rules, BoxLayout.Y_AXIS));
        rules.setSize(1920 / 3 - 50, 1080);
        rules.setBackground(new Color(0, 0, 0));
        rules.setLocation(2 * 1920 / 3 + 50, 0);

        guideDisplay = new JLabel();
        guideDisplay.setText(guide(1));
        guideDisplay.setForeground(Color.decode("#ffb200"));
        guideDisplay.setFont(new Font("Sitka", Font.PLAIN, 25));
        rules.add(guideDisplay);

        nextPageIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\nextIdle.png")));

        nextPageFocus = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\nextFocus.png")));
        nextPage = new JButton(nextPageFocus);
        Avatars.changeButtonSize(nextPage, 100, 104);
        nextPage.setIcon(nextPageIdle);
        Avatars.changeButtonSize(nextPage, 100, 104);
        nextPage.setSize(100, 104);
        nextPage.setFocusPainted(false);
        nextPage.setBorderPainted(false);
        nextPage.setContentAreaFilled(false);
        nextPage.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Sounds.ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                nextPage.setIcon(nextPageFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                nextPage.setIcon(nextPageIdle);
            }
        });
        nextPage.addActionListener(event -> {
            pageID++;
            guideDisplay.setText(guide(pageID));
            previousPage.setEnabled(true);
            if (pageID >= 4) nextPage.setEnabled(false);
        });

        nextPage.setLocation(Width - nextPage.getWidth() - 50, Height - nextPage.getHeight() - 50);

        previousPageIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\previousIdle.png")));

        previousPageFocus = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\previousFocus.png")));
        previousPage = new JButton(previousPageFocus);
        Avatars.changeButtonSize(previousPage, 100, 104);
        previousPage.setIcon(previousPageIdle);
        Avatars.changeButtonSize(previousPage, 100, 104);
        previousPage.setSize(100, 104);
        previousPage.setFocusPainted(false);
        previousPage.setBorderPainted(false);
        previousPage.setContentAreaFilled(false);
        previousPage.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Sounds.ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                previousPage.setIcon(previousPageFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                previousPage.setIcon(previousPageIdle);
            }
        });
        previousPage.setLocation(1920 - rules.getWidth() + previousPage.getWidth(),
                1080 - previousPage.getHeight() - 50);
        previousPage.setEnabled(false);
        previousPage.addActionListener(event -> {
            pageID--;
            guideDisplay.setText(guide(pageID));
            nextPage.setEnabled(true);
            if (pageID <= 1) previousPage.setEnabled(false);
        });

        start.setLocation(1920 - rules.getWidth() - start.getWidth() - 70,
                rules.getHeight() - start.getHeight() - 20);

        back.setLocation(20, rules.getHeight() - start.getHeight() - 20);

        add(nextPage);
        add(previousPage);
        add(rules);

        add(back);
        add(start);

        add(playersPanel);
        add(backGround);
        Cursor.Idle(frame);
    }

    private String guide(int page) {
        String guideSTR = "";
        try {
            guideSTR = new String(Files.readAllBytes(Paths.get("guide" + page + ".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<html>" + guideSTR + "</html>";
    }

    private enum MAP_TYPE {DEFAULT, CUSTOM}
}
