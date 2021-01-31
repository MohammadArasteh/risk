package Game.Map;

import Game.Manager.Manager;

import java.io.Serializable;
import java.util.List;

public class Map implements Serializable {

    protected Manager gameManager;
    protected List<State> States;
    protected List<Continent> Continents;

    public List<State> getStates() {
        return this.States;
    }
    public void setGameManager(Manager manager)
    {
        this.gameManager = manager;
    }
    public List<Continent> getContinents() {
        return this.Continents;
    }
}
