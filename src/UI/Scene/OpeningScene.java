package UI.Scene;

import HelpingClasses.FadingText;
import UI.Menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.ButtonsSound;

public class OpeningScene extends JPanel {

    private FadingText text;

    public OpeningScene() {
        MainMenu.firstJoin = false;
        setLayout(new BorderLayout());
        setVisible(true);
        JLabel label = new JLabel();
        Icon bgIcon = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\opening.jpg")));
        label.setIcon(bgIcon);
        label.setSize(frame.getBounds().width, frame.getBounds().height);

        add(ShowText());
        add(label);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OnClicked();
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
    }

    public void OnClicked() {
        ButtonsSound("play");
        frame.getContentPane().removeAll();
        frame.repaint();
        MainMenu scene = new MainMenu();
        frame.add(scene);
        frame.setVisible(true);
    }

    public JLabel ShowText() {

        text = new FadingText("PRESS ANY BUTTON TO START", 20, Color.WHITE);
        text.setFont(new Font("Sitka Small", Font.PLAIN, 50));
        text.setBackground(Color.white);
        text.setBounds(frame.getBounds().width / 2 - 400,
                Height - Height / 8 - text.getHeight(), 900, 75);
        return text;
    }
}
