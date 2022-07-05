package dev.suback.marshmallow.resource;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MSSound {

	private URL url;
	private AudioInputStream audioIn;
	private Clip clip;
	private String path;
	public int startPosition = 0;
	
	public Clip getClip() {
		return clip;
	}
	
	private void getSound() {
		try {
			url = this.getClass().getClassLoader().getResource(path);
			audioIn = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MSSound(String path) {
		this.path = path;
		getSound();
	}

	public void play() {
		Thread thread = new Thread() {
			
			public void run() {
				try {
					getSound();
					clip.setFramePosition(startPosition);
					clip.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.setDaemon(false);
		thread.start();
	}

}
