package sk.pocsik.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {

//    private final File winFile;
//    private final File looseFile;
//    private final File drawFile;

    private final InputStream winFile;
    private final InputStream looseFile;
    private final InputStream drawFile;

    public SoundPlayer() {
        winFile = getClass().getClassLoader().getResourceAsStream("winner.wav");
        looseFile = getClass().getClassLoader().getResourceAsStream("loose.wav");
        drawFile = getClass().getClassLoader().getResourceAsStream("draw.wav");
    }


    public void playWin() {
        play(winFile);
    }

    public void playLoose() {
        play(looseFile);
    }

    public void playDraw() {
        play(drawFile);
    }

    private void play(InputStream file) {

        if (file != null) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
