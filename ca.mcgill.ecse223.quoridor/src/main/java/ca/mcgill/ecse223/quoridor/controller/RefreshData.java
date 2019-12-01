package ca.mcgill.ecse223.quoridor.controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.view.NewJBoard;

public class RefreshData extends Thread{
	public NewJBoard board;
	public RefreshData(NewJBoard board) {
		this.board = board;
	}
	public void run() {
		while(true) {
			GameStatus current = QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus();
			boolean whiteTurn = QuoridorApplication.getJboard().isWhiteTurn();
			
			boolean blackTurn = !whiteTurn;
		
			if(current == GameStatus.BlackWon) {
				board.getBigTime().setBackground(Color.RED);
				board.getBigTime().setText("Black Won!");
			}else if (current == GameStatus.WhiteWon) {
				board.getBigTime().setBackground(Color.RED);
				board.getBigTime().setText("White Won!");
			}
			//board.getwhitePawn().setVisible(whiteTurn);
			//board.getblackPawn().setVisible(blackTurn);
			board.getWhiteStock().setText(QuoridorController.getWhiteStocks());
			board.getBlackStock().setText(QuoridorController.getBlackStocks());	
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
