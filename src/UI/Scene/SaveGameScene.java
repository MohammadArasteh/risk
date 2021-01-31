package UI.Scene;

import Game.GUI.GameGUI;
import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;
import UI.Effects.Sounds;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.*;

public class SaveGameScene {

    private JDialog window;
    private JPanel panel;

    private JLabel description;
    private JTextField textField;
    private JButton submit, close;
    private ImageIcon submitIdle, submitFocus, closeIdle, closeFocus;


    public SaveGameScene(GameGUI gui) {
        window = new JDialog(frame);
        window.setLayout(new GridBagLayout());
        window.setSize(Width, Height);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setBackground(new Color(0, 0, 0, 200));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        description = new JLabel("Please enter game name in the box below");
        description.setFont(new Font("Sitka", Font.TRUETYPE_FONT, 20));

        textField = new JTextField();
        textField.setSize(100, 120);
        textField.setFont(new Font("Arial", Font.TRUETYPE_FONT, 30));
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                Sounds.playSound("src\\Resource\\Sounds\\SFX\\typing.wav");
                if (validate(textField.getText()))
                    submit.setEnabled(true);
                else submit.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Sounds.playSound("src\\Resource\\Sounds\\SFX\\typing.wav");
                if (validate(textField.getText()))
                    submit.setEnabled(true);
                else submit.setEnabled(false);
            }

            public void changedUpdate(DocumentEvent e) {

            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        submitIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SubmitIdle.png")));
        submitFocus = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SubmitFocus.png")));

        submit = new JButton(submitFocus);
        Avatars.changeButtonSize(submit, 200, 74);
        submit.setIcon(submitIdle);
        Avatars.changeButtonSize(submit, 200, 74);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(false);
        submit.setFocusPainted(false);
        submit.setSize(344, 128);
        submit.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (submit.isEnabled())
                    ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (submit.isEnabled()) {
                    playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                    submit.setIcon(submitFocus);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submit.setIcon(submitIdle);
            }
        });
        submit.setEnabled(false);
        submit.addActionListener(e -> {
            String saveName = textField.getText();
            if (validate(saveName)) {
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                gui.saveGame(saveName);
            }
        });

        closeIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\CloseIdle.png")));
        closeFocus = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\CloseFocus.png")));

        close = new JButton(closeFocus);
        Avatars.changeButtonSize(close, 200, 74);
        close.setIcon(closeIdle);
        Avatars.changeButtonSize(close, 200, 74);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
        close.setFocusPainted(false);
        close.setSize(344, 128);
        close.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                close.setIcon(closeFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                close.setIcon(closeIdle);
            }
        });
        close.addActionListener(e -> {
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        });

        buttonPanel.add(close);
        buttonPanel.add(submit);

        description.setAlignmentX(.5f);
        textField.setAlignmentX(.5f);
        buttonPanel.setAlignmentY(.5f);

        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(description);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(textField);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(buttonPanel);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        window.add(panel);
        window.setVisible(true);
        Cursor.Idle(window);
    }

    private boolean validate(String string) {
        if (string.isEmpty()) return false;
        String invalids = "!@#$%^&*()][_+-=\\|/?.>,<{}'\";:";
        for (int index = 0; index < string.length(); index++)
            if (invalids.contains(string.charAt(index) + ""))
                return false;
        return true;
    }
}
