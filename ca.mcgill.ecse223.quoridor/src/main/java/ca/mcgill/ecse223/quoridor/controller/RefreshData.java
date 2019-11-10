package ca.mcgill.ecse223.quoridor.controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import ca.mcgill.ecse223.quoridor.view.NewJBoard;

public class RefreshData extends Thread{
	public NewJBoard board;
	public RefreshData(NewJBoard board) {
		this.board = board;
	}
	public void run() {
		while(true) {
		JLabel lblTime = new JLabel("Time3");
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
			//board.setTxtrTime();
			try {
				Thread.sleep(1000);
			}
			catch(Exception e) {
				System.out.println("Message:"+e.getMessage());
				System.out.println("Caused by:"+e.getCause());

			}
		}
	}
	
}
