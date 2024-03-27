import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private File winFile = new File(getClass().getClassLoader().getResource("winner.wav").getPath());
    private File looseFile = new File(getClass().getClassLoader().getResource("loose.wav").getPath());
    private File drawFile = new File(getClass().getClassLoader().getResource("draw.wav").getPath());

    public void playWin() {
        play(winFile);
    }

    public void playLoose() {
        play(looseFile);
    }

    public void playDraw() {
        play(drawFile);
    }

    private void play(File file) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
