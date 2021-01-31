package Game.Manager;

import Game.GUI.GameGUI;
import Game.GUI.SelectSoldiers;
import Game.Map.Continent;
import Game.Map.Map;
import Game.Map.State;
import Game.Objects.CustomTimer;
import Game.Objects.Flag;
import Game.Objects.War;
import Game.Player.Player;
import Game.Player.Team;
import HelpingClasses.CustomMethods;
import UI.Effects.Sounds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author : Mohammad Reza Arasteh
 * @version 1.0
 * @since 5/3/2020
 */

public class Manager implements Serializable {

    private List<Player> players;
    public Player currentPlayer;
    private int currentPlayerIndex = 0;
    private int INIT_SOLDIERS;
    private boolean finished = false;

    private Map map;
    private State origin , destination;

    public boolean shuffled = false;
    public ManagerAction action = ManagerAction.NOT_STARTED;
    private Timer updateInitializer;
    //graphic
    private transient GameGUI graphic;
    private CustomTimer timer;
    public int turnPlayed = 0;

    //INTRO : Placing soldiers that given to players at start
    //REINFORCE : Placing soldiers that given based on number of states and continents
    //ATTACK : Attack Other players
    //FORTIFY : Send troops to another state
    public void setOrigin(State state) {
        if(!timer.isAwake)
            timer.start();

        this.resetAttack();
        this.origin = state;


        //INTRO =============================
        if(action == ManagerAction.INTRO)
        {
            this.currentPlayer.addReadySoldier(origin);
            //check if all players placed their init soldiers , then go to the next stage
            if(this.players.stream().filter(p -> p.getFreeSoldiersCount() != 0).toArray().length == 0)
            {
                this.currentPlayerIndex = 0;
                this.currentPlayer = this.players.get(currentPlayerIndex);
                this.nextAction();
            }
            else this.nextPlayer();
        }
        //REINFORCE =========================
        else if(this.action == ManagerAction.REINFORCE)
        {
            this.currentPlayer.addReadySoldier(origin);
            if(this.currentPlayer.getFreeSoldiersCount() == 0)
                this.nextAction();
        }
        //ATTACK ============================
        else if(this.action == ManagerAction.ATTACK)
        {

            this.origin.setSelected(true);
            for(State neighbor : this.origin.getNeighbors())
                neighbor.setSelected(true);
        }
        //FORTIFY ===========================
        else if (this.action == ManagerAction.FORTIFY)
        {
            this.origin.setSelected(true);
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\FORTIFY_SELECT" + CustomMethods.random(1,4) + ".mp3");
        }
    }
    public State getOrigin() {
        return this.origin;
    }
    public void setDestination(State state) {

        //Change old destination to new destination
        if(this.destination != null)
            this.destination.setSelected(false);
        this.destination = state;
        this.destination.setSelected(true);

        //ATTACK ============================
        if(this.action == ManagerAction.ATTACK)
        {
            if(!this.origin.isNeighbor(destination)) {
                this.resetAttack();
                Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\OnlyAttackNeighbor" + CustomMethods.random(1,2) + ".mp3");
                return;
            }
            War war = new War(origin,destination);
            war.setManager(this);
            this.graphic.addWarPanel(war);
            this.resetAttack();
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\WarPanelStart" + CustomMethods.random(1,2) + ".mp3");
        }
        //FORTIFY ===========================
        else if (this.action == ManagerAction.FORTIFY)
        {
            if (this.origin.hasPath(destination))
                new SelectSoldiers(origin, destination);
            this.resetAttack();
        }

    }
    public State getDestination() {
        return this.destination;
    }

    public Manager (Map map , List<Player> players) {
        this.map = map;
        this.map.setGameManager(this);
        this.players = players;

        //set flag for each player
        for(int index = 0; index < players.size(); index++)
            this.players.get(index).setFlag(Flag.values()[index]);

        this.timer = new CustomTimer();

        //Waiting for LoadingScene to finish loading
        new Timer(1000, new ActionListener() {
            int counter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                if(counter >= 10) {
                    startingActions();
                    ((Timer)e.getSource()).stop();
                }
            }
        }).start();
    }
    public void init() {
        Team.init(this.players);
        for(State state : this.map.getStates())
            state.initGraphic();
        for(Player player : this.players)
            player.init();

        //When we load a game from saved games, we need to start Update method caller again, so here we check if
        //game loaded from saves, then start updateInitializer again.
        if(this.action != ManagerAction.SHUFFLING && this.action != ManagerAction.NOT_STARTED)
        {
            this.updateInitializer = new Timer(33,e -> Update());
            this.updateInitializer.start();
        }
    }

    private void startingActions() {
        //set initialize number of soldiers
        switch (players.size()) {
            case 2 : INIT_SOLDIERS = 30;
                break;
            case 3 : INIT_SOLDIERS = 25;
                break;
            default : INIT_SOLDIERS = 20;
        }

        //setup update method
        updateInitializer = new Timer(33 , e -> Update());

        initialize();
        shuffleProcess();
        currentPlayer = players.get(0);

    }

    //Randomly change States color for for 5 seconds
    private void shuffleProcess() {
        Timer timer = new Timer(100, new ActionListener() {
            private int counter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                List<State> leftStates = new ArrayList<>(map.getStates());
                Random randomGenerator = new Random();
                while(leftStates.size() > 0) {
                    int randomIndex = randomGenerator.nextInt(leftStates.size());
                    State state = leftStates.remove(randomIndex);
                    state.setOwner(currentPlayer);
                    nextPlayer();
                }
                counter++;
                if(counter >= 50) {
                    shuffled = true;
                    shuffleStates();
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    //Giving players random state
    private void shuffleStates() {

        this.action = ManagerAction.SHUFFLING;

        List<State> leftStates = new ArrayList<>(this.map.getStates());
        //removing sea blocks from giving states
        leftStates = leftStates.stream().filter(state -> !state.isSea).collect(Collectors.toList());
        Random randomGenerator = new Random();
        this.currentPlayer = this.players.get(0);
        while(leftStates.size() > 0)
        {
            int randomIndex = randomGenerator.nextInt(leftStates.size());
            State state = leftStates.remove(randomIndex);
            state.setOwner(currentPlayer);
            this.currentPlayer.addState(state);
            this.currentPlayer.addReadySoldier(state);
            this.nextPlayer();
        }

        this.action = ManagerAction.INTRO;
        this.graphic.getTimer().start();
        this.currentPlayer = players.get(0);
        this.currentPlayerIndex = 0;
        Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\MapOccupied" + CustomMethods.random(1,3) + ".mp3");
        this.updateInitializer.start();
    }

    //connecting states to game manager and giving starting soldiers to players
    private void initialize() {
        for(State state : this.map.getStates())
            state.setManager(this);

        for(Player player : this.players)
            player.init_soldiers(INIT_SOLDIERS);

    }

    public void nextAction() {
        this.resetAttack();
        if(this.action == ManagerAction.INTRO)
            this.Reinforce();
        else if(this.action == ManagerAction.REINFORCE)
            this.Attack();
        else if (this.action == ManagerAction.ATTACK)
            this.Fortify();
        else if(this.action == ManagerAction.FORTIFY) {
            this.nextPlayer();
            this.Reinforce();
        }
    }
    private void Reinforce() {
        this.action = ManagerAction.REINFORCE;
        this.giveSoldierTo(this.currentPlayer);
        if(this.currentPlayer.getFreeSoldiersCount() == 0)
            this.nextAction();
        else
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\REINFORCE" + CustomMethods.random(1,5) + ".mp3");
    }
    private void Attack() {
        this.action = ManagerAction.ATTACK;
        Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\ATTACK" + CustomMethods.random(1,2) + ".mp3");
    }
    private void Fortify() {
        this.action = ManagerAction.FORTIFY;
        Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\FORTIFY_START" + CustomMethods.random(1,3) + ".mp3");
    }
    //reset origin and destination
    public void resetAttack() {
     if(this.origin != null) {
         this.origin.setSelected(false);
         for(State neighbor : this.origin.getNeighbors())
             neighbor.setSelected(false);
         this.origin = null;
     }
     if(this.destination != null) {
         this.destination.setSelected(false);
         this.destination = null;
     }
    }

    private void giveSoldierTo(Player player) {
        int soldiers = player.statesSize()/3;
        int bonus = 0;
        for(Continent continent : map.getContinents())
            if(continent.isCapturedBy(player))
                bonus += continent.getScore();
        player.putSoldier(soldiers + bonus);
    }

    private void nextPlayer() {
        this.currentPlayerIndex += 1;
        if(this.currentPlayerIndex >= this.players.size())
            this.currentPlayerIndex = 0;
        this.currentPlayer = this.players.get(this.currentPlayerIndex);

        if(this.action == ManagerAction.INTRO && this.currentPlayer.getFreeSoldiersCount() == 0)
            this.nextPlayer();
        if(this.action == ManagerAction.FORTIFY)
        {
            this.turnPlayed += 1;
            this.graphic.updateTurns();
        }
    }

    //Getters
    public int playersSize()
    {
        return this.players.size();
    }
    public int getCurrentPlayerIndex()
    {
        return this.currentPlayerIndex;
    }
    public boolean isFinished()
    {
        return this.finished;
    }
    public CustomTimer getTimer()
    {
        return this.timer;
    }
    //Setters
    public void setGraphic(GameGUI graphic)
    {
        this.graphic = graphic;
    }

    //Update is called once per frame
    private void Update() {
        for(int index = players.size() - 1; index >= 0; index--) {
            if(players.get(index).isDead()) {
                this.graphic.removePlayer(players.get(index));
                Team playerTeam = players.get(index).getTeam();
                playerTeam.removeMember(players.get(index));
                this. players.remove(index);
                if(oneTeamLeft())
                    this.finished = true;
            }
        }
    }
    public void finishGame() {
        this.updateInitializer.stop();
        this.graphic.showWinner(Team.winnerTeam());
    }

    public void stopUpdate()
    {
        this.updateInitializer.stop();
    }
    private boolean oneTeamLeft()
    {
        for(Player player : players)
            for(Player other : players)
                if(player.getTeam() != other.getTeam())
                    return false;
        return true;
    }


}
