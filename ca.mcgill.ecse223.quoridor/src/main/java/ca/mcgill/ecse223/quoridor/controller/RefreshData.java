package ca.mcgill.ecse223.quoridor.controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.view.NewJBoard;

public class RefreshData extends Thread{
	public NewJBoard board;
	public RefreshData(NewJBoard board) {
		this.board = board;
	}
	public void run() {
		while(true) {
			boolean whiteTurn = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().hasGameAsWhite();
			boolean blackTurn = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().hasGameAsBlack();
			int whiteInStock = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWalls().size();
			int blackInStock = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWalls().size();
			board.getwhitePawn().setVisible(whiteTurn);
			board.getblackPawn().setVisible(blackTurn);
			board.getwhiteStock().setText(whiteInStock+"left");
			board.getblackStock().setText(blackInStock+"left");
			int min,sec;
			if(whiteTurn) {
				min = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getRemainingTime().getMinutes();
				sec = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getRemainingTime().getSeconds();
				board.getwhiteTime().setText(min+":"+sec);
				board.getBigTime().setText(min+":"+sec);
			}else{
				min = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getRemainingTime().getMinutes();
				sec = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getRemainingTime().getSeconds();
				board.getblackTime().setText(min+":"+sec);
				board.getBigTime().setText(min+":"+sec);
				
			}
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
