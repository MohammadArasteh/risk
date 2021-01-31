package Game.GUI;

import Game.Objects.Flag;
import Game.Player.Player;
import UI.Scene.SelectPlayerScene.PlayerGUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class UsernameField implements Serializable {
    private JLabel label;
    private Icon on, off;

    private Player player;

    public UsernameField(Player player) {
        this.label = new JLabel();
        String username = player.getUserName();
        this.player = player;
        this.setIcons(player.getFlag());

        this.label.setText(username);
        this.label.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN,25));
        this.label.setForeground(Color.decode("#0a0a0a"));
        this.label.setHorizontalTextPosition(JLabel.CENTER);
        this.label.setVerticalTextPosition(JLabel.CENTER);
    }
    private void setIcons(Flag flag)
    {
        this.off = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Field\\UNDEFINED.png")));
        switch (flag)
        {
            case YELLOW:
                this.on = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Field\\YELLOW.png")));
                break;
            case RED:
                this.on = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Field\\RED.png")));
                break;
            case GREEN:
                this.on = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Field\\GREEN.png")));
                break;
            case BLUE:
                this.on = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Field\\BLUE.png")));
                break;

        }
    }
    public void on() {
        this.label.setIcon(on);
    }
    public void off() {
        this.label.setIcon(off);
    }

    public Player getPlayer() {
        return  this.player;
    }
    public PlayerGUI getGUI() {
        return  this.player.getGUI();
    }
    public JLabel getLabel() {
        return  this.label;
    }
}
