package UI.Scene.SelectPlayerScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static UI.Effects.Sounds.playSound;

public class AvatarButton extends Container {

    public AvatarButton(SelectAvatarMenu window, PlayerGUI reference, JLabel avatar, int avatar_id) {
        setLayout(new BorderLayout());

        add(avatar);
        avatar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                reference.setAvatar(avatar_id);
                window.close();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
