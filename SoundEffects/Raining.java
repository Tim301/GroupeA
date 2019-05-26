package SoundEffects;
import SoundEffects.AudioPlayer;

public class Raining extends Thread {
    String audioFilePath = ("./Soundtracks/rain.wav");
    AudioPlayer player = new AudioPlayer();
    public boolean state;
    public Raining(boolean tmp){
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
