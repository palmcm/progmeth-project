package utils;

import java.net.URL;
import java.util.ArrayList;

import javafx.scene.media.AudioClip;
import logic.towers.BaseTower;

/**
 * Utility for sound
 */
public class SoundUtil {
	/** All song list*/
	private static ArrayList<AudioClip> bgmList = new ArrayList<AudioClip>();
	/** Current song index*/
	private static int bgmIndex;
	/** Is currently play*/
	private static boolean play;
	
	/** All effect pack*/
	private static ArrayList<String> attackEffectPacks = new ArrayList<String>();
	
	/** All effect pack path*/
	private static ArrayList<String> attackEffectPacksUrl = new ArrayList<String>();
	
	/** Effect index*/
	private static int effectIndex;
	
	private static AudioClip endTurn;
	
	
	
	static {
		loadResource();
		bgmIndex = 0;
		play = false;
		effectIndex = 1;
		addEffectPack("Off","off");
		addEffectPack("Basic Pack","normal");
	}
	
	/**
	 * Load song 
	 */
	public static void loadResource() {
		endTurn = new AudioClip(ClassLoader.getSystemResource("sound/end_turn.wav").toString());
		
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
		bgmIndex = (bgmIndex+1) % (bgmList.size());
		if(bgmIndex != 0) {
			bgmList.get(bgmIndex-1).stop();
		}else {
			bgmList.get(bgmList.size()-1).stop();
		}
		bgmList.get(bgmIndex).play();
		
		return "Music " + (bgmIndex+1);
	}
	
	/**
	 * Get currently song
	 * @return currently song
	 */
	public static String getBgm() {
		return "Music " + (bgmIndex+1);
	}
	
	/**
	 * Toggle music
	 * @return display string for option
	 */
	public static String toggleBgm() {
		play = !play;
		if(play) {
			bgmList.get(bgmIndex).play();
			return "Stop Music";
		}else {
			bgmList.get(bgmIndex).stop();
			return "Play Music";
		}
	}
	
	/**
	 * Add pack to {@link #attackEffectPacks}
	 * @param packUrl Path to sound effect
	 * @param Display Display in Option
	 */
	public static void addEffectPack(String displayString, String packUrl) {
		attackEffectPacks.add(displayString);
		attackEffectPacksUrl.add(packUrl);
	}
	/**
	 * Change effect pack
	 */
	public static void changeEffectPack() {
		effectIndex = (effectIndex+1) % (attackEffectPacks.size());
	}
	
	/**
	 * Get currently effect pack
	 * @return currently effect pack
	 */
	public static String getEffectPack() {
		return attackEffectPacks.get(effectIndex);
	}
	
	/**
	 * Play effect according to tower
	 * @param tower Attacking tower
	 */
	public static void playAttackEffect(BaseTower tower) {
		if (tower == null || tower.getAttackSoundUrl() == null) {
			return;
		}
		URL url = ClassLoader.getSystemResource("sound/pack/"+attackEffectPacksUrl.get(effectIndex)+"/"+tower.getAttackSoundUrl());
		if (url == null) {
			return;
		}
		AudioClip effect = new AudioClip(url.toString());
		effect.play(0.1);
	}
	
	/**
	 * Play end turn sound
	 */
	public static void playEndturn() {
		if (effectIndex == 0) {
			return;
		}
		endTurn.play(0.2);
	}
}
