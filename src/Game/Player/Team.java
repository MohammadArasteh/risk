package Game.Player;

import Game.Objects.Flag;
import Game.Objects.House;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
    public House house;
    private List<Player> members = new ArrayList<>();

    public static Team HouseStark = new Team(House.STARK);
    public static Team HouseBratheon = new Team(House.BARATHEON);
    public static Team HouseMartel = new Team(House.MARTEL);
    public static Team HouseLannister = new Team(House.LANNISTER);

    private Team(House house)
    {
        this.house = house;
    }

    public static void init(List<Player> players)
    {
        for(Player player : players)
        {
            switch (player.getTeam().house)
            {
                case STARK:
                    HouseStark = player.getTeam();
                    break;
                case MARTEL:
                    HouseMartel = player.getTeam();
                    break;
                case LANNISTER:
                    HouseLannister = player.getTeam();
                    break;
                case BARATHEON:
                    HouseBratheon = player.getTeam();
                    break;
            }
        }
    }

    public void addMember(Player player)
    {
        this.members.add(player);
        player.setTeam(this);
    }
    public void removeMember(Player player)
    {
        if(!members.contains(player)) return;
        this.members.remove(player);
    }
    public List<Player> getMembers()
    {
        return this.members;
    }
    public boolean isExist(Player player)
    {
        return this.members.contains(player);
    }
    public static void clearHouse(Team TeamName)
    {
        TeamName.members.clear();
    }
    public static void clearHouses()
    {
        HouseStark.members.clear();
        HouseBratheon.members.clear();
        HouseMartel.members.clear();
        HouseLannister.members.clear();
    }
    public static Team winnerTeam() {
        if(!HouseLannister.members.isEmpty())
            return HouseLannister;
        else if(!HouseStark.members.isEmpty())
            return HouseStark;
        else if(!HouseMartel.members.isEmpty())
            return HouseMartel;
        else if(!HouseBratheon.members.isEmpty())
            return HouseBratheon;
        return null;
    }
}
