package Game.Map.CustomMap;

import Game.Player.Player;
import HelpingClasses.Cursor;
import Resource.Images.Avatars.Avatars;
import UI.Effects.Sounds;
import UI.Scene.GameEntryScene;
import UI.Scene.SelectMapScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static Main.Main.frame;
import static UI.Effects.Sounds.ButtonsSound;
import static UI.Effects.Sounds.playSound;

public class Design extends JPanel {

    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;

    public JComboBox<Integer> WidthBox;
    public JComboBox<Integer> HeightBox;

    private JButton Undo;
    private ImageIcon undoIdle, undoFocus;

    private JButton back;
    private ImageIcon backIconIdle, backIconFocus;

    public JPanel buttonsPanel;
    public JButton[][] Table;
    public JLabel width;
    public JLabel height;

    public Stack<Integer> Actions;
    public Stack<Integer> savedIndexI;
    public Stack<Integer> savedIndexJ;
    public Stack<Integer> savedIcons;
    public Stack<Integer> savedWidth;
    public Stack<Integer> savedHeight;

    public SelectMapScene LastScene;

    public int[][] icon;
    public JButton[] Items;

    private int StateCounter = 0;
    private int SeaCounter = 0;

    private boolean isCreated = false;
    private boolean isPut = false;
    private boolean[] isFirstTime;
    private boolean[][] isNull;

    private JButton Submit;
    private ImageIcon submitIdle, submitFocus;

    private List<Player> players;

    //Style
    private JLabel backGround,tableBackground;
    private ImageIcon backGroundIcon, tableBackgroundIcon;

    public Design(SelectMapScene LastScene, List<Player> players) {
        this.players = players;
        this.LastScene = LastScene;

        setLayout(null);
        setSize(frame.getBounds().width, frame.getBounds().height);

        backGroundIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Maps\\customMapSceneBackground.jpg")));
        backGround = new JLabel(backGroundIcon);
        backGround.setSize(frame.getWidth(),frame.getHeight());

        tableBackgroundIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Maps\\tableBackground.png")));
        tableBackground = new JLabel(tableBackgroundIcon);
        tableBackground.setSize(1200,798);
        tableBackground.setLocation(getWidth()/2 - tableBackground.getWidth()/2,getHeight()/2 - tableBackground.getHeight()/2 + 55);


        Actions = new Stack<>();
        savedWidth = new Stack<>();
        savedHeight = new Stack<>();
        savedIndexI = new Stack<>();
        savedIndexJ = new Stack<>();
        savedIcons = new Stack<>();

        submitIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SubmitIdle.png")));
        submitFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\SubmitFocus.png")));
        Submit = new JButton(submitFocus);
        Avatars.changeButtonSize(Submit,300,112);
        Submit.setIcon(submitIdle);
        Avatars.changeButtonSize(Submit,300,112);
        Submit.setBorderPainted(false);
        Submit.setFocusPainted(false);
        Submit.setContentAreaFilled(false);
        Submit.setIcon(submitIdle);
        Submit.setSize(300, 112);
        Submit.setLocation(frame.getWidth() - Submit.getWidth() - 20, frame.getHeight() - Submit.getHeight() - 20);


        backIconIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\BackIdle.png")));
        backIconFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\BackFocus.png")));
        back = new JButton(backIconIdle);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setLocation(20, 20);
        back.setIcon(backIconIdle);
        back.setSize(122, 122);

        add(back);
        add(Submit);


        setDimensions();
        setUndo();
        setItems();
        setDragAndDrop();

        setListeners();
        Cursor.Idle(frame);

        isFirstTime = new boolean[5];
        for (int i = 0; i < 5; i++) isFirstTime[i] = true;

    }

    public void setDimensions() {

        WidthBox = new JComboBox<>();
        WidthBox.setToolTipText("Width: ");
        for (int index = 6; index <= 9; index++) WidthBox.addItem(index);
        WidthBox.setMaximumRowCount(4);
        WidthBox.setSelectedIndex(0);
        WidthBox.setFont(new Font("Antapani-ExtraBold",Font.TRUETYPE_FONT,50));
        WidthBox.setBackground(Color.decode("#8c330c"));
        WidthBox.setForeground(Color.decode("#e6ba8d"));
        WidthBox.setLocation(1383, 94);
        WidthBox.setSize(150, 75);

        HeightBox = new JComboBox<>();
        for (int index = 4; index <= 6; index++) HeightBox.addItem(index);
        HeightBox.setMaximumRowCount(3);
        HeightBox.setSelectedIndex(2);
        HeightBox.setFont(new Font("Antapani-ExtraBold",Font.TRUETYPE_FONT,50));
        HeightBox.setBackground(Color.decode("#8c330c"));
        HeightBox.setForeground(Color.decode("#e6ba8d"));
        HeightBox.setLocation(1538, 94);
        HeightBox.setSize(150, 75);

        add(WidthBox);
        add(HeightBox);

        icon = new int[11][11];

        isNull = new boolean[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                isNull[i][j] = true;
    }

    private void setItems() {

        Items = new JButton[10];

        Items[0] = new JButton();
        Items[0].setSize( 125, 125);
        Items[0].setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Maps\\0.png"))));
        Items[0].setFocusPainted(false);
        Items[0].setBorderPainted(false);
        Items[0].setContentAreaFilled(false);
        Items[0].setLocation(getWidth()/2 - (2 - 0)*140 - 125/2,20);

        Items[5] = new JButton();
        Items[5].setSize( 125, 125);
        Items[5].setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Maps\\0.png"))));
        Items[5].setFocusPainted(false);
        Items[5].setBorderPainted(false);
        Items[5].setContentAreaFilled(false);
        Items[5].setEnabled(false);
        Items[5].setVisible(false);
        Items[5].setLocation(getWidth()/2 - (2 - 0)*140 - 125/2,20);

        add(Items[0]);
        add(Items[5]);

        for (int index = 1; index < 10; index++) {
            if (index != 5) {
                Items[index] = new JButton();

                ImageIcon icon = new ImageIcon("src\\Resource\\Images\\Maps\\" + (index % 5) + ".png");
                Image image = icon.getImage().getScaledInstance(125, 125, Image.SCALE_DEFAULT);
                icon.setImage(image);
                Items[index].setIcon(icon);
                Items[index].setFocusPainted(false);
                Items[index].setContentAreaFilled(false);
                Items[index].setBorderPainted(false);
                Items[index].setSize(125,125);
                Items[index].setLocation(getWidth()/2 - (2 - (index % 5))*140 - 125/2,20);
                if (index > 5) {
                    Items[index].setEnabled(false);
                    Items[index].setVisible(false);
                }
                add(Items[index]);
            }
        }
        setTable();
    }

    public void setTable() {

        if (isCreated)
            for (JButton[] RowButtons : Table)
                for (JButton button : RowButtons) {
                    remove(button);
                    frame.getContentPane().remove(button);
                }

        int width = (int) WidthBox.getSelectedItem();
        int height = (int) HeightBox.getSelectedItem();
        Table = new JButton[height][width];
        isCreated = true;

        //GridLayout Required
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                Table[i][j] = new JButton();
                Table[i][j].setIcon(new ImageIcon(Objects.requireNonNull(
                        this.getClass().getClassLoader().getResource("Resource\\Images\\Maps\\Tile.png"))));
                Table[i][j].setContentAreaFilled(false);
                Table[i][j].setFocusPainted(false);
                Table[i][j].setBorderPainted(false);
                Table[i][j].setBounds(getWidth()/2 -  (width/2 - j)*125, getHeight()/2 -  (height/2 - i)*125 + 125/2, 125, 125);
                add(Table[i][j]);
                remove(backGround);
                add(backGround);
                repaint();
            }
        Undo.setLocation(getWidth()/2 -  ((width)/2 + 1)*125, getHeight()/2 -  ((height)/2)*125 + 62);

        if (!savedIndexI.empty())
            for (int index = 0; index < savedIndexI.size(); index++) {

                if (savedIndexI.get(index) < (int) HeightBox.getSelectedItem() &&
                    savedIndexJ.get(index) < (int) WidthBox.getSelectedItem()) {

                    Table[savedIndexI.get(index)][savedIndexJ.get(index)].setIcon(new ImageIcon(
                            "src\\Resource\\Images\\Maps\\" + savedIcons.get(index) + ".png"));
                }
            }
    }

    public void setDragAndDrop() {

        for (int i = 0; i < 5; i++) {

            int finalI = i;
            Items[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    Cursor.Got(frame);
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    myX = Items[finalI].getX();
                    myY = Items[finalI].getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Cursor.Get(frame);
                    Sounds.playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
                    for (int j = 0; j < (int) HeightBox.getSelectedItem(); j++)
                        for (int k = 0; k < (int) WidthBox.getSelectedItem(); k++) {
                            if (Items[finalI].getX() + 50 > Table[j][k].getX()
                                    &&
                                    Items[finalI].getX() + 50 < Table[j][k].getX() + Table[j][k].getWidth()
                                    &&
                                    Items[finalI].getY() + 50 > Table[j][k].getY()
                                    &&
                                    Items[finalI].getY() + 50 < Table[j][k].getY() + Table[j][k].getHeight()
                                    &&
                                    isNull[j][k]) {

                                isNull[j][k] = false;
                                Items[finalI].setLocation(getWidth()/2 - (2 - (finalI % 5))*140 - 125/2,20);
                                Items[finalI + 5].setVisible(false);
                                if (isFirstTime[finalI]) {
                                    Table[j][k].setIcon(new ImageIcon("src\\Resource\\Images\\Maps\\" + finalI + ".png"));

                                    Undo.setEnabled(true);
                                    isPut = true;
                                    icon[j][k] = finalI;

                                    if (finalI > 0) {
                                        StateCounter++;
                                        isFirstTime[finalI] = false;
                                    } else SeaCounter++;

                                    savedIndexI.push(j);
                                    savedIndexJ.push(k);
                                    savedIcons.push(finalI);
                                    Actions.push(3);
                                } else {
                                    try {
                                        if (icon[j][k + 1] == finalI ||
                                                icon[j + 1][k] == finalI ||
                                                icon[j - 1][k] == finalI ||
                                                icon[j][k - 1] == finalI) {
                                            Table[j][k].setIcon(new ImageIcon(
                                                    "src\\Resource\\Images\\Maps\\" + finalI + ".png"));

                                            isNull[j][k] = false;
                                            Undo.setEnabled(true);
                                            isPut = true;
                                            isFirstTime[finalI] = false;
                                            icon[j][k] = finalI;
                                            if (finalI > 0) StateCounter++;
                                            else SeaCounter++;

                                            savedIndexI.push(j);
                                            savedIndexJ.push(k);
                                            savedIcons.push(finalI);
                                            Actions.push(3);
                                        } else {
                                            isNull[j][k] = true;
                                            playSound("src\\Resource\\Sounds\\SFX\\new\\12.wav");
                                            JOptionPane.showMessageDialog(frame,
                                                    "States of a Continent MUST Be Connected by one of Four Sides!",
                                                    "NOTICE!", JOptionPane.WARNING_MESSAGE);
                                        }
                                    } catch (ArrayIndexOutOfBoundsException Error) {
                                        try {
                                            if (icon[j][k - 1] == finalI) {
                                                Table[j][k].setIcon(new ImageIcon(
                                                        "src\\Resource\\Images\\Maps\\" + finalI + ".png"));

                                                isNull[j][k] = false;
                                                Undo.setEnabled(true);
                                                isPut = true;
                                                isFirstTime[finalI] = false;
                                                icon[j][k] = finalI;
                                                if (finalI > 0) StateCounter++;
                                                else SeaCounter++;

                                                savedIndexI.push(j);
                                                savedIndexJ.push(k);
                                                savedIcons.push(finalI);
                                                Actions.push(3);
                                            } else {
                                                isNull[j][k] = true;
                                                playSound("src\\Resource\\Sounds\\SFX\\new\\12.wav");
                                                JOptionPane.showMessageDialog(frame,
                                                        "States of a Continent MUST Be Connected by one of Four Sides!",
                                                        "NOTICE!", JOptionPane.WARNING_MESSAGE);
                                            }
                                        } catch (ArrayIndexOutOfBoundsException E) {
                                            isNull[j][k] = true;
                                            playSound("src\\Resource\\Sounds\\SFX\\new\\12.wav");
                                            JOptionPane.showMessageDialog(frame,
                                                    "States of a Continent MUST Be Connected by one of Four Sides!",
                                                    "NOTICE!", JOptionPane.WARNING_MESSAGE);
                                        }
                                    }
                                }
                            }
                        }
                    if (!isPut) {
                        Items[finalI].setLocation(getWidth()/2 - (2 - (finalI % 5))*140 - 125/2,20);
                        Items[finalI + 5].setVisible(false);
                    } else isPut = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Cursor.Get(frame);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Cursor.Idle(frame);
                }
            });

            Items[finalI].addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    Items[finalI].setLocation(myX + deltaX, myY + deltaY);
                    Items[finalI + 5].setVisible(true);
                }

                @Override
                public void mouseMoved(MouseEvent e) { }

            });

        }
    }

    public void setUndo() {
        undoIdle = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\UndoIdle.png")));
        undoFocus = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Resource\\Images\\Buttons\\UndoFocus.png")));
        Undo = new JButton(undoFocus);
        Avatars.changeButtonSize(Undo,120,74);
        Undo.setIcon(undoIdle);
        Avatars.changeButtonSize(Undo,120,74);
        Undo.setSize(120,74);
        Undo.setEnabled(false);
        Undo.setFocusPainted(false);
        Undo.setContentAreaFilled(false);
        Undo.setBorderPainted(false);

        add(Undo);
    }

    public void Paint() {

        if (savedWidth.empty()) WidthBox.setSelectedItem(6);
        else WidthBox.setSelectedItem(savedWidth.peek());

        if (savedHeight.empty()) HeightBox.setSelectedItem(6);
        else HeightBox.setSelectedItem(savedHeight.peek());

    }

    public void setListeners() {

        WidthBox.addItemListener(Event -> {
            savedWidth.push((Integer) WidthBox.getSelectedItem());
            Actions.push(1);

            if (Actions.empty()) Undo.setEnabled(false);
            else Undo.setEnabled(true);

            setTable();
        });
        HeightBox.addItemListener(Event -> {
            savedHeight.add((Integer) HeightBox.getSelectedItem());
            Actions.push(2);

            if (Actions.empty()) Undo.setEnabled(false);
            else Undo.setEnabled(true);

            setTable();
        });

        Undo.addActionListener(Event -> {

            switch (Actions.peek()) {
                case 1:
                    for (int i = 0; i < 2; i++) {
                        savedWidth.pop();
                        Actions.pop();
                    }
                    break;
                case 2:
                    for (int i = 0; i < 2; i++) {
                        savedHeight.pop();
                        Actions.pop();
                    }
                    break;
                default:
                    if (savedIcons.peek() > 0) StateCounter--;
                    else SeaCounter--;
                    Table[savedIndexI.peek()][savedIndexJ.peek()].setIcon(
                            new ImageIcon(Objects.requireNonNull(
                                    this.getClass().getClassLoader().getResource("Resource\\Images\\Maps\\Tile.png"))));
                    isNull[savedIndexI.peek()][savedIndexJ.peek()] = true;
                    icon[savedIndexI.peek()][savedIndexJ.peek()] = -1;
                    savedIndexI.pop();
                    savedIndexJ.pop();
                    savedIcons.pop();
                    Actions.pop();
                    for (int i = 0; i < 5; i++)
                        if (savedIcons.search(i) == -1) isFirstTime[i] = true;
            }
            if (Actions.empty()) Undo.setEnabled(false);
            Paint();
        });
        Undo.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) {
                if (Undo.isEnabled()) ButtonsSound("play");
            }
            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                if (Undo.isEnabled())
                    playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                Undo.setIcon(undoFocus);
            }
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                Undo.setIcon(undoIdle);
            }
        });

        Submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.add(LastScene);
                frame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                Submit.setIcon(submitFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                Submit.setIcon(submitIdle);
            }
        });
        Submit.addActionListener(e -> {
            if (StateCounter + SeaCounter >= (int) WidthBox.getSelectedItem() * (int) HeightBox.getSelectedItem()
                    && !isFirstTime[1] && !isFirstTime[2] && !isFirstTime[3] && !isFirstTime[4]) {

                    for (int i = 0; i < (int) HeightBox.getSelectedItem(); i++) {
                        for (int j = 0; j < (int) WidthBox.getSelectedItem(); j++) {
                            icon[savedIndexI.pop()][savedIndexJ.pop()] = savedIcons.pop();
                        }
                    }
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    CustomMapGUI gui = new CustomMapGUI(icon, (int) WidthBox.getSelectedItem(), (int) HeightBox.getSelectedItem());
                    GameEntryScene scene = new GameEntryScene(gui, players);
                    frame.add(scene);
                    frame.setVisible(true);

            } else {
                playSound("src\\Resource\\Sounds\\SFX\\new\\12.wav");
                JOptionPane.showMessageDialog(frame,
                        "The Map is not Created Compeletely!",
                        "WARNING", JOptionPane.ERROR_MESSAGE);
            }
        });

        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Actions.empty()) {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    frame.add(LastScene);
                    frame.setVisible(true);
                } else if (JOptionPane.showConfirmDialog(null, "Changes Will Be Lost!\nExit Anyway?",
                        "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    frame.add(LastScene);
                    frame.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ButtonsSound("play");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(frame);
                playSound("src\\Resource\\Sounds\\SFX\\ButtonHover.wav");
                back.setIcon(backIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(frame);
                back.setIcon(backIconIdle);
            }
        });
    }
}
