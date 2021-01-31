package UI.Scene.SelectPlayerScene;

import Game.Objects.House;
import HelpingClasses.Cursor;

import static UI.Effects.Sounds.*;
import static Main.Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class ChangeableTeamButton extends JLabel {

    private static Color red = Color.RED;
    private static Color blue = Color.BLUE;
    private static Color green = Color.GREEN;
    private static Color yellow = Color.YELLOW;
    private ImageIcon icon;
    private House house;

    public ChangeableTeamButton(House house) {
        this.house = house;
        setColor();
        setContent();
    }

    private void setContent() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                next();
                setColor();
            }

            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
            }

            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
            }
        });
    }

    private void next() {
        switch (house) {
            case STARK:
                house = House.BARATHEON;
                break;
            case BARATHEON:
                house = House.MARTEL;
                break;
            case MARTEL:
                house = House.LANNISTER;
                break;
            case LANNISTER:
                house = House.STARK;
                break;
        }
    }

    private void setColor() {
        switch (house) {
            case STARK:
                icon = new ImageIcon(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("Resource\\Images\\Team1.png")));
                setIcon(icon);
                break;
            case LANNISTER:
                icon = new ImageIcon(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("Resource\\Images\\Team2.png")));
                setIcon(icon);
                break;
            case BARATHEON:
                icon = new ImageIcon(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("Resource\\Images\\Team3.png")));
                setIcon(icon);
                break;
            case MARTEL:
                icon = new ImageIcon(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("Resource\\Images\\Team4.png")));
                setIcon(icon);
                break;
        }
    }

    public House getHouse() {
        return house;
    }
}