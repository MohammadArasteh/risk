package Game.Objects;

import Game.Manager.Manager;
import Game.Map.CustomMap.CustomMapGUI;
import Game.Map.DefaultMap.DefaultMapGUI;
import Game.Player.Player;

import java.io.*;
import java.util.List;

/**
 * @author Mohammad Reza Arasteh
 * @since 5/11/2020
 * @version 1.0
 */
public class SavedInformation implements Serializable {

    private Manager manager;

    private DefaultMapGUI defaultMap;
    private CustomMapGUI customMap;
    public boolean isDefault;

    private List<Player> players;

    private SavedInformation instance = this;
    //Constructors
    private SavedInformation(Manager manager, List<Player> players) {
        this.manager = manager;
        this.players = players;
    }
    public SavedInformation(Manager manager, DefaultMapGUI mapGUI, List<Player> players) {
        this(manager,players);
        this.defaultMap = mapGUI;
        this.isDefault = true;
    }
    public SavedInformation(Manager manager, CustomMapGUI mapGUI, List<Player> players) {
        this(manager,players);
        this.customMap = mapGUI;
        this.isDefault = false;
    }


    public void Write(String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream("saves\\" + fileName + ".txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Read(String fileName) {
        try {
            FileInputStream inputStream = new FileInputStream("saves\\" + fileName + "\\.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            instance = (SavedInformation)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public SavedInformation getInstance() {
        return instance;
    }

    //Getters
    public List<Player> getPlayers() {
        return players;
    }
    public DefaultMapGUI getDefaultMap() {
        return defaultMap;
    }
    public CustomMapGUI getCustomMap() {
        return customMap;
    }
    public Manager getManager() {
        return manager;
    }

}
