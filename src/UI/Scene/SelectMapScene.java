package UI.Scene;

import Game.Map.CustomMap.Design;
import Game.Map.DefaultMap.DefaultMapGUI;
import Game.Player.Player;
import HelpingClasses.Cursor;
import UI.Effects.Sounds;
import UI.Scene.SelectPlayerScene.SelectPlayerScene;

import static Main.Main.*;
import static UI.Effects.Sounds.playSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Objects;

public class SelectMapScene extends JPanel {

    private JButton DefaultMap;
    private JLabel DefaultIdle;

    private JButton CustomMap;
    private JLabel CustomIdle;

    private JButton chooseMap;
    private JLabel chooseIdle;

    private ImageIcon[] Default;
    private ImageIcon[] Custom;
    private ImageIcon[] choose;

    private Image[] DefaultIMG;
    private Image[] CustomIMG;
    private Image[] chooseIMG;

    private JLabel backGround;
    private Icon backGroundIcon;

    private JButton back;
    private Icon backIconIdle;
    private Icon backIconFocus;

    public SelectMapScene(SelectPlayerScene LastScene, List<Player> players) {
        setLayout(null);

        setDefaultMap();
        setCustomMap();
        setChooseMap();
        setBackButton();
        setBackgroundImage();
        Cursor.Idle(frame);
        setListeners(LastScene, players);

    }

    private void setDefaultMap() {

        Default = new ImageIcon[3];
        DefaultIMG = new Image[3];
        for (int i = 0; i < 3; i++) {
            Default[i] = new ImageIcon("src\\Resource\\Images\\Maps\\WorldMap" + (i + 1) + ".png");
            if (i == 0)
                DefaultIMG[i] = Default[i].getImage().getScaledInstance(375, 577, Image.SCALE_DEFAULT);
            else
                DefaultIMG[i] = Default[i].getImage().getScaledInstance(635, 865, Image.SCALE_DEFAULT);
            Default[i].setImage(DefaultIMG[i]);
        }

        DefaultMap = new JButton();
        DefaultMap.setMargin(new Insets(0, 0, 0, 0));
        DefaultMap.setBorder(BorderFactory.createEmptyBorder());
        DefaultMap.setIcon(Default[0]);
        DefaultMap.setBorderPainted(false);
        DefaultMap.setFocusPainted(false);
        DefaultMap.setContentAreaFilled(false);
        DefaultMap.setBounds(250, 250, 375, 577);

        DefaultIdle = new JLabel();
        DefaultIdle.setIcon(Default[1]);
        DefaultIdle.setBounds(120, 120, 635, 865);


        add(DefaultMap);
        add(DefaultIdle);

    }

    private void setCustomMap() {
        Custom = new ImageIcon[3];
        CustomIMG = new Image[3];
        for (int i = 0; i < 3; i++) {
            Custom[i] = new ImageIcon("src\\Resource\\Images\\Maps\\create" + (i + 1) + ".png");
            if (i == 0)
                CustomIMG[i] = Custom[i].getImage().getScaledInstance(375, 577, Image.SCALE_DEFAULT);
            else
                CustomIMG[i] = Custom[i].getImage().getScaledInstance(635, 865, Image.SCALE_DEFAULT);
            Custom[i].setImage(CustomIMG[i]);
        }

        CustomMap = new JButton();
        CustomMap.setMargin(new Insets(0, 0, 0, 0));
        CustomMap.setBorder(BorderFactory.createEmptyBorder());
        CustomMap.setIcon(Custom[0]);
        CustomMap.setBorderPainted(false);
        CustomMap.setFocusPainted(false);
        CustomMap.setContentAreaFilled(false);
        CustomMap.setBounds(750, 250, 375, 577);

        CustomIdle = new JLabel();
        CustomIdle.setIcon(Custom[1]);
        CustomIdle.setBounds(630, 120, 635, 865);

        add(CustomMap);
        add(CustomIdle);

    }

    private void setChooseMap() {

        choose = new ImageIcon[3];
        chooseIMG = new Image[3];
        for (int i = 0; i < 3; i++) {
            choose[i] = new ImageIcon("src\\Resource\\Images\\Maps\\Provided" + (i + 1) + ".png");
            if (i == 0)
                chooseIMG[i] = choose[i].getImage().getScaledInstance(375, 577, Image.SCALE_DEFAULT);
            else
                chooseIMG[i] = choose[i].getImage().getScaledInstance(635, 865, Image.SCALE_DEFAULT);
            choose[i].setImage(chooseIMG[i]);
        }

        chooseMap = new JButton();
        chooseMap.setIcon(choose[0]);
        chooseMap.setBorderPainted(false);
        chooseMap.setFocusPainted(false);
        chooseMap.setContentAreaFilled(false);
        chooseMap.setBounds(1226, 247, 375, 577);

        chooseIdle = new JLabel();
        chooseIdle.setIcon(choose[1]);
        chooseIdle.setBounds(1100, 120, 635, 865);

        add(chooseMap);
        add(chooseIdle);
    }

    private void setBackgroundImage() {

        backGroundIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\BackGround.png")));
        backGround = new JLabel(backGroundIcon);
        backGround.setSize(frame.getBounds().width, frame.getBounds().height);

        add(backGround);
    }

    private void setBackButton() {

        backIconIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\BackIdle.png")));
        backIconFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\BackFocus.png")));

        back = new JButton(backIconIdle);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setLocation(20, 20);
        back.setIcon(backIconIdle);
        back.setSize(122, 122);

        add(back);
    }

    private void setListeners(SelectPlayerScene LastScene, List<Player> players) {

        DefaultMap.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultMapGUI gui = new DefaultMapGUI("Resource\\Images\\Maps\\DefaultMap.png", true);
                Sounds.ButtonsSound("play");
                frame.getContentPane().removeAll();
                frame.repaint();
                GameEntryScene scene = new GameEntryScene(gui, players);
                frame.add(scene);
                frame.setVisible(true);
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
                DefaultIdle.setIcon(Default[2]);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                DefaultIdle.setIcon(Default[1]);
                repaint();
            }
        });

        CustomMap.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Sounds.ButtonsSound("play");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                CustomIdle.setIcon(Custom[2]);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                CustomIdle.setIcon(Custom[1]);
                repaint();
            }
        });
        CustomMap.addActionListener(Event -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            Design scene = new Design(this, players);
            frame.add(scene);
            frame.setVisible(true);
        });

        chooseMap.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Sounds.ButtonsSound("play");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                chooseIdle.setIcon(choose[2]);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                chooseIdle.setIcon(choose[1]);
                repaint();
            }
        });
        chooseMap.addActionListener(e -> {
            DefaultMapGUI gui = new DefaultMapGUI("Resource\\Images\\Maps\\SpaceMap.png", false);
            frame.getContentPane().removeAll();
            frame.repaint();
            GameEntryScene scene = new GameEntryScene(gui, players);
            frame.add(scene);
            frame.setVisible(true);
        });
        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cursor.Idle(frame);
                Sounds.ButtonsSound("play");
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.add(LastScene);
                frame.setVisible(true);
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
                back.setIcon(backIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                back.setIcon(backIconIdle);
            }
        });
    }
}



















