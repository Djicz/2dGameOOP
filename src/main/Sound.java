package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip; // Su dung luu tep am thanh
    URL soundURL[] = new URL[30]; // mang luu duong dan cac am thanh

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/worldMap.wav");
        soundURL[1] = getClass().getResource("/sound/dungeon.wav");
        soundURL[2] = getClass().getResource("/sound/chest_close.wav");
        soundURL[3] = getClass().getResource("/sound/chest_open.wav");
        soundURL[4] = getClass().getResource("/sound/human_atk_sword.wav");
        soundURL[5] = getClass().getResource("/sound/human_special_atk.wav");
        soundURL[6] = getClass().getResource("/sound/human_walk.wav");
        soundURL[7] = getClass().getResource("/sound/orc_damage.wav");
        soundURL[8] = getClass().getResource("/sound/boss_atk.wav");
    }

    public void setFile(int i) {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e) {

        }
    }
    public void play() {

        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
