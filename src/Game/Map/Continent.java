package Game.Map;

import Game.Player.Player;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Continent extends JComponent implements Serializable {

    public List<State> Countries;
    private int Score;
    public String Name;

    public Continent(String name, int score) {
        setScore(score);
        Name = name;
        Countries = new ArrayList<>();
    }


    public boolean isCapturedBy(Player Owner) {
        if(Countries.size() == 0) return false;
        for(State state : Countries) {
            if(state.getOwner() != Owner)
                return false;
        }
        return true;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void addState(State Country) {
        Countries.add(Country);
    }

    public String getName() {
        return this.Name;
    }
}
