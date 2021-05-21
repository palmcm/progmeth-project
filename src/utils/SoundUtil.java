package utils;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

public class SoundUtil {
	
	private static AudioClip bgm1;
	
	private static ArrayList<AudioClip> bgmList = new ArrayList<AudioClip>();
	private static int index;
	
	static {
		loadResource();
		index = -1;
	}
	
	public static void loadResource() {
		bgm1 = new AudioClip(ClassLoader.getSystemResource("sound/bgm.mp3").toString());
		bgm1.setVolume(0.1);
		bgm1.setCycleCount(AudioClip.INDEFINITE);
		
		bgmList.add(bgm1);
	}
	
	public static String toggleBgm() {
		index = (index+1) % (bgmList.size()+1);
		if(index != 0) {
			bgmList.get(index-1).stop();
		}
		if (index >= bgmList.size()) {
			return "Change to Song 1";
		}
		bgmList.get(index).play();
		if (index+1 == bgmList.size()) {
			return "Turn off Song";
		}
		
		return "Change to Song " + (index+1);
	}
}
