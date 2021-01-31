package Game.Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Objects;

public class CustomTimer implements Serializable {
    private transient Timer timer;

    private long hoursPassed = 0;
    private long minutePassed = 0;
    private long secondPassed = 0;

    private transient JLabel content;
    private transient JLabel time;
    public boolean isAwake;
    public void init() {
        setContent();
        this.timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });
    }
    public void setContent() {
        this.content = new JLabel();
        this.content.setBackground(new Color(0,0,0,0));
        this.content.setLayout(new FlowLayout(FlowLayout.LEFT,20,5));
        this.content.setSize(300,80);

        this.time = new JLabel("00:00:00");
        this.time.setFont(new Font("Impact",Font.PLAIN,40));
        this.time.setForeground(Color.decode("#29c300"));
        updateTimer();

        this.content.add(time);

    }
    public JLabel getContent() {
        return content;
    }
    public void start() {
        this.timer.start();
        this.isAwake = true;
    }
    public void stop() {
        this.timer.stop();
        this.isAwake = false;
    }
    private void Update() {
        this.secondPassed += 1;
        updateTimer();
    }
    private void updateTimer() {

        if(this.secondPassed >= 60)
        {
            this.minutePassed += secondPassed/60;
            this.secondPassed %= 60;
        }
        if(this.minutePassed >= 60)
        {
            this.hoursPassed += minutePassed/60;
            this.minutePassed %= 60;
        }

        String result = String.format("%02d:%02d:%02d",hoursPassed,minutePassed,secondPassed);
        this.time.setText(result);
    }
}
