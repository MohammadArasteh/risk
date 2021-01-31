package Game.GUI;

import Game.Map.State;
import Resource.Images.Avatars.Avatars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Objects;

import static Main.Main.*;

public class SelectSoldiers {

    private JDialog window;
    private JPanel panel;
    private JSlider soldiersSlider;
    private JLabel current;
    private JButton close,send;
    private ImageIcon closeIdle, closeFocus, sendIdle, sendFocus;

    public SelectSoldiers(State origin,State destination) {

        window = new JDialog(frame);
        window.setSize(Width,Height);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setBackground(new Color(0,0,0,0));

        JPanel backGround = new JPanel();
        backGround.setSize(Width,Height);
        backGround.setBackground(new Color(0,0,0,200));

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
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#f6b36c"));

        JPanel header = new JPanel();
        header.setBackground(new Color(0,0,0,0));
        header.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        JLabel min = new JLabel("1");
        min.setFont(new Font("Arial",Font.BOLD,30));
        min.setForeground(Color.decode("#382011"));
        JLabel max = new JLabel(String.valueOf(origin.getSoldiersCount() - 1));
        max.setFont(new Font("Arial",Font.BOLD,30));
        max.setForeground(Color.decode("#382011"));
        soldiersSlider = new JSlider();
        soldiersSlider.setMinimum(Integer.parseInt(min.getText()));
        soldiersSlider.setMaximum(Integer.parseInt(max.getText()));
        soldiersSlider.setValue(soldiersSlider.getMaximum());
        soldiersSlider.addChangeListener(e -> {
            current.setText(String.valueOf(soldiersSlider.getValue()));
            panel.revalidate();
            panel.repaint();
        });
        soldiersSlider.setSize(200,20);
        header.add(min);
        header.add(soldiersSlider);
        header.add(max);

        JPanel body = new JPanel();
        body.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        body.setBackground(new Color(0,0,0,0));
        current = new JLabel(max.getText());
        current.setForeground(Color.decode("#382011"));
        current.setFont(new Font("Arial",Font.BOLD,30));
        ImageIcon soldierIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\soldierIcon.png")));
        JLabel soldier = new JLabel(soldierIcon);
        Avatars.changeLabelSize(soldier,50,80);
        soldier.setSize(50,80);
        body.add(soldier);
        body.add(current);

        closeIdle = new ImageIcon(java.util.Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\CloseIdle.png")));
        closeFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\CloseFocus.png")));
        close = new JButton(closeFocus);
        close.setFocusPainted(false);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        close.setSize(150,56);
        close.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) {
                close.setIcon(closeFocus);
            }

            public void mouseExited(MouseEvent e) {
                close.setIcon(closeIdle);
            }
        });
        close.addActionListener(e -> window.dispatchEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSING)));
        Avatars.changeButtonSize(close,150,56);
        close.setIcon(closeIdle);
        Avatars.changeButtonSize(close,150,56);

        sendIdle = new ImageIcon(java.util.Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SendIdle.png")));
        sendFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SendFocus.png")));
        send = new JButton(sendFocus);
        send.setFocusPainted(false);
        send.setContentAreaFilled(false);
        send.setBorderPainted(false);
        send.setSize(150,56);
        send.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) {
                send.setIcon(sendFocus);
            }

            public void mouseExited(MouseEvent e) {
                send.setIcon(sendIdle);
            }
        });
        send.addActionListener(e -> {
            origin.sendTroopsTo(destination, soldiersSlider.getValue());
            window.dispatchEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSING));
        });
        Avatars.changeButtonSize(send,150,56);
        send.setIcon(sendIdle);
        Avatars.changeButtonSize(send,150,56);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        footer.setBackground(new Color(0,0,0,0));
        footer.add(close);
        footer.add(send);

        panel.add(header);
        panel.add(Box.createRigidArea(new Dimension(10,10)));
        panel.add(body);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(footer);
        panel.setSize(450,300);
        panel.setLocation(Width/2 - panel.getWidth()/2,Height/2 - panel.getHeight()/2);

        window.add(panel);
        window.add(backGround);
        window.setVisible(true);

    }
}
