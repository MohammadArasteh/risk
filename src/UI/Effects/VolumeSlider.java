package UI.Effects;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.swing.*;

public class VolumeSlider {
    // to get the audio system gain control
    public static void setGain(float ctrl) {
        try {
            Mixer.Info[] infos = AudioSystem.getMixerInfo();
            for (Mixer.Info info : infos) {
                Mixer mixer = AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                    Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
                    port.open();
                    if (port.isControlSupported(FloatControl.Type.VOLUME)) {
                        FloatControl volume = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
                        volume.setValue(ctrl / 100);
                    }
                    port.close();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n" + e);
        }
    }
}