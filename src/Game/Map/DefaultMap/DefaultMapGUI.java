package Game.Map.DefaultMap;

import Game.Map.State;
import Resource.Images.Avatars.Avatars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class DefaultMapGUI extends DefaultMap {

    transient JPanel content;
    transient JLabel backGround;
    transient Icon backGroundIcon;
    private String backGroundPath;

    public DefaultMapGUI(String backGroundPath, boolean stateVoice) {
        super(stateVoice);
        this.backGroundPath = backGroundPath;
    }
    public void init() {
        this.content = new JPanel(null);
        this.content.setSize(1200,798);
        this.content.setBackground(new Color(0,0,0,120));

        this.backGround = new JLabel();
        this.backGround.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if( gameManager != null)
                    gameManager.resetAttack();
            }

            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        this.backGroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGroundPath)));
        this.backGround.setIcon(backGroundIcon);
        this.backGround.setSize(1200,798);
        Avatars.changeLabelSize(backGround,1200,798);
        this.content.setLocation(676,142);

        for(State state : this.States)
            state.initGraphic();

        this.States.get(0).getContent().setLocation(38,59);
        this.States.get(1).getContent().setLocation(146,191);
        this.States.get(2).getContent().setLocation(168,53);
        this.States.get(3).getContent().setLocation(218,148);
        this.States.get(4).getContent().setLocation(145,282);
        this.States.get(5).getContent().setLocation(242,246);
        this.States.get(6).getContent().setLocation(293,143);
        this.States.get(7).getContent().setLocation(193,353);
        this.States.get(8).getContent().setLocation(383,76);
        this.States.get(9).getContent().setLocation(263,398);
        this.States.get(10).getContent().setLocation(343,538);
        this.States.get(11).getContent().setLocation(218,538);
        this.States.get(12).getContent().setLocation(263,668);
        this.States.get(13).getContent().setLocation(478,98);
        this.States.get(14).getContent().setLocation(453,188);
        this.States.get(15).getContent().setLocation(595,138);
        this.States.get(16).getContent().setLocation(577,213);
        this.States.get(17).getContent().setLocation(454,318);
        this.States.get(18).getContent().setLocation(572,347);
        this.States.get(19).getContent().setLocation(665,213);
        this.States.get(20).getContent().setLocation(702,408);
        this.States.get(21).getContent().setLocation(776,296);
        this.States.get(22).getContent().setLocation(815,357);
        this.States.get(23).getContent().setLocation(791,125);
        this.States.get(24).getContent().setLocation(997,440);
        this.States.get(25).getContent().setLocation(974,342);
        this.States.get(26).getContent().setLocation(860,59);
        this.States.get(27).getContent().setLocation(975,218);
        this.States.get(28).getContent().setLocation(910,192);
        this.States.get(29).getContent().setLocation(973,32);
        this.States.get(30).getContent().setLocation(1042,101);
        this.States.get(31).getContent().setLocation(1100,222);
        this.States.get(32).getContent().setLocation(522,454);
        this.States.get(33).getContent().setLocation(628,418);
        this.States.get(34).getContent().setLocation(657,499);
        this.States.get(35).getContent().setLocation(555,590);
        this.States.get(36).getContent().setLocation(616,727);
        this.States.get(37).getContent().setLocation(762,705);
        this.States.get(38).getContent().setLocation(964,596);
        this.States.get(39).getContent().setLocation(1108,516);
        this.States.get(40).getContent().setLocation(1012,650);
        this.States.get(41).getContent().setLocation(1124,703);

        for(int index = 0; index < 42; index++)
            this.backGround.add(States.get(index).getContent());

        this.content.add(backGround);

    }

    public JPanel getContent() {
        return  this.content;
    }
}
