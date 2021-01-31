package Game.Map.CustomMap;

import Game.Map.Continent;
import Game.Map.Map;
import Game.Map.State;

import java.util.ArrayList;
import java.util.Arrays;

public class Logic extends Map {

    public State[][] StatesID;

    public Logic(int[][] ID, int width, int height) {
        setStates(ID, width, height);
        setContinents();
    }

    public void setStates(int[][] ID, int width, int height) {
        StatesID = new State[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                if (ID[i][j] == 1) StatesID[i][j] = new State("USA", 1);
                else if (ID[i][j] == 2) StatesID[i][j] = new State("EUROPE",2);
                else if (ID[i][j] == 3) StatesID[i][j] = new State("ASIA",3);
                else if (ID[i][j] == 4) StatesID[i][j] = new State("AFRICA",4);
                else {
                    StatesID[i][j] = new State("SEA",0);
                }
            }

        StatesID[0][0].addNeighbors(new State[]{
                StatesID[0][1],
                StatesID[1][0]
        });

        StatesID[0][width - 1].addNeighbors(new State[]{
                StatesID[1][width - 1],
                StatesID[0][width - 2]
        });

        StatesID[height - 1][0].addNeighbors(new State[]{
                StatesID[height - 1][1],
                StatesID[height - 2][0]
        });

        StatesID[height - 1][width - 1].addNeighbors(new State[]{
                StatesID[height - 2][width - 1],
                StatesID[height - 1][width - 2]
        });

        for (int i = 1; i < height - 1; i++) {
            StatesID[i][0].addNeighbors(new State[]{
                    StatesID[i][1],
                    StatesID[i - 1][0],
                    StatesID[i + 1][0]
            });
        }

        for (int i = 1; i < height - 1; i++) {
            StatesID[i][width - 1].addNeighbors(new State[]{
                    StatesID[i][width - 2],
                    StatesID[i - 1][width - 1],
                    StatesID[i + 1][width - 1]
            });
        }

        for (int j = 1; j < width - 1; j++) {
            StatesID[0][j].addNeighbors(new State[]{
                    StatesID[1][j],
                    StatesID[0][j + 1],
                    StatesID[0][j - 1]
            });
        }

        for (int j = 1; j < width - 1; j++) {
            StatesID[height - 1][j].addNeighbors(new State[]{
                    StatesID[height - 2][j],
                    StatesID[height - 1][j + 1],
                    StatesID[height - 1][j - 1]
            });
        }

        for (int i = 1; i < height - 1; i++)
            for (int j = 1; j < width - 1; j++) {
                StatesID[i][j].addNeighbors(new State[]{
                        StatesID[i + 1][j],
                        StatesID[i - 1][j],
                        StatesID[i][j + 1],
                        StatesID[i][j - 1]
                });
            }

        States = new ArrayList<>();
        for (State[] state : StatesID)
            States.addAll(Arrays.asList(state));

    }

    public void setContinents() {
        Continents = new ArrayList<>();

        Continents.add(new Continent("USA", 3));
        Continents.add(new Continent("EUROPE", 4));
        Continents.add(new Continent("ASIA", 4));
        Continents.add(new Continent("AFRICA", 2));

        for (State state: States) {

            switch (state.getName()) {
                case "USA":
                    Continents.get(0).addState(state);
                    break;
                case "EUROPE":
                    Continents.get(1).addState(state);
                    break;
                case "ASIA":
                    Continents.get(2).addState(state);
                    break;
                case "AFRICA":
                    Continents.get(3).addState(state);
                    break;
            }
        }
    }
}
