package UI.Menu;

import HelpingClasses.Cursor;
import Resource.Fonts.CustomFont;
import UI.Effects.VolumeSlider;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Objects;

import static Main.Main.frame;
import static UI.Effects.Sounds.*;
import static UI.Menu.MainMenu.*;

public class OptionsScene {

    public JDialog OptionsFrame;
    private JTabbedPane Options;

    public JPanel DisplayMode;
    public JPanel AudioOption;

    private JButton Home1;
    private JButton Home2;

    private JLabel AudioSymbol;
    private JLabel ScreenSymbol;

    private JLabel MusicMessage;
    private JLabel BGMusic;
    private JLabel MusicVol;
    private JLabel SFX;

    private JLabel CustomMusic;
    private JButton FileChooser;
    private JFileChooser JFC;

    private ButtonGroup MessageGroup;
    private JRadioButton Message_ON;
    private JRadioButton Message_OFF;

    private ButtonGroup MusicGroup;
    private JRadioButton ON;
    private JRadioButton OFF;

    private ButtonGroup SFXgroup;
    private JRadioButton SFX_ON;
    private JRadioButton SFX_OFF;

    private JSlider slider;
    public static String customSinger;

    public static boolean isCustom = false;

    public OptionsScene(boolean isMenu) {

        ImageIcon homeIdle = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Options\\closeIdle.png")));
        ImageIcon homeFocus = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("Resource\\Images\\Options\\closeFocus.png")));

        Home1 = new JButton(homeIdle);
        Home1.setBorderPainted(false);
        Home1.setFocusPainted(false);
        Home1.setContentAreaFilled(false);
        Home1.setIcon(homeIdle);
        Home1.setSize(200,61);
        Home1.setLocation(275,570);
        Home1.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(OptionsFrame);
                Home1.setIcon(homeFocus);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                Cursor.Idle(OptionsFrame);
                Home1.setIcon(homeIdle);
            }
        });

        Home2 = new JButton(homeIdle);
        Home2.setBorderPainted(false);
        Home2.setFocusPainted(false);
        Home2.setContentAreaFilled(false);
        Home2.setIcon(homeIdle);
        Home2.setSize(200,61);
        Home2.setLocation(275,570);
        Home2.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                Cursor.Select(OptionsFrame);
                Home2.setIcon(homeFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(OptionsFrame);
                Home2.setIcon(homeIdle);
            }
        });


        ScreenSymbol = new JLabel();
        ImageIcon displayIdle = new ImageIcon(
                Objects.requireNonNull(getClass().getClassLoader().
                        getResource("Resource\\Images\\Options\\displayIdle.png")));

        ImageIcon disPlayFocus = new ImageIcon(
                Objects.requireNonNull(getClass().getClassLoader().
                        getResource("Resource\\Images\\Options\\displayFocus.png")));

        ScreenSymbol.setIcon(displayIdle);
        ScreenSymbol.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                Options.setSelectedIndex(0);
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {
                ScreenSymbol.setIcon(disPlayFocus);
                Cursor.Select(OptionsFrame);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ScreenSymbol.setIcon(displayIdle);
                Cursor.Idle(OptionsFrame);
            }
        });

        AudioSymbol = new JLabel();
        ImageIcon musicIdle = new ImageIcon(
                Objects.requireNonNull(getClass().getClassLoader().
                        getResource("Resource\\Images\\Options\\musicIdle.png")));

        ImageIcon musicFocus = new ImageIcon(
                Objects.requireNonNull(getClass().getClassLoader().
                        getResource("Resource\\Images\\Options\\musicFocus.png")));
        AudioSymbol.setIcon(musicIdle);
        AudioSymbol.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                Options.setSelectedIndex(1);
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {
                AudioSymbol.setIcon(musicFocus);
                Cursor.Select(OptionsFrame);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                AudioSymbol.setIcon(musicIdle);
                Cursor.Idle(OptionsFrame);
            }
        });

        setDisplayMode();
        setAudioOption();

        UIManager.put("TabbedPane.unselectedForeground", Color.RED);
        UIManager.put("TabbedPane.selectedBackground", Color.RED);

        OptionsFrame = new JDialog(frame);
        OptionsFrame.setModal(true);
        OptionsFrame.setLayout(null);
        OptionsFrame.setTitle("Options");
        OptionsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        OptionsFrame.setSize(frame.getWidth(), frame.getHeight());
        OptionsFrame.setResizable(false);
        OptionsFrame.setUndecorated(true);

        Cursor.Idle(OptionsFrame);
        OptionsFrame.setBackground(new Color(0,0,0,10));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(750,750);
        panel.setBackground(Color.decode("#eeeeee"));
        panel.setLocation(frame.getBounds().width/2 - 400 , frame.getBounds().height/2 - 400);

        Options = new JTabbedPane();
        Options.setSize(750,750);
        Options.addTab("DisplayMode",displayIdle ,DisplayMode);
        Options.addTab("AudioOption",musicIdle, AudioOption);

        Options.setTabComponentAt(0, ScreenSymbol);
        Options.setTabComponentAt(1, AudioSymbol);
        Options.setBackgroundAt(0,  Color.decode("#eeeeee"));
        Options.setBackgroundAt(1, Color.decode("#eeeeee"));
        Options.setBackground(Color.RED);
        Options.setForeground(Color.RED);

        ActionListeners(isMenu);

        panel.add(Options);
        OptionsFrame.add(panel);
        OptionsFrame.setVisible(true);
    }

    private void setDisplayMode() {

        DisplayMode = new JPanel();
        DisplayMode.setBackground(Color.decode("#eeeeee"));
        DisplayMode.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        DisplayMode.setLayout(new GridLayout(0, 1));
        DisplayMode.setLayout(null);

        setMusicMessage();

        JLabel Title = new JLabel();
        Title.setText("Display Mode");
        Title.setFont(new Font("Sitka Small",Font.PLAIN,45));
        Title.setSize(250, 100);
        Title.setForeground(Color.decode("#2b2b2b"));
        Title.setBounds(175, 5, 350, 100);

        DisplayMode.add(Home1);
        DisplayMode.add(Title);
    }

    private void setAudioOption() {

        AudioOption = new JPanel();
        AudioOption.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        AudioOption.setLayout(new GridLayout(0, 1));
        AudioOption.setLayout(null);

        setBackGroundMusic();
        setVolumeSlider();
        setSFX();
        setFileChooser();

        JLabel Title = new JLabel();
        Title.setText("Audio");
        Title.setFont(new Font("Sitka Small",Font.PLAIN,45));
        Title.setSize(250, 100);
        Title.setForeground(Color.decode("#2b2b2b"));
        Title.setBounds(275, 5, 250, 100);

        AudioOption.setBackground(Color.decode("#eeeeee"));
        AudioOption.add(Home2);
        AudioOption.add(Title);
    }

    private void setMusicMessage() {

        MusicMessage = new JLabel();
        MusicMessage.setText("Music Message : ");
        MusicMessage.setFont(new Font("Tahoma", Font.ITALIC, 25));
        MusicMessage.setForeground(Color.decode("#2b2b2b"));
        MusicMessage.setBounds(100, 150, 250, 50);

        Message_ON = new JRadioButton();
        Message_ON.setText("ON");
        Message_ON.setBounds(400, 150, 100, 50);
        Message_ON.setBackground(Color.decode("#eeeeee"));
        Message_ON.setForeground(Color.decode("#2b2b2b"));
        Message_ON.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));

        Message_OFF = new JRadioButton();
        Message_OFF.setText("OFF");
        Message_OFF.setBounds(500, 150, 100, 50);
        Message_OFF.setBackground(Color.decode("#eeeeee"));
        Message_OFF.setForeground(Color.decode("#2b2b2b"));
        Message_OFF.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));

        if (isMessageOn) {
            Message_ON.setSelected(true);
            Message_ON.setEnabled(false);
        } else {
            Message_OFF.setSelected(true);
            Message_OFF.setEnabled(false);
        }

        MessageGroup = new ButtonGroup();
        MessageGroup.add(Message_ON);
        MessageGroup.add(Message_OFF);

        DisplayMode.add(MusicMessage);
        DisplayMode.add(Message_ON);
        DisplayMode.add(Message_OFF);

    }

    private void setBackGroundMusic() {

        BGMusic = new JLabel();
        BGMusic.setText("BackGround Music : ");
        BGMusic.setFont(new Font("Tahoma", Font.ITALIC, 25));
        BGMusic.setForeground(Color.decode("#2b2b2b"));
        BGMusic.setBounds(100, 150, 250, 50);

        ON = new JRadioButton();
        ON.setText("ON");
        ON.setBounds(400, 150, 100, 50);
        ON.setBackground(Color.decode("#eeeeee"));
        ON.setForeground(Color.decode("#2b2b2b"));
        ON.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));

        OFF = new JRadioButton();
        OFF.setText("OFF");
        OFF.setBounds(500, 150, 100, 50);
        OFF.setBackground(Color.decode("#eeeeee"));
        OFF.setForeground(Color.decode("#2b2b2b"));
        OFF.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));

        if (isON) {
            ON.setSelected(true);
            ON.setEnabled(false);
        } else {
            OFF.setSelected(true);
            OFF.setEnabled(false);
        }

        MusicGroup = new ButtonGroup();
        MusicGroup.add(ON);
        MusicGroup.add(OFF);

        AudioOption.add(BGMusic);
        AudioOption.add(ON);
        AudioOption.add(OFF);

    }

    private void setVolumeSlider() {

        MusicVol = new JLabel();
        MusicVol.setText("Volume :  " + SliderValue);
        MusicVol.setFont(new Font("Tahoma", Font.ITALIC, 25));
        MusicVol.setForeground(Color.decode("#2b2b2b"));
        MusicVol.setBounds(100, 250, 175, 50);

        slider = new JSlider();
        slider.setMaximum(100);
        slider.setMinimum(0);
        slider.setFont(Font.getFont("Arial"));
        slider.setValue(SliderValue);
        slider.setBackground(Color.decode("#000000"));
        slider.setBounds(375, 250, 300, 75);

        AudioOption.add(MusicVol);
        AudioOption.add(slider);

    }

    private void setSFX() {

        SFX = new JLabel();
        SFX.setText("SFX : ");
        SFX.setFont(new Font("Tahoma", Font.ITALIC, 25));
        SFX.setForeground(Color.decode("#2b2b2b"));
        SFX.setBounds(100, 350, 100, 50);

        SFX_ON = new JRadioButton();
        SFX_ON.setText("ON");
        SFX_ON.setBounds(400, 350, 100, 50);
        SFX_ON.setBackground(Color.decode("#eeeeee"));
        SFX_ON.setForeground(Color.decode("#2b2b2b"));
        SFX_ON.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));

        SFX_OFF = new JRadioButton();
        SFX_OFF.setText("OFF");
        SFX_OFF.setBounds(500, 350, 100, 50);
        SFX_OFF.setBackground(Color.decode("#eeeeee"));
        SFX_OFF.setForeground(Color.decode("#2b2b2b"));
        SFX_OFF.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));

        if (isSFX) {
            SFX_ON.setSelected(true);
            SFX_ON.setEnabled(false);
        } else {
            SFX_OFF.setSelected(true);
            SFX_OFF.setEnabled(false);
        }

        SFXgroup = new ButtonGroup();
        SFXgroup.add(SFX_ON);
        SFXgroup.add(SFX_OFF);

        AudioOption.add(SFX);
        AudioOption.add(SFX_ON);
        AudioOption.add(SFX_OFF);

    }

    private void setFileChooser() {

        CustomMusic = new JLabel();
        CustomMusic.setText("Upload Your Own Music!");
        CustomMusic.setFont(new Font("Tahoma", Font.ITALIC, 25));
        CustomMusic.setForeground(Color.decode("#2b2b2b"));
        CustomMusic.setBounds(100, 450, 600, 50);

        FileChooser = new JButton();
        FileChooser.setText("Choose File");
        FileChooser.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));
        FileChooser.setBackground(Color.decode("#000000"));
        FileChooser.setForeground(Color.decode("#ffffff"));
        FileChooser.setBounds(450, 450, 150, 50);

        AudioOption.add(CustomMusic);
        AudioOption.add(FileChooser);
    }

    public void ActionListeners(boolean inMenu) {

        Message_ON.addActionListener(Event -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
            Message_ON.setEnabled(false);
            Message_OFF.setEnabled(true);
            isMessageOn = true;
        });

        Message_OFF.addActionListener(Event -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
            Message_OFF.setEnabled(false);
            Message_ON.setEnabled(true);
            isMessageOn = false;
        });

        ON.addActionListener(Event -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
            ON.setEnabled(false);
            OFF.setEnabled(true);
            isON = true;
            isCustom = false;
            slider.setValue(50);
            if (inMenu) {
                BackgroundMusic("src\\Resource\\Sounds\\BackGroundSound\\backGroundMusic0.mp3");
                CurrentSound = "backGroundMusic";
            } else {
                BackgroundMusic("src\\Resource\\Sounds\\BackGroundSound\\GamePlay1.mp3");
                CurrentSound = "GamePlay";
            }
            loop = false;
        });

        OFF.addActionListener(Event -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
            player[0].close();
            OFF.setEnabled(false);
            ON.setEnabled(true);
            isON = false;
        });

        slider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                Cursor.Select(OptionsFrame);
                slider.setBackground(Color.decode("#ffb200"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cursor.Idle(OptionsFrame);
                slider.setBackground(Color.decode("#000000"));
            }
        });
        slider.addChangeListener(Event -> {
            SliderValue = slider.getValue();
            MusicVol.setText("Volume :  " + SliderValue);
            VolumeSlider.setGain(slider.getValue());
        });

        SFX_ON.addActionListener(Event -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
            SFX_ON.setEnabled(false);
            SFX_OFF.setEnabled(true);
            isSFX = true;
        });

        SFX_OFF.addActionListener(Event -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");
            SFX_OFF.setEnabled(false);
            SFX_ON.setEnabled(true);
            isSFX = false;
        });

        FileChooser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                FileChooser.setBackground(Color.decode("#ffb200"));
                FileChooser.setForeground(Color.decode("#2b2b2b"));
                Cursor.Select(OptionsFrame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                FileChooser.setBackground(Color.decode("#000000"));
                FileChooser.setForeground(Color.decode("#ffffff"));
                Cursor.Idle(OptionsFrame);
            }
        });
        FileChooser.addActionListener(Event -> {
            playSound("src\\Resource\\Sounds\\SFX\\new\\2.wav");

            JFC = new JFileChooser();
            JFC.setSize(500, 500);

            // resctrict the user to selec files of all types
            JFC.setAcceptAllFileFilterUsed(false);

            // set a title for the dialog
            JFC.setDialogTitle("Upload A Music Dude!");

            // only allow files of .mp3 or .wav extension
            FileNameExtensionFilter filterMP3 = new FileNameExtensionFilter("mp3", "mp3");
            FileNameExtensionFilter filterWAV = new FileNameExtensionFilter("wav", "wav");

            JFC.addChoosableFileFilter(filterMP3);
            JFC.addChoosableFileFilter(filterWAV);

            // invoke the showsSaveDialog function to show the save dialog
            int Item = JFC.showOpenDialog(null);

            // if the user selects a file
            if (Item == JFileChooser.APPROVE_OPTION && ON.isSelected()) {
                // set the Background Music to the to the selected file
                isCustom = true;
                String Path = JFC.getSelectedFile().getAbsolutePath();
                customSinger = JFC.getSelectedFile().getName();

                BackgroundMusic(Path);
                loop = false;

            } else if (OFF.isSelected()) {

                JOptionPane.showMessageDialog(frame,
                        "Background Music option is OFF\n" + "Turn On The Background Music and Try Again",
                        "Alert!", JOptionPane.WARNING_MESSAGE);
            }
        });

        Home1.addActionListener(Event -> {
            if(inMenu) MainMenu.startTimer();
            OptionsFrame.dispatchEvent(new WindowEvent(OptionsFrame, WindowEvent.WINDOW_CLOSING));
        });

        Home2.addActionListener(Event -> {
            if(inMenu) MainMenu.startTimer();
            OptionsFrame.dispatchEvent(new WindowEvent(OptionsFrame, WindowEvent.WINDOW_CLOSING));
        });
    }

}