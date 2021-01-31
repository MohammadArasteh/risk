package Game.Map.CustomMap;

import Game.Map.State;

import javax.swing.*;
import java.awt.*;

public class CustomMapGUI extends Logic {

    public transient JPanel content;
    public State[][] states;

    private int[][] ID;
    private int width,height;

    public CustomMapGUI(int[][] ID, int width, int height) {
        super(ID, width, height);
        this.ID = ID;
        this.width = width;
        this.height = height;
    }

    public void setContent() {
        content = new JPanel(new GridLayout(height, width));
        content.setSize(125 * width,125 * height);
        content.setBackground(new Color(0,0,0,0));
        content.setLocation(676 + (1200 - content.getWidth())/2,142 + (798 - content.getHeight())/2);
        states = StatesID;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                states[i][j].initGraphic();
                content.add(states[i][j].getContent());
            }
        }
    }
    public void init() {
        setContent();
    }

    public JPanel getContent() {
        return  content;
    }
}
