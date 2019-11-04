package ca.mcgill.ecse223.quoridor.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Pawn extends JPanel{
public enum PawnColor{WHITE,BLACK};
private PawnColor color;
public Pawn (PawnColor color) {
	this.color = color;
}
public void paint(Graphics g) {
	super.paint(g);
	switch(color) {
	case WHITE:
		g.setColor(Color.WHITE);
		g.fillOval(0, 0, 50, 50);

		break;
	case BLACK:
		g.setColor(Color.BLACK);
		g.fillOval(0, 0, 50, 50);
		break;
	}
}
}
