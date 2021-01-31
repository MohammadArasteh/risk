package UI.Scene.SelectPlayerScene;

import Game.Objects.House;
import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;
import UI.Effects.Sounds;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static Main.Main.frame;
import static UI.Effects.Sounds.*;

/*
    This class is a helper class to display Player Inputs (avatar and userName)
    used in SelectPlayerScene
 */
public class PlayerGUI extends JComponent {

    private JLabel avatar, userName;
    private JButton selectAvatar;
    private Icon buttonIcon;
    private Icon buttonIconFocus;
    private JTextField userNameField;
    private int avatar_id = 0;
    private ChangeableTeamButton teamButton;
    private JLabel teamButtonPlace;

    /* id is used to get Avatar label from Avatars class
     you can make a PlayerGUI with custom avatar using this constructor

     example code :
     PlayerGUI myGUI = new PlayerGUI(2);
     frame.add(myGUI);

    */
    public PlayerGUI(int id) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        avatar = Avatars.get(id);
        avatar.setBorder(BorderFactory.createEmptyBorder());
        avatar.setOpaque(false);
        avatar.setBackground(new Color(0, 0, 0, 0));

        userName = new JLabel();

        selectAvatar = new JButton();
        selectAvatar.setMargin(new Insets(0, 0, 0, 0));
        buttonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\Avatar.png")));
        buttonIconFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\AvatarFocus.png")));
        selectAvatar.setIcon(buttonIcon);
        selectAvatar.setBorder(BorderFactory.createEmptyBorder());
        selectAvatar.addMouseListener(new MouseListener() {
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
                selectAvatar.setIcon(buttonIconFocus);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                selectAvatar.setIcon(buttonIcon);
            }
        });
        selectAvatar.addActionListener(event -> {
            SelectAvatarMenu menu = new SelectAvatarMenu(this);

        });

        userNameField = new JTextField();
        userNameField.setFont(new Font("Sitka Small", Font.PLAIN, 30));
        userNameField.setBackground(Color.decode("#282828"));
        userNameField.setForeground(Color.decode("#ffb200"));
        userNameField.setText("Nick Name");
        userNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Sounds.playSound("src\\Resource\\Sounds\\SFX\\typing.wav");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Sounds.playSound("src\\Resource\\Sounds\\SFX\\typing.wav");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        teamButtonPlace = new JLabel();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Resource\\Images\\blank.png")));
        teamButtonPlace.setIcon(icon);
        add(teamButtonPlace);
        teamButtonPlace.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        add(avatar);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(selectAvatar);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(userNameField);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(teamButtonPlace);

        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamButtonPlace.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void addTeamButton(ChangeableTeamButton input) {
        this.teamButton = input;
        teamButtonPlace.add(teamButton);
        revalidate();
        repaint();
    }

    public JLabel getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar_id) {
        this.avatar_id = avatar_id;
        this.avatar.setIcon(Avatars.get(avatar_id).getIcon());
        revalidate();
        repaint();
    }

    public String getUserName() {
        return userNameField.getText();
    }

    public boolean validateUserName() {
        String regex = "`\\~!@#$%^&*()_-+=[]';:}.{<>,/?\"]";
        boolean containInvalid = false;
        String userName = getUserName();
        for (int i = 0; i < userName.length(); i++) {
            if (regex.contains(userName.charAt(i) + "")) containInvalid = true;
        }
        return !(userName.length() <= 3) && !containInvalid && userName.length() <= 15;

    }

    public void showGUI(boolean state) {
        teamButtonPlace.setVisible(state);
        selectAvatar.setVisible(state);
        userNameField.setVisible(state);
    }

    public void showName() {
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userName.setSize(100, 50);
        userName.setFont(new Font("Sitka Small", Font.PLAIN, 30));
        userName.setForeground(Color.decode("#ffb200"));
        add(userName);
        revalidate();
        repaint();
    }

    public void setUsername(String username) {
        userName.setText(username);
        revalidate();
        repaint();
    }

    public void showUsername(boolean state) {
        userName.setVisible(state);
    }

    public int getAvatar_id() {
        return avatar_id;
    }

    public House getHouse() {
        return teamButton.getHouse();
    }
}
