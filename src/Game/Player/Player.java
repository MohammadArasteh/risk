package Game.Player;

import Game.Map.State;
import Game.Objects.Flag;
import UI.Scene.SelectPlayerScene.PlayerGUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Player implements Serializable {

    private final String userName;
    private Team team;
    private int avatar_id;
    private Flag flag;

    private ArrayList<State> states = new ArrayList<>();
    private ArrayList<Soldier> readySoldiers = new ArrayList<>();
    private Stack<Soldier> newSoldiers = new Stack<>();

    public Player(String userName, int avatar_id) {
        this.userName = userName;
        this.avatar_id = avatar_id;
    }
    public void init() {

        for(Soldier soldier : readySoldiers)
            soldier.init();
        for(Soldier soldier : newSoldiers)
            soldier.init();
    }
    //======================================================== SOLDIER ========================================================//
    public void init_soldiers(int number) {
        for(int index = 0; index < number; index++)
            this.newSoldiers.push(new Soldier(this));
    }
    public void putSoldier(int count) {
        for(int index = 0; index < count; index++) {
            Soldier soldier = new Soldier(this);
            this.newSoldiers.push(soldier);
        }
    }
    public void removeSoldier(Soldier soldier) {
        this.readySoldiers.remove(soldier);
    }
    //get a soldier from newSoldiers and set a state to it , then put it in readySoldiers List
    public void addReadySoldier(State state) {
        if(hasFreeSoldier()) {
            Soldier soldier = this.newSoldiers.pop();
            state.addSoldier(soldier);
            this.readySoldiers.add(soldier);
        }
    }
    //================================================
    public boolean hasFreeSoldier() {
        return this.newSoldiers.size() != 0;
    }

    //======================================================== SOLDIER ========================================================//
    //======================================================== STATE ==========================================================//
    public void addState(State... states) {
        for (State state : states) {
            this.states.add(state);
            state.setOwner(this);
        }
    }
    public void removeState(State state) {
        if(this.states.contains(state))
            this.states.remove(state);
    }
    public int statesSize() {
        return this.states.size();
    }
    //======================================================== STATE ==========================================================//
    public String getUserName() {
        return this.userName;
    }
    public int getAvatarID() {
        return this.avatar_id;
    }
    public PlayerGUI getGUI(){
        PlayerGUI gui = new PlayerGUI(avatar_id);
        gui.setUsername(userName);
        return gui;
    }

    public Flag getFlag() {
        return this.flag;
    }
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public int getFreeSoldiersCount() {
        return this.newSoldiers.size();
    }
    public boolean isDead() {
        return this.states.size() == 0;
    }
    //Team
    public void setTeam(Team team)
    {
        this.team = team;
    }
    public Team getTeam()
    {
        return this.team;
    }
}
