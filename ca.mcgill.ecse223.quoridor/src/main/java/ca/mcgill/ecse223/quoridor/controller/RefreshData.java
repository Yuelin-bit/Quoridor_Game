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
			boolean whiteTurn = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().equals(
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove());
			
			boolean blackTurn = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().equals(
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove());
		
			board.getwhitePawn().setVisible(whiteTurn);
			board.getblackPawn().setVisible(blackTurn);
			board.getwhiteStock().setText(QuoridorController.getWhiteStocks());
			board.getblackStock().setText(QuoridorController.getBlackStocks());	
			if(whiteTurn) {
				String time = QuoridorController.getWhiteRemainingTime();
				board.getwhiteTime().setText(time);
				board.getBigTime().setText(time);
			}else{
				String time = QuoridorController.getBlackRemainingTime();
				board.getblackTime().setText(time);
				board.getBigTime().setText(time);
				
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
