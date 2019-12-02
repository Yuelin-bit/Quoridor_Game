package ca.mcgill.ecse223.quoridor.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Wall;
import ca.mcgill.ecse223.quoridor.view.JTile;
import ca.mcgill.ecse223.quoridor.view.JWall;

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
		
		
		List<Wall> b = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
		List<Wall> w = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
		ArrayList<ReplayWallTO> bGUI = new ArrayList<ReplayWallTO>();
		ArrayList<ReplayWallTO> wGUI = new ArrayList<ReplayWallTO>();
		for(int i=0; i<b.size(); i++) {
			boolean isVertical;
			int rowSmall;
			int columnSmall;
			if(b.get(i).getMove().getWallDirection().equals(Direction.Horizontal)) {
				isVertical = false;
			}else {
				isVertical = true;
			}
			rowSmall = b.get(i).getMove().getTargetTile().getRow();
			columnSmall = b.get(i).getMove().getTargetTile().getColumn();
			ReplayWallTO t = new ReplayWallTO(isVertical, rowSmall, columnSmall);
			bGUI.add(t);
		}
		for(int i=0; i<w.size(); i++) {
			boolean isVertical;
			int rowSmall;
			int columnSmall;
			if(w.get(i).getMove().getWallDirection().equals(Direction.Horizontal)) {
				isVertical = false;
			}else {
				isVertical = true;
			}
			rowSmall = w.get(i).getMove().getTargetTile().getRow();
			columnSmall = w.get(i).getMove().getTargetTile().getColumn();
			ReplayWallTO t = new ReplayWallTO(isVertical, rowSmall, columnSmall);
			wGUI.add(t);
		}
		
		JWall[] ww = QuoridorApplication.getLoadPosition().getReplayBoard().getJBlackWallInStock();
		for(int i=0;i<10;i++) {
			ww[i].setVisible(false);
		}
		for(int i=0; i<bGUI.size(); i++) {
			QuoridorApplication.getLoadPosition().getReplayBoard().getMainLayerPanel().add(ww[i]);
			ww[i].setBackground(Color.BLUE);
			setLocation(ww[i], bGUI.get(i).isVertical(), bGUI.get(i).getRowSmall(), bGUI.get(i).getColumnSmall());
			ww[i].setVisible(true);	
		}
		QuoridorApplication.getLoadPosition().getReplayBoard().setJBlackWallInStock(ww);
		
		JWall[] ww2 = QuoridorApplication.getLoadPosition().getReplayBoard().getJWhiteWallInStock();
		for(int i=0;i<10;i++) {
			ww2[i].setVisible(false);
		}
		for(int i=0; i<wGUI.size(); i++) {
			QuoridorApplication.getLoadPosition().getReplayBoard().getMainLayerPanel().add(ww2[i]);
			ww2[i].setBackground(Color.BLUE);
			setLocation(ww2[i], wGUI.get(i).isVertical(), wGUI.get(i).getRowSmall(), wGUI.get(i).getColumnSmall());
			ww2[i].setVisible(true);	
		}
		QuoridorApplication.getLoadPosition().getReplayBoard().setJWhiteWallInStock(ww2);
		
		//QuoridorApplication.getLoadPosition().getReplayBoard().getWhiteStock().setText(QuoridorApplication.getLoadPosition().getReplayBoard().transferInt(10));
		//QuoridorApplication.getLoadPosition().getReplayBoard().getBlackStock().setText(QuoridorApplication.getLoadPosition().getReplayBoard().transferInt(10));
		
		QuoridorApplication.getLoadPosition().getReplayBoard().getWhiteStock().setText(QuoridorApplication.getLoadPosition().getReplayBoard().transferInt(10-QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().size()));
		QuoridorApplication.getLoadPosition().getReplayBoard().getBlackStock().setText(QuoridorApplication.getLoadPosition().getReplayBoard().transferInt(10-QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().size()));
	}
	
	private static void setLocation(JWall a, boolean isV, int row, int column) {
		int acX = 0;
		int acY = 0;
		int width = 0;
		int height = 0;
		if(isV==true) {
			acX = 17 + column * 56;
			acY = 138 + row * 56;
			width = 9;
			height = 103;
		}else {
			acX = column * 56 - 30;
			acY = 185 + row * 56;
			width = 103;
			height = 9;
		}
		a.setBounds(acX, acY, width, height);
	}

}
	
	
	
	


