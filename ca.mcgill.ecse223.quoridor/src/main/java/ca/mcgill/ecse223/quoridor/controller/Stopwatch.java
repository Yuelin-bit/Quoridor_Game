package ca.mcgill.ecse223.quoridor.controller;

import java.sql.Time;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.User;
import java.time.Duration;


public class Stopwatch extends Thread{
	public int minute;
	public int second;
	public int total;
	public boolean whiteTimeIsEnd = false, blackTimeIsEnd = false;
	private long startTime=0;
	private long stopTime;
	private long whiteTimeLeft,blackTimeLeft;
	public int whiteTimeLeftInSec = 999, blackTimeLeftInSec= 9999;
	private final int numConversion = 1000;
	private int totalInMilli;
	private Player white,black;
	private Time whiteTimeRemaining,blackTimeRemaining;
	private int whiteCurrentTime,blackCurrentTime = 0;
	private boolean whiteRunning = true ,blackRunning = false;

	@SuppressWarnings("deprecation")
	public Stopwatch(Player player1,Player player2) {
		this.white = player1;
		this.black = player2;
		this.minute = this.white.getRemainingTime().getMinutes();
		this.second = this.white.getRemainingTime().getSeconds();
		this.total = minute*60 + second;
		this.totalInMilli = total * numConversion;
	}
	
	public void run() {
		while(whiteCurrentTime<=totalInMilli||blackCurrentTime<=totalInMilli) {
			if(whiteRunning&&!whiteTimeIsEnd) {	
//				System.out.println("total"+totalInMilli);
//				System.out.println("currentTime"+currentTime);
				whiteCurrentTime+=1000;
				whiteTimeLeft = totalInMilli - whiteCurrentTime;
				whiteTimeRemaining = new Time(whiteTimeLeft);
				white.setRemainingTime(whiteTimeRemaining);
				whiteTimeLeftInSec = (int) (whiteTimeLeft/numConversion);
				whiteTimeIsEnd = whiteTimeLeft>0?false:true;
				try {
					Thread.sleep(980);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}else if(blackRunning&&!blackTimeIsEnd) {
			
				blackCurrentTime+=1000;
				blackTimeLeft = totalInMilli - blackCurrentTime;
				blackTimeRemaining = new Time(blackTimeLeft);
				black.setRemainingTime(blackTimeRemaining);
				blackTimeLeftInSec = (int) (blackTimeLeft/numConversion);
				blackTimeIsEnd = blackTimeLeft>0?false:true;

				try {
					Thread.sleep(980);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		// set game state to <other player> won
	}

	public int getWhiteTimeLeftInSec() {
		return whiteTimeLeftInSec;
	}

	public int getBlackTimeLeftInSec() {
		return blackTimeLeftInSec;
	}

	public void setWhiteTimeLeftInSec(int whiteTimeLeftInSec) {
		this.whiteTimeLeftInSec = whiteTimeLeftInSec;
	}

	public void setBlackTimeLeftInSec(int blackTimeLeftInSec) {
		this.blackTimeLeftInSec = blackTimeLeftInSec;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public int getTotal() {
		return total;
	}

	public boolean isWhiteTimeIsEnd() {
		return whiteTimeIsEnd;
	}

	public boolean isBlackTimeIsEnd() {
		return blackTimeIsEnd;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getStopTime() {
		return stopTime;
	}

	public long getWhiteTimeLeft() {
		return whiteTimeLeft;
	}

	public long getBlackTimeLeft() {
		return blackTimeLeft;
	}



	public int getNumConversion() {
		return numConversion;
	}

	public int getTotalInMilli() {
		return totalInMilli;
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}

	public Time getWhiteTimeRemaining() {
		return whiteTimeRemaining;
	}

	public Time getBlackTimeRemaining() {
		return blackTimeRemaining;
	}

	public int getWhiteCurrentTime() {
		return whiteCurrentTime;
	}

	public int getBlackCurrentTime() {
		return blackCurrentTime;
	}

	public boolean isWhiteRunning() {
		return whiteRunning;
	}

	public boolean isBlackRunning() {
		return blackRunning;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setWhiteTimeIsEnd(boolean whiteTimeIsEnd) {
		this.whiteTimeIsEnd = whiteTimeIsEnd;
	}

	public void setBlackTimeIsEnd(boolean blackTimeIsEnd) {
		this.blackTimeIsEnd = blackTimeIsEnd;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}

	public void setWhiteTimeLeft(long whiteTimeLeft) {
		this.whiteTimeLeft = whiteTimeLeft;
	}

	public void setBlackTimeLeft(long blackTimeLeft) {
		this.blackTimeLeft = blackTimeLeft;
	}



	public void setTotalInMilli(int totalInMilli) {
		this.totalInMilli = totalInMilli;
	}

	public void setWhite(Player white) {
		this.white = white;
	}

	public void setBlack(Player black) {
		this.black = black;
	}

	public void setWhiteTimeRemaining(Time whiteTimeRemaining) {
		this.whiteTimeRemaining = whiteTimeRemaining;
	}

	public void setBlackTimeRemaining(Time blackTimeRemaining) {
		this.blackTimeRemaining = blackTimeRemaining;
	}

	public void setWhiteCurrentTime(int whiteCurrentTime) {
		this.whiteCurrentTime = whiteCurrentTime;
	}

	public void setBlackCurrentTime(int blackCurrentTime) {
		this.blackCurrentTime = blackCurrentTime;
	}

	public void setWhiteRunning(boolean whiteRunning) {
		this.whiteRunning = whiteRunning;
	}

	public void setBlackRunning(boolean blackRunning) {
		this.blackRunning = blackRunning;
	}
	

}