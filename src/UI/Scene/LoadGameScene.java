package UI.Scene;

import Game.GUI.GameGUI;
import Game.Objects.SavedInformation;
import HelpingClasses.Cursor;
import HelpingClasses.JTableUtilities;
import Resource.Images.Avatars.Avatars;
import UI.Effects.Sounds;
import UI.Menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.ButtonsSound;
import static UI.Effects.Sounds.playSound;
import static UI.Menu.OptionsScene.isCustom;

public class LoadGameScene extends JPanel {

    private JTable savedGames;
    private String[] columnsName;
    private String[][] data;
    private String selected;

    private JButton back, load, delete;
    private ImageIcon backIdle, backFocus, loadIdle, loadFocus, deleteIdle, deleteFocus;
    private JLabel backGround;
    private ImageIcon backGroundIcon;


    public LoadGameScene() {
        this.setSize(Width, Height);
        setLayout(null);
        setContent();

        frame.add(this);
        frame.setVisible(true);
    }

    private void setContent() {
        backIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\BackIdle.png")));
        backFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\BackFocus.png")));

        back = new JButton(backIdle);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setLocation(20, 20);
        back.setIcon(backIdle);
        back.addMouseListener(new MouseListener() {
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
                back.setIcon(backFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                back.setIcon(backIdle);
            }
        });
        back.addActionListener(event -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            MainMenu scene = new MainMenu();
            frame.add(scene);
            frame.setVisible(true);
        });
        back.setSize(122, 122);

        load = new JButton();
        load.setBorderPainted(false);
        load.setContentAreaFilled(false);
        load.setFocusPainted(false);
        load.setSize(344, 128);
        loadIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\LoadGame.png")));
        loadFocus = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\LoadGameFocus.png")));
        load.setIcon(loadIdle);
        load.addMouseListener(new MouseListener() {
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
                load.setIcon(loadFocus);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                load.setIcon(loadIdle);
            }
        });
        load.addActionListener(e -> {
            isCustom = false;
            if (!selected.isEmpty())
                loadGame(selected);
        });
        load.setLocation(Width - load.getWidth() - 20, Height - load.getHeight() - 20);
        load.setVisible(false);

        delete = new JButton();
        delete.setBorderPainted(false);
        delete.setContentAreaFilled(false);
        delete.setFocusPainted(false);
        delete.setSize(250, 93);
        deleteIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\DeleteIdle.png")));
        deleteFocus = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\DeleteFocus.png")));

        delete.setIcon(deleteFocus);
        Avatars.changeButtonSize(delete, 250, 93);
        delete.setIcon(deleteIdle);
        Avatars.changeButtonSize(delete, 250, 93);
        delete.addMouseListener(new MouseListener() {
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
                delete.setIcon(deleteFocus);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                delete.setIcon(deleteIdle);
            }
        });
        delete.addActionListener(e -> {
            if (!selected.isEmpty())
                deleteGame(selected);
        });
        delete.setVisible(false);

        backGroundIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\BackGround2.jpg")));

        backGround = new JLabel(backGroundIcon);
        Avatars.changeLabelSize(backGround, Width, Height);
        backGround.setSize(Width, Height);


        columnsName = new String[2];
        columnsName[0] = "Name";
        columnsName[1] = "Creation Date";

        try {
            File[] files = new File("saves").listFiles();
            assert files != null;
            data = new String[files.length][2];
            for (int index = 0; index < files.length; index++) {
                BasicFileAttributes basicFileAttributes = Files.readAttributes(files[index].toPath(), BasicFileAttributes.class);
                StringBuilder creation_date = new StringBuilder(basicFileAttributes.creationTime().toString());
                creation_date.delete(10, creation_date.length());

                String fileName = files[index].getName().replace(".txt", "");

                data[index][0] = fileName;
                data[index][1] = creation_date.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        savedGames = new JTable(data, columnsName);
        savedGames.setFont(new Font("Sitka Small", Font.PLAIN, 35));
        savedGames.setForeground(Color.decode("#ffb200"));
        savedGames.setBackground(Color.decode("#4d1009"));
        savedGames.setSelectionBackground(Color.decode("#2c0703"));
        savedGames.setSelectionForeground(Color.decode("#e8bd93"));
        savedGames.setRowHeight(80);

        savedGames.getTableHeader().setBackground(Color.BLACK);
        savedGames.getTableHeader().setForeground(Color.WHITE);
        savedGames.getTableHeader().setPreferredSize(new Dimension(150, 50));
        savedGames.getTableHeader().setFont(new Font("Sitka Small", Font.PLAIN, 30));

        JTableUtilities.setCellsAlignment(savedGames, SwingConstants.CENTER);
        savedGames.setDefaultEditor(Object.class, null);
        savedGames.getTableHeader().setReorderingAllowed(false);
        savedGames.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ButtonsSound("play");

                load.setVisible(true);
                delete.setVisible(true);

                revalidate();
                repaint();
                int column = 0;
                int row = savedGames.getSelectedRow();
                selected = savedGames.getModel().getValueAt(row, column).toString();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JScrollPane sp = new JScrollPane(savedGames);
        sp.setSize(Width / 2, 2 * Height / 3);
        sp.getViewport().setBackground(new Color(24, 19, 11, 150));
        sp.setBackground(new Color(0, 0, 0, 0));
        sp.setLocation(Width / 2 - sp.getWidth() / 2, Height / 2 - sp.getHeight() / 2);
        delete.setLocation(Width / 2 - delete.getWidth() / 2, Height / 2 + sp.getHeight() / 2 + 20);


        add(load);
        add(delete);
        add(sp);
        add(back);
        add(backGround);
        Cursor.Idle(frame);

    }

    private void loadGame(String name) {
        try {

            FileInputStream fileInputStream = new FileInputStream("saves\\" + name + ".txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedInformation information = (SavedInformation) objectInputStream.readObject();

            GameGUI gameGUI = new GameGUI(information);

            frame.getContentPane().removeAll();
            frame.repaint();
            frame.add(new LoadingScene(gameGUI, true));
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteGame(String name) {
        try {
            Files.delete(Paths.get("saves\\" + name + ".txt"));
            updateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new LoadGameScene());
        frame.setVisible(true);
    }
}
