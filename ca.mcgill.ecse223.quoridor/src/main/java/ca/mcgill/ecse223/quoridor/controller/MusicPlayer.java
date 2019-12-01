package ca.mcgill.ecse223.quoridor.controller;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer {
	public static void play(String fileurl) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));
			AudioFormat aif = ais.getFormat();
			final SourceDataLine sdl;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			double value = 2;
			float dB =(float) (Math.log(value==0.0?0.0001:value)/Math.log(10.0)*20.0);
			fc.setValue(dB);
			int nByte = 0;
			final int SIZE = 1024*64;
			byte[] buffer = new byte[SIZE];
			while(nByte!=-1) {
				nByte = ais.read(buffer, 0, SIZE);
				if(nByte==-1) {
					break;
				}
				sdl.write(buffer,  0,  nByte);
			}
			sdl.stop();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
