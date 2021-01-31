package Game.GUI.WinnerScene;

import Game.Player.Player;
import Game.Player.Team;
import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;
import UI.Menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.*;
import static UI.Effects.Sounds.loop;

public class WinnerScene extends JPanel {


    private JButton menu;
    private Icon menuIdle, menuFocus;

    private Team winner;

    public WinnerScene(Team winnerTeam) {
        this.winner = winnerTeam;
        setSize(Width,Height);
        setGraphic();

        loop = false;
        CurrentSound = "Ending";
        BackgroundMusic("src\\Resource\\Sounds\\BackGroundSound\\Ending0.mp3");
    }
    private void setGraphic() {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0,0));
        panel.setSize(Width,750);
        panel.setLocation(0,(Height - 750)/2);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));

        menuIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\MenuIdle.png")));
        menuFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\MenuFocus.png")));
        menu = new JButton(menuFocus);
        menu.setBorderPainted(false);
        menu.setFocusPainted(false);
        menu.setContentAreaFilled(false);
        menu.setSize(250,93);
        menu.setLocation(20,20);
        menu.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                ButtonsSound("play");
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.add(new MainMenu());
                frame.setVisible(true);

                loop = false;
                CurrentSound = "backGroundMusic";
                BackgroundMusic("src\\Resource\\Sounds\\BackGroundSound\\backGroundMusic0.mp3");
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                menu.setIcon(menuFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                menu.setIcon(menuIdle);
            }
        });
        Avatars.changeButtonSize(menu,250,93);
        menu.setIcon(menuIdle);
        Avatars.changeButtonSize(menu,250,93);

        ImageIcon backGroundIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\WinnerScene\\winnerBackGround.png")));
        JLabel backGround = new JLabel(backGroundIcon);
        backGround.setSize(Width,Height);

        ImageIcon houseLabelIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\WinnerScene\\" + winner.house + "Label.png")));
        JLabel houseLabel = new JLabel(houseLabelIcon);
        houseLabel.setSize(Width,188);
        houseLabel.setLocation(0,830);

        for(Player player : winner.getMembers())
        {
            Award award = new Award(player,1);
            panel.add(award);
        }

        add(menu);
        add(houseLabel);
        add(panel);
        add(backGround);

        Cursor.Idle(frame);

    }

}
