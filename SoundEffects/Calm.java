package SoundEffects;
import SoundEffects.AudioPlayer;

public class Calm extends Thread {
    String audioFilePath = ("./Soundtracks/calm.wav");
    AudioPlayer player = new AudioPlayer();
    public boolean state;
    public Calm(boolean tmp){
        this.state = tmp;
    }

    public void run(){
        if (state == true){
            player.play(audioFilePath);
        }
    }

    public void exit(){
        if (state == true){
            player.stop();
        }
    }

}
