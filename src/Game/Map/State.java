package Game.Map;

import Game.Manager.Manager;
import Game.Objects.Flag;
import Game.Player.Player;
import Game.Player.Soldier;
import HelpingClasses.CustomMethods;
import Resource.Images.Buttons.StateButtons.StateButtons;
import UI.Effects.Sounds;
import Game.Manager.ManagerAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class State implements Serializable {

    private String Name;
    private Flag flag;

    private transient JButton StateButton;
    private transient Icon Idle, Selected;
    private transient JLabel SoldiersCountLabel, content;
    private int backGroundID;

    private Player Owner;
    private Manager gameManager;
    public List<State> Neighbors;
    private List<Soldier> Soldiers;

    private boolean selected = false;
    public boolean isSea = false;
    private boolean isDefault;
    private boolean hasNameVoice;

    //Constructors
    public State() {
        this.Neighbors = new ArrayList<>();
        this.Soldiers = new ArrayList<>();
    }
        //Used in DefaultMap
    public State(String name, boolean hasNameVoice) {
        this();
        this.hasNameVoice = hasNameVoice;
        this.setName(name);
        this.isDefault = true;
        this.initGraphic();
    }
        //Used in CustomMap
    public State(String name , int id) {
        this();
        hasNameVoice = false;
        this.setName(name);
        this.backGroundID = id;
        if(getName().equals("SEA"))
            this.isSea = true;
        this.isDefault = false;
        this.initGraphic();
    }
    public void initGraphic() {
        this.content = new JLabel();
        if(isDefault)
            this.setGraphicDefaultMap();
        else
            this.setGraphicCustomMap();
    }

    //Graphics
    private void setGraphicDefaultMap() {
        this.content.setLayout(null);
        this.content.setSize(48, 48);
        this.content.setBackground(new Color(0, 0, 0, 0));

        this.StateButton = new JButton();
        this.StateButton.setBackground(new Color(0,0,0,0));
        this.StateButton.setFocusPainted(false);
        this.StateButton.setContentAreaFilled(false);
        this.StateButton.setBorderPainted(false);
        this.StateButton.setSize(48,48);
        this.StateButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                StateButton.setIcon(Selected);
            }

            public void mouseExited(MouseEvent e) {
                if (!selected)
                    StateButton.setIcon(Idle);
                else StateButton.setIcon(Selected);
            }
        });
        this.StateButton.addActionListener(clicked());
        this.SoldiersCountLabel = new JLabel();
        this.SoldiersCountLabel.setForeground(Color.BLACK);
        this.updateState();
        this.StateButton.add(SoldiersCountLabel);
        this.content.add(StateButton);
    }
    public void setGraphicCustomMap() {
        this.content.setLayout(null);
        this.content.setSize(125,125);
        this.content.setBackground(new Color(0,0,0,0));

        ImageIcon backGround = StateButtons.Land(backGroundID);
        JLabel label = new JLabel(backGround);
        label.setSize(125,125);

        this.StateButton = new JButton(Idle);
        this.StateButton.setFocusPainted(false);
        this.StateButton.setContentAreaFilled(false);
        this.StateButton.setBorderPainted(false);
        this.StateButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                StateButton.setIcon(Selected);
            }

            public void mouseExited(MouseEvent e) {
                if (!selected)
                    StateButton.setIcon(Idle);
                else StateButton.setIcon(Selected);
            }
        });
        this.StateButton.addActionListener(clicked());

        this.SoldiersCountLabel = new JLabel();
        this.SoldiersCountLabel.setForeground(Color.BLACK);
        this.StateButton.add(SoldiersCountLabel);
        this.updateState();
        this.StateButton.setLocation(125/2 - 48/2,125/2 - 48/2);
        this.StateButton.setSize(48,48);

        if(!this.isSea) {
            this.content.add(StateButton);
            this.content.add(label);
        }
    }
    public void clickedSound()
    {
        if(gameManager.action == ManagerAction.INTRO || gameManager.action == ManagerAction.REINFORCE)
        {
            if(hasNameVoice)
                Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\Territories\\" + getName() + ".mp3");
            else Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\4.wav");
        }
        else if(gameManager.action == ManagerAction.ATTACK)
        {
            if(getSoldiersCount() >= 2)
            {
                if(hasNameVoice)
                    Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\Territories\\" + getName() + ".mp3");
                else Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\4.wav");
            }
            else
                {
                if(hasNameVoice)
                    Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\HasOnlyOneUnit" + CustomMethods.random(1,2) + ".mp3");
                else Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\11.wav");
            }
        }
        else if(gameManager.action == ManagerAction.FORTIFY)
        {
            if(gameManager.getOrigin() == null)
            {
                if(hasNameVoice)
                    Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\HasOnlyOneUnit" + CustomMethods.random(1,2) + ".mp3");
                else Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\11.wav");
            }
            else if(gameManager.getOrigin() != null && gameManager.getDestination() == null)
            {
                if(hasNameVoice)
                    Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\Territories\\" + getName() + ".mp3");
                else Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\4.wav");
            }
        }
    }
    private ActionListener clicked() {
        State instance = this;
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //INTRO & REINFORCE =================
                if(gameManager.action == ManagerAction.INTRO || gameManager.action == ManagerAction.REINFORCE) {
                    if(gameManager.currentPlayer == Owner)
                    {
                        gameManager.setOrigin(instance);
                        clickedSound();
                    }
                }
                //ATTACK ============================
                else if(gameManager.action == ManagerAction.ATTACK) {
                    if(gameManager.currentPlayer == Owner) {
                        if(getSoldiersCount() >= 2)
                            gameManager.setOrigin(instance);
                        clickedSound();
                    }
                    else {
                        if (gameManager.getOrigin() != null) {
                            if(!Owner.getTeam().isExist(gameManager.currentPlayer))
                                gameManager.setDestination(instance);
                            else gameManager.resetAttack();
                        }
                    }
                }
                //FORTIFY ===========================
                else if (gameManager.action == ManagerAction.FORTIFY)
                {
                    if (gameManager.getOrigin() == null) {
                        if (getSoldiersCount() >= 2) {
                            if(gameManager.currentPlayer == Owner)
                            {
                                gameManager.resetAttack();
                                gameManager.setOrigin(instance);
                            }
                        }
                        else {
                            //SOUND//
                            if(hasNameVoice)
                                Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\HasOnlyOneUnit" + CustomMethods.random(1,2) + ".mp3");
                            else Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\11.wav");
                            //SOUND//
                        }
                    }
                    else if (gameManager.getOrigin() != null && gameManager.getDestination() == null)
                    {
                        if(Owner.getTeam().isExist(gameManager.currentPlayer))
                        {
                            gameManager.setDestination(instance);
                            //SOUND//
                            if(hasNameVoice)
                                Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\Territories\\" + getName() + ".mp3");
                            else Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\4.wav");
                            //SOUND//
                        }
                    }
                    else
                    {
                        if (getSoldiersCount() >= 2)
                        {
                            if(gameManager.currentPlayer == Owner)
                            {
                                gameManager.resetAttack();
                                gameManager.setOrigin(instance);
                            }
                        }
                    }
                }
            }
        };
    }
    public JComponent getContent() {
        return content;
    }

    public Boolean isNeighbor(State Neighbor) {
        return Neighbors.contains(Neighbor);
    }

    //Getters and Setters
    public void setManager(Manager manager) {
        this.gameManager = manager;
    }

    public void addNeighbors(State[] Neighbors) {

        this.Neighbors.addAll(Arrays.asList(Neighbors));
    }

    public List<State> getNeighbors() {
        return Neighbors;
    }

    public void setOwner(Player owner) {
        this.Owner = owner;
        this.flag = owner.getFlag();
        updateState();
    }

    public Player getOwner() {
        return this.Owner;
    }

    public String getName() {
        return this.Name;
    }
    public void setName(String name) {
        this.Name = name;
    }

    public int getSoldiersCount() {
        return this.Soldiers.size();
    }

    //Sending Troops methods
    public List<Soldier> getSoldiersToAttack() {
        List<Soldier> soldiers = new ArrayList<>();
        int count = (this.getSoldiersCount() > 3) ? 3 : getSoldiersCount() - 1;
        for (int index = 0; index < count; index++) {
            soldiers.add(Soldiers.get(index));
        }
        return soldiers;
    }

    public List<Soldier> getSoldiersToDefend() {
        List<Soldier> soldiers = new ArrayList<>();
        int count = (this.getSoldiersCount() >= 2) ? 2 : 1;
        for (int index = 0; index < count; index++) {
            soldiers.add(Soldiers.get(index));
        }
        return soldiers;
    }

    public void sendTroopsTo(State state, int number) {
        int count = (number > 0) ? number : Soldiers.size() - 1;

        for (int index = 0; index < count; index++) {
            state.addSoldier(Soldiers.get(0));
            this.removeSoldier(Soldiers.get(0));
        }
    }

    public void addSoldier(Soldier soldier) {
        this.Soldiers.add(soldier);
        soldier.setState(this);
        this.updateState();
    }

    public void removeSoldier(Soldier soldier) {
        this.Soldiers.remove(soldier);
        this.updateState();
    }

    private void updateState() {
        if(flag == null) return;
        switch (flag) {
            case RED:
                Idle = StateButtons.RedIdle();
                Selected = StateButtons.RedSelected();
                break;
            case BLUE:
                Idle = StateButtons.BlueIdle();
                Selected = StateButtons.BlueSelected();
                break;
            case GREEN:
                Idle = StateButtons.GreenIdle();
                Selected = StateButtons.GreenSelected();
                break;
            case YELLOW:
                Idle = StateButtons.YellowIdle();
                Selected = StateButtons.YellowSelected();
                break;
        }
        this.StateButton.setIcon(Idle);

        if(gameManager.action != ManagerAction.NOT_STARTED)
        {
            String text = Soldiers.size() + "";
            if (text.length() >= 2)
                this.SoldiersCountLabel.setFont(new Font("Antapani-ExtraBold", Font.PLAIN, 14));
            else this.SoldiersCountLabel.setFont(new Font("Antapani-ExtraBold", Font.PLAIN, 20));
            this.SoldiersCountLabel.setText(text);
        }
    }

    public void setSelected(boolean b) {
        this.selected = b;
        if (!selected)
            this.StateButton.setIcon(Idle);
        else
            this.StateButton.setIcon(Selected);
    }
    //hasPath method (DFS)
    public boolean hasPath(State b) {
        if(!b.getOwner().getTeam().isExist(Owner))
            return false;
        HashSet<State> visited = new HashSet<>();
        return hasPath(this,b,visited);
    }
    private boolean hasPath(State a, State b, HashSet<State> visited) {
        if(visited.contains(a)) return false;
        visited.add(a);
        if(a == b) return true;
        for(State city : a.getNeighbors())
        {
            if(city.getOwner().getTeam().isExist(Owner))
            if(hasPath(city,b,visited)) return true;
        }
        return false;
    }
}
















