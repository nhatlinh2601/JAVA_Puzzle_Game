package Sound;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class sound {
	private static AudioInputStream audioStream;
	public static Clip clip;
	
	public static void playSoundMain(String path) {
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playSoundOneClick(String path) {
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeSound() {
	
		try {
			clip.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
