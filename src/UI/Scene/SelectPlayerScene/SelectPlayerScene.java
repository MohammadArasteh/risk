package UI.Scene.SelectPlayerScene;

import Game.Objects.House;
import Game.Player.Player;
import Game.Player.Team;
import HelpingClasses.Cursor;
import HelpingClasses.CustomMethods;
import UI.Effects.Sounds;
import UI.Menu.MainMenu;
import UI.Scene.SelectMapScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.playSound;

public class SelectPlayerScene extends JPanel {

    private List<Player> players = new ArrayList<>();
    private List<PlayerGUI> cards = new ArrayList<>();

    private JButton add, remove;
    private Icon addIcon, addIconFocus, removeIcon, removeIconFocus;
    private JButton back;
    private Icon backIconIdle, backIconFocus;
    private JButton confirm;
    private Icon confirmIconIdle, confirmIconFocus;

    private JLabel errorLabel;

    private JPanel avatars;


    public SelectPlayerScene() {
        Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\AddGeneral" + CustomMethods.random(1, 2) + ".mp3");
        setLayout(null);

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
                back.setIcon(backIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                back.setIcon(backIconIdle);
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

        confirmIconIdle = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\selectMapIdle.png")));
        confirmIconFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\selectMapFocus.png")));

        confirm = new JButton(confirmIconIdle);
        confirm.setBorderPainted(false);
        confirm.setMargin(new Insets(0, 0, 0, 0));
        confirm.setBorder(BorderFactory.createEmptyBorder());
        confirm.setSize(344, 128);
        confirm.setLocation(Width - 20 - confirm.getWidth(), Height - 20 - confirm.getHeight());
        confirm.addMouseListener(new MouseListener() {
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
                confirm.setIcon(confirmIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                confirm.setIcon(confirmIconIdle);
            }
        });

        addIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\AddIdle.png")));
        addIconFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\AddFocus.png")));
        add = new JButton(addIcon);
        add.setMargin(new Insets(0, 0, 0, 0));
        add.setBorder(BorderFactory.createEmptyBorder());
        add.setSize(100, 62);
        add.addMouseListener(new MouseListener() {
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
                add.setIcon(addIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                add.setIcon(addIcon);
            }
        });

        removeIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\RemoveIdle.png")));
        removeIconFocus = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\RemoveFocus.png")));

        remove = new JButton(removeIcon);
        remove.setMargin(new Insets(0, 0, 0, 0));
        remove.setBorder(BorderFactory.createEmptyBorder());
        remove.setSize(100, 62);
        remove.addMouseListener(new MouseListener() {
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
                remove.setIcon(removeIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                remove.setIcon(removeIcon);
            }
        });
        remove.setVisible(false);

        Icon backGroundIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("Resource\\Images\\BackGround.png")));
        JLabel backGround = new JLabel(backGroundIcon);
        backGround.setSize(Width, Height);

        errorLabel = new JLabel();
        errorLabel.setSize(700, 100);
        errorLabel.setLocation(Width / 2 - 250, Height - Height / 8);
        errorLabel.setFont(new Font("Sitka Small", Font.PLAIN, 30));
        errorLabel.setForeground(Color.decode("#ff0000"));

        avatars = new JPanel();
        avatars.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        avatars.setSize(Width, 830);
        avatars.setLocation(0, 150);
        avatars.setBackground(new Color(0, 0, 0, 0));

        JPanel head = new JPanel();
        head.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        head.add(remove);
        head.add(add);
        head.setSize(300, 80);
        head.setLocation(Width / 2 - 150, 50);
        head.setBackground(new Color(0, 0, 0, 0));


        add(head);
        add(avatars);
        add(errorLabel);
        add(back);
        add(confirm);
        add(backGround);
        Cursor.Idle(frame);

        ActionListeners();

    }

    private void ActionListeners() {
        add.addActionListener(event -> {

            PlayerGUI gui = new PlayerGUI(0);
            ChangeableTeamButton teamButton = new ChangeableTeamButton(House.values()[cards.size()]);
            gui.addTeamButton(teamButton);

            avatars.add(gui);
            cards.add(gui);
            if (cards.size() >= 1) remove.setVisible(true);
            revalidate();
            repaint();
            if (cards.size() >= 4) add.setVisible(false);
        });

        remove.addActionListener(event -> {
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\GeneralDeleted" + CustomMethods.random(1, 4) + ".mp3");
            if (cards.size() == 4) add.setVisible(true);
            avatars.remove(avatars.getComponentCount() - 1);
            cards.remove(cards.size() - 1);
            if (cards.size() == 0) remove.setVisible(false);
            revalidate();
            repaint();
        });
        confirm.addActionListener(event -> {
            if (validateInputs() && userNamesNotRepeated() && cards.size() > 1 && !SameHouses()) {
                createAndSendPlayers();
            } else {
                if (!validateInputs()) {
                    showError("Invalid Nicknames (maximum characters for nickname : 15)");
                } else if (!userNamesNotRepeated()) {
                    showError("Please use different nicknames");
                } else if (cards.size() <= 1) {
                    Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\NotEnoughGenerals" + CustomMethods.random(1, 4) + ".mp3");
                } else if (SameHouses()) {
                    showError("At Least 2 Teams Required");
                }
            }
        });
    }

    private void createAndSendPlayers() {
        players.clear();
        Team.clearHouses();

        cards.forEach(card -> {
            String userName = card.getUserName();
            Player player = new Player(userName, card.getAvatar_id());

            switch (card.getHouse()) {
                case STARK:
                    Team.HouseStark.addMember(player);
                    break;
                case BARATHEON:
                    Team.HouseBratheon.addMember(player);
                    break;
                case MARTEL:
                    Team.HouseMartel.addMember(player);
                    break;
                case LANNISTER:
                    Team.HouseLannister.addMember(player);
                    break;
            }

            players.add(player);
        });

        frame.getContentPane().removeAll();
        frame.repaint();
        SelectMapScene Map = new SelectMapScene(this, players);
        frame.add(Map);
        frame.setVisible(true);
        confirm.setIcon(confirmIconIdle);
    }

    private boolean validateInputs() {
        boolean result = true;

        for (PlayerGUI gui : cards) {
            if (!gui.validateUserName())
                result = false;
        }

        return result;
    }

    private boolean userNamesNotRepeated() {
        boolean result = true;

        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).getUserName().equalsIgnoreCase(cards.get(j).getUserName())) result = false;
            }
        }
        return result;
    }

    private boolean SameHouses() {

        for (int index = 0; index < cards.size(); index++) {
            for (int j = index + 1; j < cards.size(); j++) {
                if (!cards.get(index).getHouse().equals(cards.get(j).getHouse()))
                    return false;
            }
        }
        return true;
    }

    private void showError(String message) {
        errorLabel.setText("<html><div style='text-align: center;'>" + message + "</div></html>");
        errorLabel.setForeground(Color.decode("#ff0000"));


    }
}