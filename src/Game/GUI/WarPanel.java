package Game.GUI;

import Game.Objects.Dice;
import Game.Objects.War;
import HelpingClasses.Cursor;
import HelpingClasses.CustomMethods;
import Resource.Images.Avatars.Avatars;
import UI.Effects.Sounds;
import UI.Scene.SelectPlayerScene.PlayerGUI;

import static Main.Main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;

import static Main.Main.frame;

/**
 * @author : Mohammad Reza Arasteh
 * @version 1.0
 * @since 5/3/2020
 */

public class WarPanel {

    private JDialog window;

    private JPanel background,parent,panel,left,right,footer,center;
    private JLabel rightSoldiers, leftSoldiers;
    private JButton cancel , roll, again , close;
    private Icon cancelIdle,cancelFocus,rollIdle,rollFocus, againIdle, againFocus , closeIdle, closeFocus;

    private War war;
    private List<Dice> attackersDice , defendersDice;

    public WarPanel(War war) {

        window = new JDialog(frame);
        window.setLayout(null);
        window.setSize(Width,Height);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setBackground(new Color(0,0,0,0));

        this.war = war;
        WarPanel instance = this;

        panel = new JPanel(new BorderLayout());
        //panel.setSize(1920/3,3*1080/4);
        panel.setLocation(Width/2 - panel.getWidth()/2 , Height/2 - panel.getHeight()/2);

        right = new JPanel();
        right.setLayout(new GridLayout(0,1));
        rightSoldiers = new JLabel();
        rightSoldiers.setFont(new Font("Sitka", Font.PLAIN,40));
        rightSoldiers.setText(war.getStatesSoldiersCount()[1] + "");

        left = new JPanel();
        left.setLayout(new GridLayout(0,1));
        leftSoldiers = new JLabel();
        leftSoldiers.setFont(new Font("Sitka", Font.PLAIN,40));
        leftSoldiers.setText(war.getStatesSoldiersCount()[0] + "");

        center = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        center.add(leftSoldiers);
        center.add(rightSoldiers);
        center.setPreferredSize(new Dimension(500,700));

        againIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\AgainIdle.png")));
        againFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\AgainFocus.png")));
        again = new JButton(againFocus);
        again.setFocusPainted(false);
        again.setContentAreaFilled(false);
        again.setBorderPainted(false);
        again.setSize(150,56);
        again.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) {
                Cursor.Select(window);
                again.setIcon(againFocus);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(window);
                again.setIcon(againIdle);
            }
        });
        again.addActionListener(e -> {
            makeWar();
            window.dispatchEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSING));
        });
        Avatars.changeButtonSize(again,150,56);
        again.setIcon(againIdle);
        Avatars.changeButtonSize(again,150,56);

        closeIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\CloseIdle.png")));
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
                Cursor.Select(window);
                close.setIcon(closeFocus);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(window);
                close.setIcon(closeIdle);
            }
        });
        close.addActionListener(e -> {
            window.dispatchEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSING));
            if(war.getManager().isFinished()) {
                war.getManager().finishGame();
            }
        });
        Avatars.changeButtonSize(close,150,56);
        close.setIcon(closeIdle);
        Avatars.changeButtonSize(close,150,56);

        cancelIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\RetreatIdle.png")));
        cancelFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\RetreatFocus.png")));
        cancel = new JButton(cancelFocus);
        cancel.setBorderPainted(false);
        cancel.setFocusPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setSize(150,56);
        cancel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(window);
                cancel.setIcon(cancelFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(window);
                cancel.setIcon(cancelIdle);
            }
        });
        cancel.addActionListener(e -> {
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\Retreat" + CustomMethods.random(1,3) + ".mp3");
            frame.revalidate();
            frame.repaint();
            window.dispatchEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSING));
        });
        Avatars.changeButtonSize(cancel,150,56);
        cancel.setIcon(cancelIdle);
        Avatars.changeButtonSize(cancel,150,56);

        rollIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\RollIdle.png")));
        rollFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\RollFocus.png")));
        roll = new JButton(rollFocus);
        roll.setBorderPainted(false);
        roll.setFocusPainted(false);
        roll.setContentAreaFilled(false);
        roll.setSize(150,56);
        roll.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {
                roll.setIcon(rollFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roll.setIcon(rollIdle);
            }
        });
        roll.addActionListener(e -> {
            roll.setEnabled(false);
            cancel.setEnabled(false);
            for(Dice dice : attackersDice) dice.rollAnimation();
            for (Dice dice : defendersDice) dice.rollAnimation();
            Sounds.playSound("src\\Resource\\Sounds\\DiceShaking.wav");
            new Timer(100, new ActionListener() {
                int counter = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    counter++;
                    if(counter > 20) {
                        for(Dice dice : attackersDice) dice.rollStop();
                        for (Dice dice : defendersDice) dice.rollStop();
                        war.action();
                        cancel.setEnabled(true);
                        leftSoldiers.setText(war.getStatesSoldiersCount()[0] + "");
                        rightSoldiers.setText(war.getStatesSoldiersCount()[1] + "");
                        if(war.canFightAgain()) {
                            footer.remove(roll);
                            footer.add(again);
                            footer.revalidate();
                            footer.repaint();
                        }
                        else {
                            footer.remove(roll);
                            footer.remove(cancel);
                            footer.add(close);
                            footer.revalidate();
                            footer.repaint();
                        }
                        ((Timer)e.getSource()).stop();
                    }
                }
            }).start();

            panel.revalidate();
            panel.repaint();
        });
        Avatars.changeButtonSize(roll,150,56);
        roll.setIcon(rollIdle);
        Avatars.changeButtonSize(roll,150,56);

        footer = new JPanel(new FlowLayout(FlowLayout.CENTER,1,10));
        footer.add(cancel);
        footer.add(roll);
        cancel.setAlignmentY(.5f);
        roll.setAlignmentY(.5f);
        again.setAlignmentY(.5f);

        war.start();
        attackersDice = war.getAttackersDice();
        defendersDice = war.getDefendersDice();


        for(Dice dice : attackersDice) {
            dice.resetImage();
            left.add(dice);
        }

        for(Dice dice : defendersDice) {
            dice.resetImage();
            right.add(dice);
        }

        background = new JPanel();
        background.setSize(1920,1080);
        background.setBackground(new Color(0,0,0,200));

        panel.add(footer,BorderLayout.SOUTH);
        panel.add(left,BorderLayout.WEST);
        panel.add(right,BorderLayout.EAST);
        panel.add(center,BorderLayout.CENTER);

        int diceWidth = 140;
        int diceHeight = 155;

        right.setPreferredSize(new Dimension(diceWidth,3*diceHeight + 50));
        left.setPreferredSize(new Dimension(diceWidth,3*diceHeight + 50));
        center.setPreferredSize(new Dimension(100,100));
        footer.setPreferredSize(new Dimension(500,70));

        right.setBackground(Color.decode("#a3b1c0"));
        left.setBackground(Color.decode("#a3b1c0"));
        center.setBackground(Color.decode("#a3b1c0"));
        footer.setBackground(Color.decode("#ffb200"));

        parent = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        PlayerGUI attacker = war.getAttacker().getOwner().getGUI();
        attacker.showGUI(false);
        attacker.showName();
        PlayerGUI defender = war.getDefender().getOwner().getGUI();
        defender.showGUI(false);
        defender.showName();
        parent.add(attacker);
        parent.add(panel);
        parent.add(defender);
        parent.setSize(1500,800);
        parent.setBackground(new Color(0,0,0,0));
        parent.setLocation(Width/2 - parent.getWidth()/2,Height/2 - parent.getHeight()/2);

        window.add(parent);
        window.add(background);
        window.setVisible(true);
        Cursor.Idle(window);
        Cursor.Idle(frame);
    }
    private void makeWar() {
        WarPanel panel = new WarPanel(war);
    }
}
