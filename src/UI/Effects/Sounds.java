package UI.Effects;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static Main.Main.*;
import static Resource.Fonts.CustomFont.*;
import static UI.Menu.MainMenu.*;
import static UI.Menu.OptionsScene.*;

public class Sounds {

    //Storage for Clips ( Sounds in the Current Scene )
    public static Map<String, Clip> sounds = new HashMap<>();
    private static HashMap<String, String> MusicDetails;

    //Player[1] : CustomMusic Channel
    //Player[3] : Guide Voice Channel
    private static File[] file;
    private static FileInputStream[] fis;
    private static BufferedInputStream[] bis;
    public static Player[] player;

    public static String CurrentSound = "backGroundMusic";
    private static int Counter = 0;
    public static boolean loop = true;

    private static JDialog MessageFrame;
    private static JPanel MessagePanel;

    private static JLabel Message;
    private static JLabel MessageIcon;
    private static JLabel MessageBackground;

    private static Timer UpMotion;
    private static Timer DownMotion;

    public static void init() {
        setSingers();
        try {
            file = new File[4];
            fis = new FileInputStream[4];
            bis = new BufferedInputStream[4];
            player = new Player[10];

            for (int index = 0; index < 4; index++) {
                if (index < 2)
                    file[index] = new File("src\\Resource\\Sounds\\BackGroundSound\\Test.mp3");
                else
                    file[index] = new File("src\\Resource\\Sounds\\SFX\\ButtonClick.wav");

                fis[index] = new FileInputStream(file[index]);
                bis[index] = new BufferedInputStream(fis[index]);
                try {
                    player[index] = new Player(bis[index]);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //setting Background music
    public static void BackgroundMusic(String path) {

        Counter = 0;
        pause(0);
        player[0].close();

        if (isON) {
            if (isMessageOn) {
                try {
                    MessageFrame.setVisible(false);
                    MessageFrame.getParent().remove(MessageFrame);
                    MessageFrame.getParent().repaint();

                    MessageFrame.dispose();
                    MessageFrame.dispatchEvent(new WindowEvent(MessageFrame, WindowEvent.WINDOW_CLOSED));
                    MessageFrame.dispatchEvent(new WindowEvent(MessageFrame, WindowEvent.WINDOW_CLOSING));

                    frame.getContentPane().remove(MessageFrame);
                    frame.remove(MessageFrame);
                    frame.getContentPane().repaint();
                    frame.repaint();
                    showMusicDetails();
                } catch (NullPointerException E) {
                    showMusicDetails();
                }
            }

            new Thread(() -> {
                try {
                    File file = new File(path);
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    try {
                        player[0] = new Player(bis);
                        player[0].play();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                    setLoop(path);
                } catch (FileNotFoundException e) {
                    System.out.println("CAN'T OPEN THE FILE!");
                }

            }).start();
        }
    }

    //Sound for Clicking buttons
    public static void ButtonsSound(String Structure) {
        if (Structure.toLowerCase().equals("play")) {
            new Thread(() -> {
                try {
                    player[2].play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }

            }).start();
        } else
            new Thread(() -> player[2].close()).start();

        try {
            File file = new File("src\\Resource\\Sounds\\SFX\\ButtonClick.wav");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            try {
                player[2] = new Player(bis);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //It works when player must be guided
    public static void GuideSound(String path) {
        player[3].close();
        new Thread(() -> {
            try {
                File file = new File(path);
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);

                try {
                    player[3] = new Player(bis);
                    player[3].play();
                } catch (JavaLayerException error) {
                    error.printStackTrace();
                }

            } catch (FileNotFoundException error) {
                error.printStackTrace();
            }

        }).start();
    }

    //Add Sound Effects for Buttons
    private static void loadResources(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            sounds.put(soundName, clip);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void playSound(String soundName) {
        loadResources(soundName);
        Clip c = sounds.get(soundName);
        c.setFramePosition(0);
        if (isSFX) c.start();
    }

    public static void pause(int index) {
        new Thread(() -> player[index].close());
    }

    public static void setLoop(String path) {
        Counter = 0;
        while (Counter < Integer.MAX_VALUE) {

            if (!loop) {
                loop = true;
                break;
            }

            if (player[0].isComplete()) {

                Counter++;
                if (isMessageOn) showMusicDetails();

                try {
                    if (!CurrentSound.equals("custom") && !CurrentSound.equals("Ending")) {
                        File file = new File("src\\Resource\\Sounds\\BackGroundSound\\" +
                                CurrentSound + (Counter % 5) + ".mp3");
                        FileInputStream fis = new FileInputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        try {
                            player[0] = new Player(bis);
                            player[0].play();
                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        }
                    } else if (CurrentSound.equals("Ending")) {

                        File file = new File("src\\Resource\\Sounds\\BackGroundSound\\" +
                                CurrentSound + (Counter % 2) + ".mp3");
                        FileInputStream fis = new FileInputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        try {
                            player[0] = new Player(bis);
                            player[0].play();
                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        }

                    } else {

                        File file = new File(path);
                        FileInputStream fis = new FileInputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        try {
                            player[0] = new Player(bis);
                            player[0].play();
                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setSingers() {

        MusicDetails = new HashMap<>();

        MusicDetails.put("backGroundMusic0", "Cold War, Martin Dexter");
        MusicDetails.put("backGroundMusic1", "Once Upon A Time At Heaven, Max Jones");
        MusicDetails.put("backGroundMusic2", "Hide & Sick, Maria Gonzales");
        MusicDetails.put("backGroundMusic3", "Smooth Symphony, George Rough");
        MusicDetails.put("backGroundMusic4", "Nothing, Merry Kent");
        MusicDetails.put("GamePlay0", "Anniversary, Jeremy Hunt");
        MusicDetails.put("GamePlay1", "Anniversary, Jeremy Hunt");
        MusicDetails.put("GamePlay2", "Light Dream, Lucas Morn");
        MusicDetails.put("GamePlay3", "Wings Of Soul, Yan Tiersen");
        MusicDetails.put("GamePlay4", "Life's Timeline, Jennifer Punk");
        MusicDetails.put("Ending0", "Epic Inspirational Victory, Chris Minister");
        MusicDetails.put("Ending1", "The Champion, Blanston Trend");
    }

    private static void showMusicDetails() {

        MessageFrame = new JDialog(frame);
        MessageFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        MessageFrame.setSize(400, 225);
        MessageFrame.setResizable(false);
        MessageFrame.setUndecorated(true);
        MessageFrame.setLocation(frame.getBounds().width - 400, frame.getBounds().height);

        MessagePanel = new JPanel();
        MessagePanel.setLayout(null);
        MessagePanel.setSize(400, 225);
        MessagePanel.setLocation(frame.getBounds().width - 400, frame.getBounds().height);


        ImageIcon BG = new ImageIcon("src\\Resource\\Images\\Backgrounds\\MusicGradient.png");
        Image img = BG.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
        BG.setImage(img);

        MessageBackground = new JLabel();
        MessageBackground.setIcon(BG);
        MessageBackground.setSize(400, 225);
        MessageBackground.setLocation(0, 0);

        ImageIcon icon = new ImageIcon("src\\Resource\\Images\\Backgrounds\\MusicIcon.gif");
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        icon.setImage(image);

        MessageIcon = new JLabel();
        MessageIcon.setIcon(icon);
        MessageIcon.setSize(100, 100);
        MessageIcon.setLocation(0, 0);

        Message = new JLabel();
        Message.setFont(new Font("Sitka Small", Font.PLAIN, 22));
        Message.setForeground(Color.WHITE);
        Message.setSize(300, 75);
        Message.setLocation(MessageFrame.getBounds().width - Message.getBounds().width - 25,
                MessageFrame.getBounds().height - Message.getBounds().height - 50);

        if (!isCustom && !CurrentSound.equals("Ending"))
            Message.setText(MusicDetails.get(CurrentSound + (Counter % 5)));
        else if (isCustom)
            Message.setText(customSinger);
        else
            Message.setText(MusicDetails.get(CurrentSound + (Counter % 2)));

        MessagePanel.add(MessageIcon);
        MessagePanel.add(Message);
        MessagePanel.add(MessageBackground);

        MessageFrame.add(MessagePanel);
        frame.getContentPane().repaint();
        MessageFrame.setVisible(true);

        UpMotion = new Timer(25, Event -> UpTransition());
        UpMotion.start();
    }

    public static void UpTransition() {

        if (MessageFrame.getY() > frame.getBounds().height - MessageFrame.getBounds().height)
            MessageFrame.setLocation(MessageFrame.getX(), MessageFrame.getY() - 5);
        else {
            UpMotion.stop();
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DownMotion = new Timer(25, Event -> DownTransition());
                DownMotion.start();
            }).start();
        }
    }

    public static void DownTransition() {

        if (MessageFrame.getY() < frame.getBounds().height) {
            MessageFrame.setLocation(MessageFrame.getX(), MessageFrame.getY() + 5);
        } else {
            DownMotion.stop();
        }
    }
}
