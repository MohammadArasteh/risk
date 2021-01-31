package UI.Scene.SelectPlayerScene;

import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static Main.Main.*;

public class SelectAvatarMenu {

    private JDialog window;
    private JPanel panel;
    private SelectAvatarMenu instance = this;

    public SelectAvatarMenu(PlayerGUI GUI) {

        window = new JDialog(frame);
        window.setSize(Width, Height);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setBackground(new Color(0, 0, 0, 0));
        Cursor.Select(window);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
        panel.setSize(650, Height);
        panel.setPreferredSize(new Dimension(650, Height));
        panel.setBackground(new Color(0, 0, 0, 200));

        AvatarButton[] avatars = new AvatarButton[21];
        new Timer(20, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                avatars[index] = new AvatarButton(instance, GUI, Avatars.get(index, 200, 309), index);
                panel.add(avatars[index]);
                window.revalidate();
                window.repaint();
                index++;
                if (index >= 21) {
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();

        window.add(panel);
        window.setVisible(true);
        Cursor.Idle(window);
        Cursor.Idle(frame);
    }

    public void close() {
        frame.revalidate();
        frame.repaint();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
}
