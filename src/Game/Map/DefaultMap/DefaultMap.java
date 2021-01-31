package Game.Map.DefaultMap;

import Game.Map.Continent;
import Game.Map.Map;
import Game.Map.State;

import java.util.ArrayList;

public class DefaultMap extends Map {

    public DefaultMap(boolean statesVoice) {
        this.setStates(statesVoice);
        this.setContinents();
    }

    public void setStates(boolean stateHasNameVoice) {

        this.States = new ArrayList<>();

        //Defining States
        this.States.add(new State("ALASKA",stateHasNameVoice)); //0
        this.States.add(new State("ALBERTA",stateHasNameVoice)); //1
        this.States.add(new State("NORTH WEST TERRITORY",stateHasNameVoice)); //2
        this.States.add(new State("ONTARIO",stateHasNameVoice)); //3
        this.States.add(new State("WESTERN UNITED STATES",stateHasNameVoice)); //4
        this.States.add(new State("EASTERN UNITED STATES",stateHasNameVoice)); //5
        this.States.add(new State("QUEBEC",stateHasNameVoice)); //6
        this.States.add(new State("CENTRAL AMERICA",stateHasNameVoice)); //7
        this.States.add(new State("GREENLAND",stateHasNameVoice)); //8
        this.States.add(new State("VENEZUELA",stateHasNameVoice)); //9
        this.States.add(new State("BRAZIL",stateHasNameVoice)); //10
        this.States.add(new State("PERU",stateHasNameVoice)); //11
        this.States.add(new State("ARGENTINA",stateHasNameVoice)); //12
        this.States.add(new State("ICELAND",stateHasNameVoice)); //13
        this.States.add(new State("GREAT BRITAIN",stateHasNameVoice)); //14
        this.States.add(new State("SCANDINAVIA",stateHasNameVoice)); //15
        this.States.add(new State("NORTHERN EUROPE",stateHasNameVoice)); //16
        this.States.add(new State("WESTERN EUROPE",stateHasNameVoice)); //17
        this.States.add(new State("SOUTHERN EUROPE",stateHasNameVoice)); //18
        this.States.add(new State("UKRAINE",stateHasNameVoice)); //19
        this.States.add(new State("MIDDLE EAST",stateHasNameVoice)); //20
        this.States.add(new State("AFGHANISTAN",stateHasNameVoice)); //21
        this.States.add(new State("INDIA",stateHasNameVoice)); //22
        this.States.add(new State("URAL",stateHasNameVoice)); //23
        this.States.add(new State("SLAM",stateHasNameVoice)); //24
        this.States.add(new State("CHINA",stateHasNameVoice)); //25
        this.States.add(new State("SIBERIA",stateHasNameVoice)); //26
        this.States.add(new State("MONGOLIA",stateHasNameVoice)); //27
        this.States.add(new State("IRKUTSK",stateHasNameVoice)); //28
        this.States.add(new State("YAKUTSK",stateHasNameVoice)); //29
        this.States.add(new State("KAMCHATKA",stateHasNameVoice)); //30
        this.States.add(new State("JAPAN",stateHasNameVoice)); //31
        this.States.add(new State("NORTH AFRICA",stateHasNameVoice)); //32
        this.States.add(new State("EGYPT",stateHasNameVoice)); //33
        this.States.add(new State("EAST AFRICA",stateHasNameVoice)); //34
        this.States.add(new State("CONGO",stateHasNameVoice)); //35
        this.States.add(new State("SOUTH AFRICA",stateHasNameVoice)); //36
        this.States.add(new State("MADAGASCAR",stateHasNameVoice)); //37
        this.States.add(new State("INDONESIA",stateHasNameVoice)); //38
        this.States.add(new State("NEW GUINEA",stateHasNameVoice)); //39
        this.States.add(new State("WESTERN AUSTRALIA",stateHasNameVoice)); //40
        this.States.add(new State("EASTERN AUSTRALIA",stateHasNameVoice)); //41

        //Adding neighbors to each state
        this.States.get(0).addNeighbors(new State[]{States.get(1), States.get(2), States.get(30)});
        this.States.get(1).addNeighbors(new State[]{States.get(0), States.get(2), States.get(3), States.get(4)});
        this.States.get(2).addNeighbors(new State[]{States.get(0), States.get(1), States.get(3), States.get(8)});
        this.States.get(3).addNeighbors(new State[]{States.get(1), States.get(2), States.get(8), States.get(4), States.get(5), States.get(6)});
        this.States.get(4).addNeighbors(new State[]{States.get(1), States.get(3), States.get(5), States.get(7)});
        this.States.get(5).addNeighbors(new State[]{States.get(3), States.get(4), States.get(6), States.get(7)});
        this.States.get(6).addNeighbors(new State[]{States.get(3), States.get(5), States.get(8)});
        this.States.get(7).addNeighbors(new State[]{States.get(4), States.get(5), States.get(9)});
        this.States.get(8).addNeighbors(new State[]{States.get(2), States.get(3), States.get(6), States.get(13)});
        this.States.get(9).addNeighbors(new State[]{States.get(7), States.get(10), States.get(11)});
        this.States.get(10).addNeighbors(new State[]{States.get(9), States.get(11), States.get(12), States.get(32)});
        this.States.get(11).addNeighbors(new State[]{States.get(9), States.get(10), States.get(12)});
        this.States.get(12).addNeighbors(new State[]{States.get(10), States.get(11)});
        this.States.get(13).addNeighbors(new State[]{States.get(8), States.get(14), States.get(15)});
        this.States.get(14).addNeighbors(new State[]{States.get(13), States.get(15), States.get(16), States.get(17)});
        this.States.get(15).addNeighbors(new State[]{States.get(13), States.get(14), States.get(16), States.get(19)});
        this.States.get(16).addNeighbors(new State[]{States.get(13), States.get(14), States.get(15), States.get(18), States.get(19) ,States.get(17)});
        this.States.get(17).addNeighbors(new State[]{States.get(14), States.get(16), States.get(18), States.get(32)});
        this.States.get(18).addNeighbors(new State[]{States.get(16), States.get(17), States.get(19), States.get(20), States.get(32), States.get(33)});
        this.States.get(19).addNeighbors(new State[]{States.get(15), States.get(16), States.get(18), States.get(20), States.get(21), States.get(23)});
        this.States.get(20).addNeighbors(new State[]{States.get(18), States.get(19), States.get(21), States.get(22), States.get(33), States.get(34)});
        this.States.get(21).addNeighbors(new State[]{States.get(19), States.get(20), States.get(22), States.get(23), States.get(25)});
        this.States.get(22).addNeighbors(new State[]{States.get(20), States.get(21), States.get(24), States.get(25)});
        this.States.get(23).addNeighbors(new State[]{States.get(19), States.get(21), States.get(25), States.get(26)});
        this.States.get(24).addNeighbors(new State[]{States.get(22), States.get(25), States.get(38)});
        this.States.get(25).addNeighbors(new State[]{States.get(21), States.get(22), States.get(23), States.get(24), States.get(26), States.get(27)});
        this.States.get(26).addNeighbors(new State[]{States.get(23), States.get(25), States.get(27), States.get(28), States.get(29)});
        this.States.get(27).addNeighbors(new State[]{States.get(25), States.get(26), States.get(28), States.get(30), States.get(31)});
        this.States.get(28).addNeighbors(new State[]{States.get(26), States.get(27), States.get(29), States.get(30)});
        this.States.get(29).addNeighbors(new State[]{States.get(26), States.get(28), States.get(30)});
        this.States.get(30).addNeighbors(new State[]{States.get(0), States.get(27), States.get(28), States.get(29), States.get(31)});
        this.States.get(31).addNeighbors(new State[]{States.get(27), States.get(30)});
        this.States.get(32).addNeighbors(new State[]{States.get(10), States.get(17), States.get(18), States.get(33), States.get(34), States.get(35)});
        this.States.get(33).addNeighbors(new State[]{States.get(18), States.get(20), States.get(32), States.get(34)});
        this.States.get(34).addNeighbors(new State[]{States.get(20), States.get(32), States.get(33), States.get(35), States.get(36), States.get(37)});
        this.States.get(35).addNeighbors(new State[]{States.get(32), States.get(34), States.get(36)});
        this.States.get(36).addNeighbors(new State[]{States.get(34), States.get(35), States.get(37)});
        this.States.get(37).addNeighbors(new State[]{States.get(34), States.get(36)});
        this.States.get(38).addNeighbors(new State[]{States.get(24), States.get(39),States.get(40)});
        this.States.get(39).addNeighbors(new State[]{States.get(38), States.get(40), States.get(41)});
        this.States.get(40).addNeighbors(new State[]{States.get(38), States.get(39), States.get(41)});
        this.States.get(41).addNeighbors(new State[]{States.get(39), States.get(40)});

    }

    public void setContinents() {

        this.Continents = new ArrayList<>();

        this.Continents.add(new Continent("North America",5));
        this.Continents.add(new Continent("South America",2));
        this.Continents.add(new Continent("Europe",5));
        this.Continents.add(new Continent("Asia",7));
        this.Continents.add(new Continent("Africa",3));
        this.Continents.add(new Continent("Australia",2));

        for (int i = 0; i < 42; i++) {
            if (i < 9)       this.Continents.get(0).addState(States.get(i));
            else if (i < 13) this.Continents.get(1).addState(States.get(i));
            else if (i < 20) this.Continents.get(2).addState(States.get(i));
            else if (i < 32) this.Continents.get(3).addState(States.get(i));
            else if (i < 38) this.Continents.get(4).addState(States.get(i));
            else             this.Continents.get(5).addState(States.get(i));
        }
    }
}
