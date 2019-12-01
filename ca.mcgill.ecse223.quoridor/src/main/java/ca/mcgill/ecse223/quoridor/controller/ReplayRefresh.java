package ca.mcgill.ecse223.quoridor.controller;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.view.JTile;

public class ReplayRefresh {
	public static void refreshBoardInReplayMode() {
		int whitePawnRow = -1;
		int whitePawnColumn = -1;
		int blackPawnRow = -1;
		int blackPawnColumn = -1;
		
		JButton[][] allButton = QuoridorApplication.getLoadPosition().getReplayBoard().getTile().getAllButton();
		int currentID = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
//		int lastID = currentID-1;
//		if(lastID<0) lastID = 0;
//		int whitePawnRow0 = QuoridorApplication.getQuoridor().getCurrentGame().getPosition(lastID).getWhitePosition().getTile().getRow();
//		int whitePawnColumn0 = QuoridorApplication.getQuoridor().getCurrentGame().getPosition(lastID).getWhitePosition().getTile().getColumn();
//		int blackPawnRow0 = QuoridorApplication.getQuoridor().getCurrentGame().getPosition(lastID).getBlackPosition().getTile().getRow();
//		int blackPawnColumn0 = QuoridorApplication.getQuoridor().getCurrentGame().getPosition(lastID).getBlackPosition().getTile().getColumn();
		
		whitePawnRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
		whitePawnColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
		blackPawnRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
		blackPawnColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
		
//		allButton[whitePawnRow0][whitePawnColumn0].setIcon(null);
//		allButton[blackPawnRow0][blackPawnColumn0].setIcon(null);
		for(int i=1;i<10;i++) {
			for(int j=1;j<10;j++) {
				allButton[i][j].setIcon(null);
			}
		}
		allButton[whitePawnRow][whitePawnColumn].setIcon(new ImageIcon(JTile.class.getResource("/ca/mcgill/ecse223/quoridor/resources/wpawn.png")));
		allButton[blackPawnRow][blackPawnColumn].setIcon(new ImageIcon(JTile.class.getResource("/ca/mcgill/ecse223/quoridor/resources/bpawn.png")));
		QuoridorApplication.getLoadPosition().getReplayBoard().getTile().setAllButton(allButton);
	}

}
