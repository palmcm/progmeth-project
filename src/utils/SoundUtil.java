package utils;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

/**
 * Utility for sound
 */
public class SoundUtil {
	/** All song list*/
	private static ArrayList<AudioClip> bgmList = new ArrayList<AudioClip>();
	/** Current song index*/
	private static int index;
	/** Is currently play*/
	private static boolean play;
	
	static {
		loadResource();
		index = 0;
		play = false;
	}
	
	/**
	 * Load song 
	 */
	public static void loadResource() {
		AudioClip bgm1 = new AudioClip(ClassLoader.getSystemResource("sound/bgm1.mp3").toString());
		bgm1.setVolume(0.05);
		bgm1.setCycleCount(AudioClip.INDEFINITE);
		bgmList.add(bgm1);
		
		AudioClip bgm2 = new AudioClip(ClassLoader.getSystemResource("sound/bgm2.mp3").toString());
		bgm2.setVolume(0.05);
		bgm2.setCycleCount(AudioClip.INDEFINITE);
		bgmList.add(bgm2);
		
		
	}
	
	/**
	 * Change background music
	 * @return currently song
	 */
	public static String changeBgm() {
		index = (index+1) % (bgmList.size());
		if(index != 0) {
			bgmList.get(index-1).stop();
		}else {
			bgmList.get(bgmList.size()-1).stop();
		}
		bgmList.get(index).play();
		
		return "Music " + (index+1);
	}
	
	/**
	 * Get currently song
	 * @return currently song
	 */
	public static String getBgm() {
		return "Music " + (index+1);
	}
	
	/**
	 * Toggle music
	 * @return display string for option
	 */
	public static String toggleBgm() {
		play = !play;
		if(play) {
			bgmList.get(index).play();
			return "Stop Music";
		}else {
			bgmList.get(index).stop();
			return "Play Music";
		}
	}
}
