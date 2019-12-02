package ca.mcgill.ecse223.quoridor.controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.view.NewJBoard;

public class RefreshData extends Thread{
	public NewJBoard board;
	public Stopwatch watch;
	public RefreshData(NewJBoard board) {
		this.board = board;
	}
	public void run() {
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();		
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();		
		watch = new Stopwatch(whitePlayer,blackPlayer);
		watch.start();
		while(true) {
			GameStatus current = QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus();
			boolean whiteTurn = QuoridorApplication.getJboard().isWhiteTurn();			
			boolean blackTurn = !whiteTurn;
			if(watch.isWhiteTimeIsEnd()&&watch.isBlackTimeIsEnd()){
				board.getBigTime().setText("Game is END");
				watch.stop();

			}
			if(current == GameStatus.BlackWon) {
				board.getBigTime().setBackground(Color.RED);
				board.getBigTime().setText("Black Won!");
			}else if (current == GameStatus.WhiteWon) {
				board.getBigTime().setBackground(Color.RED);
				board.getBigTime().setText("White Won!");
			}
			if(whiteTurn) {
				watch.setWhiteRunning(true);
				watch.setBlackRunning(false);
				
				if(watch.isWhiteTimeIsEnd()) {
					board.getwhiteTime().setText("Time is UP!");
					QuoridorApplication.getJboard().setWhiteTurn(false);

					
				}else {
					String time = QuoridorController.getWhiteRemainingTime();
					board.getwhiteTime().setText(time);
					board.getBigTime().setText(time);

				}

			}else{
				watch.setWhiteRunning(false);
				watch.setBlackRunning(true);
				if(watch.isBlackTimeIsEnd()) {
					board.getblackTime().setText("Time is UP!");
					QuoridorApplication.getJboard().setWhiteTurn(true);

				}else{			
					String time =QuoridorController.getBlackRemainingTime();
					board.getblackTime().setText(time);
					board.getBigTime().setText(time);
				}
				

				
			}
			
			try {
				Thread.sleep(500);
			}
			catch(Exception e) {
				System.out.println("Message:"+e.getMessage());
				System.out.println("Caused by:"+e.getCause());

			}
		}
	}
	
}
