package utils;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

public class SoundUtil {
	
	private static ArrayList<AudioClip> bgmList = new ArrayList<AudioClip>();
	private static int index;
	private static boolean play;
	
	static {
		loadResource();
		index = 0;
		play = false;
	}
	
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
	
	public static String changeBgm() {
		index = (index+1) % (bgmList.size());
		if(index != 0) {
			bgmList.get(index-1).stop();
		}else {
			bgmList.get(bgmList.size()-1).stop();
		}
		bgmList.get(index).play();
		
		return "Song " + (index+1);
	}
	
	public static String getBgm() {
		return "Song " + (index+1);
	}
	
	public static String toggleBgm() {
		play = !play;
		if(play) {
			bgmList.get(index).play();
			return "Stop Song";
		}else {
			bgmList.get(index).stop();
			return "Play Song";
		}
	}
}
