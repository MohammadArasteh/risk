package Game.GUI.WinnerScene;

import Game.GUI.UsernameField;
import Game.Player.Player;
import Resource.Images.Avatars.Avatars;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Award extends JPanel {

    private Player player;
    private int rank;

    private JLabel rankLabel;
    private JLabel playerAvatar;
    private ImageIcon rankIcon;

    public Award(Player player, int rank)
    {
        this.player = player;
        this.rank = rank;

        setContent();
    }
    private void setContent()
    {
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        setMinimumSize(new Dimension(589,573));
        setMaximumSize(new Dimension(589,573));
        setPreferredSize(new Dimension(589,573));

        ImageIcon wheatAwardIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\WinnerScene\\WheatAward.png")));
        JLabel wheatAward = new JLabel(wheatAwardIcon);
        wheatAward.setLocation(0,17);
        wheatAward.setSize(589,562);

        rankLabel = new JLabel();
        setRankIcon();
        rankLabel.setSize(128,163);
        rankLabel.setLocation(232,370);

        UsernameField field = new UsernameField(player);
        field.on();
        field.getLabel().setLocation(171,513);
        field.getLabel().setSize(250,59);

        playerAvatar = player.getGUI().getAvatar();
        Avatars.changeLabelSize(playerAvatar,293,452);
        playerAvatar.setSize(293,452);
        playerAvatar.setLocation(147,32);

        add(rankLabel);
        add(wheatAward);
        add(field.getLabel());
        add(playerAvatar);
    }
    private void setRankIcon()
    {
        switch (rank)
        {
            case 1 :
                rankIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\WinnerScene\\firstPlace.png")));
                break;
            case 2 :
                rankIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\WinnerScene\\secondPlace.png")));
                break;
            case 3 :
                rankIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\WinnerScene\\thirdPlace.png")));
                break;
        }
        rankLabel.setIcon(rankIcon);
    }

}
