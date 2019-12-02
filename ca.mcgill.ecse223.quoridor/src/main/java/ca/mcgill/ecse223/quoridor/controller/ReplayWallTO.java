package ca.mcgill.ecse223.quoridor.controller;

/**
 * 
 * 
 * @author EveryOne
 * @version inner class to get data of jwalls
 * @exception nothing
 *
 */
public class ReplayWallTO{
	private boolean isVertical;
	private int rowSmall;
	private int columnSmall;
	public ReplayWallTO(boolean isVertical, int rowSmall, int columnSmall){
		this.setColumnSmall(columnSmall);
		this.setRowSmall(rowSmall);
		this.setVertical(isVertical);
	}
	public boolean isVertical() {
		return isVertical;
	}
	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	public int getRowSmall() {
		return rowSmall;
	}
	public void setRowSmall(int rowSmall) {
		this.rowSmall = rowSmall;
	}
	public int getColumnSmall() {
		return columnSmall;
	}
	public void setColumnSmall(int columnSmall) {
		this.columnSmall = columnSmall;
	}
	

}