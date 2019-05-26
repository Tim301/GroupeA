package SoundEffects;
import SoundEffects.AudioPlayer;

public class Thunder extends Thread {
    String audioFilePath = ("./Soundtracks/thunder1.wav");
    AudioPlayer player = new AudioPlayer();
    public boolean state;
    public Thunder(boolean tmp){
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
