package Game.GUI;

import Game.GUI.WinnerScene.WinnerScene;
import Game.Manager.Manager;
import Game.Map.CustomMap.CustomMapGUI;
import Game.Map.DefaultMap.DefaultMapGUI;
import Game.Objects.CustomTimer;
import Game.Objects.SavedInformation;
import Game.Objects.War;
import Game.Player.Player;
import Game.Player.Team;
import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;
import UI.Scene.SelectPlayerScene.PlayerGUI;
import Game.Manager.ManagerAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Main.Main.*;
import static UI.Effects.Sounds.ButtonsSound;
import static UI.Effects.Sounds.playSound;

/**
 * @author : Mohammad Reza Arasteh
 * @version 1.0
 * @since 5/3/2020
 */

public class GameGUI extends JPanel {

    private JLabel state, turnLabel, givenSoldersText;
    private Icon stateIcon,skipIdle,skipFocus,menuIdle,menuFocus;
    private JButton skip, menu;
    private JPanel userNameFields;
    private ArrayList<UsernameField> fields = new ArrayList<>();

    private Manager manager;
    private CustomTimer timer;
    private List<Player> players;
    private PlayerGUI playerProfile;

    private SavedInformation information;
    private Timer updateInitializer;

    public GameGUI(SavedInformation information) {
        if(information.isDefault)
        {
            this.players = information.getPlayers();
            this.manager = information.getManager();
            this.manager.setGraphic(this);
            this.manager.init();
            DefaultMapGUI defaultMapGUI = information.getDefaultMap();
            defaultMapGUI.init();
            this.add(defaultMapGUI.getContent());
            this.setGraphic();
        }
        else
        {
            this.players = information.getPlayers();
            this.manager = information.getManager();
            this.manager.setGraphic(this);
            this.manager.init();
            CustomMapGUI customMapGUI = information.getCustomMap();
            customMapGUI.init();
            this.add(customMapGUI.getContent());
            this.setGraphic();
        }
            this.information = information;
    }

    private void setGraphic() {
        setLayout(null);

        timer = manager.getTimer();
        timer.init();
        JLabel timerPanel = timer.getContent();
        timerPanel.setLocation(1700,970);
        add(timerPanel);

        Icon backGroundIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\gameBackGround.png")));
        JLabel backGround = new JLabel(backGroundIcon);
        backGround.setSize(Width,Height);

        //display state (Reinforce , Attack , Fortify)
        stateIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Starting.png")));
        state = new JLabel(stateIcon);
        state.setSize(501,197);
        state.setLocation(1047,954);

        menuIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\MenuIdle.png")));
        menuFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\MenuFocus.png")));
        menu = new JButton(menuFocus);
        menu.setSize(250,93);
        menu.setFocusPainted(false);
        menu.setContentAreaFilled(false);
        menu.setBorderPainted(false);
        menu.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }
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
        menu.addActionListener(e -> new Menu(this));
        Avatars.changeButtonSize(menu,250,93);
        menu.setIcon(menuIdle);
        Avatars.changeButtonSize(menu,250,93);
        menu.setLocation(20,20);


        //skip button
        skipIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\nextIdle.png")));
        skipFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\nextFocus.png")));
        skip = new JButton(skipIdle);
        skip.setSize(50,52);
        skip.setBorderPainted(false);
        skip.setFocusPainted(false);
        skip.setContentAreaFilled(false);
        skip.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) {
                skip.setIcon(skipFocus);
            }

            public void mouseExited(MouseEvent e) {
                skip.setIcon(skipIdle);
            }
        });
        skip.addActionListener(e -> manager.nextAction());
        skip.setLocation(1465,1011);

        //an avatar in the left size , which is used to display which player is playing
        playerProfile = new PlayerGUI(-1);
        playerProfile.setLocation(178,223);
        playerProfile.setSize(314,510);
        Avatars.changeLabelSize(playerProfile.getAvatar(),314,485);
        playerProfile.showUsername(false);
        playerProfile.showGUI(false);

        //players name in the top
        userNameFields = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        userNameFields.setSize(1200,100);
        userNameFields.setLocation(676,25);
        userNameFields.setBackground(new Color(0,0,0,0));


        for (Player player : players) {
            UsernameField field = new UsernameField(player);
            field.off();
            fields.add(field);
            userNameFields.add(field.getLabel());
        }

        //display given soldier to player
        Icon soldiersPlaceIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\SoldierPlace.png")));
        JLabel soldiersPlace = new JLabel(soldiersPlaceIcon);
        soldiersPlace.setLayout(null);
        soldiersPlace.setSize(348,304);
        soldiersPlace.setLocation(155,803);
        givenSoldersText = new JLabel("");
        givenSoldersText.setSize(200,200);
        givenSoldersText.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN,120));
        givenSoldersText.setForeground(Color.decode("#603409"));
        givenSoldersText.setLocation(50,70);
        soldiersPlace.add(givenSoldersText);


        turnLabel = new JLabel("Turn : " + manager.turnPlayed);
        turnLabel.setFont(new Font("Impact",Font.PLAIN,30));
        turnLabel.setForeground(Color.decode("#29c300"));
        turnLabel.setLocation(1720,1000);
        turnLabel.setSize(300,80);

        add(playerProfile);
        add(skip);
        add(soldiersPlace);
        add(userNameFields);
        add(menu);
        add(state);
        add(turnLabel);
        add(backGround);
        Cursor.Idle(frame);

        updateInitializer = new Timer(33, e -> Update());
        updateInitializer.start();
    }

    //Update is called once per frame
    private void Update() {

        if(manager.action == ManagerAction.INTRO)
            this.stateIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Starting.png")));
         else if(manager.action == ManagerAction.REINFORCE)
            this.stateIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Reinforce.png")));
         else if (manager.action == ManagerAction.ATTACK)
            this.stateIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Attack.png")));
         else if (manager.action == ManagerAction.FORTIFY)
            this.stateIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\GamePlayGUI\\Fortify.png")));

        this.state.setIcon(stateIcon);

        if (manager.action == ManagerAction.INTRO || manager.action == ManagerAction.REINFORCE)
            this.skip.setEnabled(false);
        else
            this.skip.setEnabled(true);




        if(this.manager.shuffled) {

            this.playerProfile.setAvatar(manager.currentPlayer.getAvatarID());
            this.playerProfile.setUsername(manager.currentPlayer.getUserName());
            Avatars.changeLabelSize(playerProfile.getAvatar(),314,485);

            if(manager.currentPlayer.getFreeSoldiersCount() != 0)
                 this.givenSoldersText.setText(manager.currentPlayer.getFreeSoldiersCount() + "");
            else this.givenSoldersText.setText("");

            for(int index = 0; index <  manager.playersSize(); index++)
            {
                if(index == manager.getCurrentPlayerIndex())
                    this.fields.get(index).on();
                 else
                    this.fields.get(index).off();

                revalidate();
                repaint();
            }

        }

    }
    public void stopGame()
    {
        this.updateInitializer.stop();
        this.manager.stopUpdate();
    }

    public void addWarPanel(War war) {
        new WarPanel(war);
    }
    public void removePlayer(Player player) {
        for (int index = this.fields.size() - 1; index >= 0; index--) {
            if(this.fields.get(index).getPlayer().equals(player))
            {
                this.userNameFields.remove(fields.get(index).getLabel());
                this.fields.remove(index);
                this.userNameFields.revalidate();
                this.userNameFields.repaint();
            }
        }
    }
    public void updateTurns()
    {
        if( this.manager.action != ManagerAction.INTRO)
            this.turnLabel.setText("Turn : " + manager.turnPlayed);
    }
    public void showWinner(Team team) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new WinnerScene(team));
        frame.setVisible(true);
    }
    public void saveGame(String name)
    {
        this.information.Write(name);
    }
    public CustomTimer getTimer() {
        return this.timer;
    }

}

