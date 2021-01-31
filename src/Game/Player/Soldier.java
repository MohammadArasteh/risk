package Game.Player;

import Game.Map.State;
import Game.Objects.Dice;

import java.io.Serializable;

public class Soldier implements Comparable<Soldier>, Serializable {

    private Player owner;
    private State state;

    private transient Dice dice;

    //Constructor
    public Soldier(Player owner) {
        this.setOwner(owner);
        this.init();
    }
    public void init() {
        this.dice = new Dice();
    }

    private void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setState(State state) {
        this.state = state;
    }
    private State getState() {
        return this.state;
    }
    public Dice getDice() {
        return this.dice;
    }

    @Override
    public int compareTo(Soldier o) {
        return Integer.compare(o.getDice().getValue(),this.getDice().getValue());
    }


    public void kill() {
        this.owner.removeSoldier(this);
        this.state.removeSoldier(this);
    }
}
