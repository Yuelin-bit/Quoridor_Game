package ca.mcgill.ecse223.quoridor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.view.LoadPosition;
import ca.mcgill.ecse223.quoridor.view.MainMenu;
import ca.mcgill.ecse223.quoridor.view.NewJBoard;
import ca.mcgill.ecse223.quoridor.view.NewJBoard4;

import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet;
import java.awt.Frame; 
import java.net.MalformedURLException; 
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;


public class QuoridorApplication {

	@SuppressWarnings("deprecation")
	static void playMusic(){//背景音乐播放
		try {
			URL cb;
			File f = new File("a"); // 引号里面的是音乐文件所在的路径
			cb = f.toURL();
			AudioClip aau;
			aau = Applet.newAudioClip(cb);
		
			aau.play();	
			aau.loop();//循环播放
			System.out.println("可以播放");
			// 循环播放 aau.play()
			//单曲 aau.stop()停止播放
 
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
	}

	private static Quoridor quoridor;
	static MainMenu mainMenu;
	public static MainMenu getMainMenu() {
		return mainMenu;
	}
	public static void setMainMenu(MainMenu mainMenu) {
		QuoridorApplication.mainMenu = mainMenu;
	}

	private static NewJBoard jboard;
	private static NewJBoard4 jboard4;
	private static LoadPosition loadposition;
	public static LoadPosition getLoadposition() {
		return loadposition;
	}
	public static void setLoadposition(LoadPosition loadposition) {
		QuoridorApplication.loadposition = loadposition;
	}

	private JOptionPane errorHint;
	private String error = null;
	
	public static NewJBoard4 getJboard4() {
		return jboard4;
	}
	public static void setJboard4(NewJBoard4 jboard4) {
		QuoridorApplication.jboard4 = jboard4;
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public JOptionPane getErrorHint() {
		return errorHint;
	}
	public void setErrorHint(JOptionPane errorHint) {
		this.errorHint = errorHint;
	}
	
	public static void setJboard(NewJBoard jboard) {

		QuoridorApplication.jboard = jboard;
	}

	public static NewJBoard getJboard() {
		return jboard;
	}
	
	public static LoadPosition getLoadPosition() {
		return loadposition;
	}

	public static void main(String[] args) {
		// start UI
		playMusic();
		mainMenu = new MainMenu();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	QuoridorController.initQuoridorAndBoard();
                mainMenu.setVisible(true);
            }
        });
	}
	
	public static Quoridor getQuoridor() {
		if (quoridor == null) {
			quoridor = new Quoridor();
		}
 		return quoridor;
	}

}