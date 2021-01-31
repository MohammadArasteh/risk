package Game.Objects;

import Game.Manager.Manager;
import Game.Map.State;
import Game.Player.Soldier;
import HelpingClasses.CustomMethods;
import UI.Effects.Sounds;
import UI.Effects.Sounds.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : Mohammad Reza Arasteh
 * @version 1.0
 * @since 5/3/2020
 */
public class War {

    private boolean warResult;

    private State attacker, defender;
    private List<Soldier> attackersSoldier , defenderSoldier;
    private List<Dice> attackersDice , defendersDice;

    private boolean hasWinner = false;
    private Manager manager;

    public War(State attacker , State defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.attackersDice = new ArrayList<>();
        this.defendersDice = new ArrayList<>();
    }
    public void start() {
        this.attackersDice.clear();
        this.defendersDice.clear();

        this.attackersSoldier = attacker.getSoldiersToAttack();
        this.defenderSoldier = defender.getSoldiersToDefend();

        for(Soldier soldier : attackersSoldier)
            soldier.getDice().roll();

        for(Soldier soldier : defenderSoldier)
            soldier.getDice().roll();

        Collections.sort(attackersSoldier);
        Collections.sort(defenderSoldier);

        for(Soldier soldier : attackersSoldier)
            this.attackersDice.add(soldier.getDice());
        for(Soldier soldier : defenderSoldier)
            this.defendersDice.add(soldier.getDice());

    }
    public List<Dice> getAttackersDice() {
        return attackersDice;
    }
    public List<Dice> getDefendersDice() {
        return defendersDice;
    }
    public void action() {
        int index = 0;
        int defendersDeaths = 0;
        int attackersDeaths = 0;
        while(index != attackersSoldier.size() && index != defenderSoldier.size())
        {
            if(attackersSoldier.get(index).getDice().getValue() <= defenderSoldier.get(index).getDice().getValue()) {
                this.attackersDice.get(index).lost();
                this.attackersSoldier.get(index).kill();
                attackersDeaths++;
            }
            else {
                this.defendersDice.get(index).lost();
                this.defenderSoldier.get(index).kill();
                defendersDeaths++;
            }
            index++;
        }
        if(defendersDeaths == 2)
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\DoubleKill" + CustomMethods.random(1,2) + ".mp3");
        else if (defendersDeaths == 1 && attackersDeaths == 1)
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\OneEach" + CustomMethods.random(1,3) + ".mp3");
        else if (defendersDeaths == 0)
            Sounds.GuideSound("src\\Resource\\Sounds\\Voice\\LostWar" + CustomMethods.random(1,3) + ".mp3");

        if(defender.getSoldiersCount() == 0)
        {
            this.hasWinner = true;
            this.defender.getOwner().removeState(defender);
            this.attacker.getOwner().addState(defender);
            for(Soldier soldier : attackersSoldier) {
                this.attacker.removeSoldier(soldier);
                soldier.setState(defender);
                this.defender.addSoldier(soldier);
            }
        }

    }
    //canFightAgain method used in WarPanel
    public boolean canFightAgain() {
        return this.defender.getSoldiersCount() >= 1 && attacker.getSoldiersCount() >= 2 && !hasWinner;
    }
    //getStatesSoldiersCounts method used in WarPanel to get each states soldiers to display it on screen.
    public int[] getStatesSoldiersCount() {
        int[] soldiersNumber = new int[2];
        soldiersNumber[0] = attacker.getSoldiersCount();
        soldiersNumber[1] = defender.getSoldiersCount();
        return soldiersNumber;
    }
    public State getAttacker() {
        return this.attacker;
    }
    public State getDefender() {
        return this.defender;
    }

    public Manager getManager() {
        return this.manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
