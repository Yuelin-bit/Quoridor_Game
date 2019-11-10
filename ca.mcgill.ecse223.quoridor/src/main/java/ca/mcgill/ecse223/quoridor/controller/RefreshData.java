package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.view.NewJBoard;

public class RefreshData extends Thread{
	public NewJBoard board;
	public RefreshData(NewJBoard board) {
		board = board;
	}
	public void run() {
		while(true) {
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
