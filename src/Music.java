import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	public static Clip clip;
	public static Clip clip2;
	public static Clip clip3;
	public static Clip clip4;
	public static Clip clip5;
	public static Clip clip6;
	public static Clip clip7;

	public static Mixer mixer;
	public static InputStream sound;
	public static InputStream sound2;
	public static InputStream sound3;
	public static InputStream sound4;
	public static InputStream sound5;
	public static InputStream sound6;
	public static InputStream sound7;

	/*public static AudioInputStream audioStream;
	public static AudioInputStream audioStream2;
	public static AudioInputStream audioStream3;
	public static AudioInputStream audioStream4;
	public static AudioInputStream audioStream5;
	public static AudioInputStream audioStream6;
	public static AudioInputStream audioStream7;*/


	public Music(){
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[0]);	
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try{
			clip = AudioSystem.getClip();
			clip2 = AudioSystem.getClip();
			clip3 = AudioSystem.getClip();
			clip4 = AudioSystem.getClip();
			clip5 = AudioSystem.getClip();
			clip6 = AudioSystem.getClip();
			clip7 = AudioSystem.getClip();
		}catch(LineUnavailableException e){
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		try{	
			//"sound/2AH.wav"
			sound = this.getClass().getClassLoader().getResourceAsStream("sound/Life_Goes_On.wav");
			sound2 = this.getClass().getClassLoader().getResourceAsStream("sound/2AH.wav");
			sound3 = this.getClass().getClassLoader().getResourceAsStream("sound/2DH.wav");
			sound4 = this.getClass().getClassLoader().getResourceAsStream("sound/ryushoryken.wav");
			sound5 = this.getClass().getClassLoader().getResourceAsStream("sound/ryuhadoken.wav");
			sound6 = this.getClass().getClassLoader().getResourceAsStream("sound/47H.wav");
			sound7 = this.getClass().getClassLoader().getResourceAsStream("sound/20H.wav");
			
			InputStream bufferedIn = new BufferedInputStream(sound);
			InputStream bufferedIn2 = new BufferedInputStream(sound2);
			InputStream bufferedIn3 = new BufferedInputStream(sound3);
			InputStream bufferedIn4 = new BufferedInputStream(sound4);
			InputStream bufferedIn5 = new BufferedInputStream(sound5);
			InputStream bufferedIn6 = new BufferedInputStream(sound6);
			InputStream bufferedIn7 = new BufferedInputStream(sound7);
			
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
			AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(bufferedIn2);
			AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(bufferedIn3);
			AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(bufferedIn4);
			AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(bufferedIn5);
			AudioInputStream audioStream6 = AudioSystem.getAudioInputStream(bufferedIn6);
			AudioInputStream audioStream7 = AudioSystem.getAudioInputStream(bufferedIn7);
			clip.open(audioStream);
			clip2.open(audioStream2);
			clip3.open(audioStream3);
			clip4.open(audioStream4);
			clip5.open(audioStream5);
			clip6.open(audioStream6);
			clip7.open(audioStream7);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public static void play(){
		clip.start();
	}
	public static void loop(){
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public static void play2(){
		clip2.stop();
		clip2.setMicrosecondPosition(0);
		clip2.start();
	}
	
	public static void play3(){
		clip3.stop();
		clip3.setMicrosecondPosition(0);
		clip3.start();
	}
	
	public static void play4(){
		clip4.stop();
		clip4.setMicrosecondPosition(0);
		clip4.start();
	}
	
	public static void play5(){
		clip5.stop();
		clip5.setMicrosecondPosition(0);
		clip5.start();
	}
	
	public static void play6(){
		clip6.stop();
		clip6.setMicrosecondPosition(0);
		clip6.start();
	}
	
	public static void play7(){
		clip7.stop();
		clip7.setMicrosecondPosition(0);
		clip7.start();
	}

}
