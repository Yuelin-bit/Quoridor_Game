package ca.mcgill.ecse223.quoridor.view;

import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet;
import java.awt.Frame; 
import java.net.MalformedURLException; 
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;
public class Music extends JFrame{ 
File f;
 URI uri;
     URL url; 
 // Music(){
 //     bgMusic();
 //  }
 Music(){  
   try {      
       f = new File("/Users/yuelinliu/Desktop/ECSE223/git-1/ecse223-project--group-02/ca.mcgill.ecse223.quoridor/⁨ca⁩/⁨mcgill⁩/⁨ecse223⁩/⁨quoridor⁩/resources⁩/shuiMu.mp3"); 
       uri = f.toURI();
       url = uri.toURL();  //解析地址
       AudioClip aau; 
       aau = Applet.newAudioClip(url);
       aau.loop();  //循环播放
   } catch (Exception e) 
   { e.printStackTrace();
   } 
 }
  public static void main(String args[]) { 
    new Music();
  }
 }