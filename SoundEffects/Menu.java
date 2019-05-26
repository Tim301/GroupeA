package SoundEffects;
import SoundEffects.AudioPlayer;

public class Menu extends Thread {
    String audioFilePath = ("./Soundtracks/main.wav");
    AudioPlayer player = new AudioPlayer();
    public void run(){
        player.play(audioFilePath);
    }
}
