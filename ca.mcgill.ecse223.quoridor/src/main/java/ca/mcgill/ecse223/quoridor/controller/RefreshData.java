package ca.mcgill.ecse223.quoridor.controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	@SuppressWarnings("deprecation")
	public void run() {
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();		
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();		
		watch = new Stopwatch(whitePlayer,blackPlayer);
		watch.start();
		while(true) {
			boolean whiteTurn = QuoridorApplication.getJboard().isWhiteTurn();			
			GameStatus current = QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus();
			if(	!current.equals(GameStatus.BlackWon)&&
					!current.equals(GameStatus.WhiteWon)&&
					!current.equals(GameStatus.Draw)) {
				if(watch.isWhiteTimeIsEnd()&&watch.isBlackTimeIsEnd()){			
					watch.stop();

				}

				if(whiteTurn) {
					watch.setWhiteRunning(true);
					watch.setBlackRunning(false);

					if(watch.isWhiteTimeIsEnd()) {
						QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.BlackWon);
						board.getBigTime().setBackground(Color.RED);
						board.getBigTime().setText("Black Won!");
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
						QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.WhiteWon);
						board.getBigTime().setBackground(Color.RED);
						board.getBigTime().setText("White Won!");

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
			}else {
				if(current == GameStatus.BlackWon) {
					board.getBigTime().setForeground(Color.RED);
					board.getBigTime().setText("Black Won!");

				}else if (current == GameStatus.WhiteWon) {
					board.getBigTime().setForeground(Color.RED);
					board.getBigTime().setText("White Won!");
				}else if(current == GameStatus.Draw) {
					board.getBigTime().setForeground(Color.RED);
					board.getBigTime().setText("Draw!");
				}	

				this.stop();
			}

		}
	}

}
