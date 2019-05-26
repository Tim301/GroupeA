package SoundEffects;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 * @author www.codejava.net
 *
 */    

public class AudioPlayer {

    /**
     * this flag indicates whether the playback completes or not.
     */
    public boolean playCompleted;
    
    private Clip audioClip; 

    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    public void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);
            
            //audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN);

            audioClip.open(audioStream);
            audioClip.start();
            while (!playCompleted) {
                // wait for the playback completes
                try {
            Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            audioClip.close();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }

    }
    
    public void stop(){
        audioClip.close();
    }

}

