package ca.mcgill.ecse223.quoridor.controller;

import java.sql.Time;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.User;

public class Stopwatch extends Thread{
	public int minute;
	public int second;
	public int total;
	public boolean timeIsEnd = false;
	private long startTime;
	private long stopTime;
	private long timeLeft;
	public int timeLeftInSec;
	private long numConversion = 1000;
	private long totalInMilli;
	private Player player;

	public Stopwatch(Player player) {
		this.minute = player.getRemainingTime().getMinutes();
		this.second = player.getRemainingTime().getSeconds();
		this.total = minute*60 + second;
		this.totalInMilli = total * numConversion;
		this.player = player;
	}
	public void run() {
		startTime = System.currentTimeMillis();
		stopTime = startTime +totalInMilli;
		while(System.currentTimeMillis()<=stopTime) {
			timeLeft = stopTime - System.currentTimeMillis();
			timeLeftInSec = (int) (timeLeft/numConversion);
		}
		timeIsEnd = true;

	}
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}
	/**
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @return the timeIsEnd
	 */
	public boolean isTimeIsEnd() {
		return timeIsEnd;
	}
	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}
	/**
	 * @return the stopTime
	 */
	public long getStopTime() {
		return stopTime;
	}
	/**
	 * @return the timeLeft
	 */
	public long getTimeLeft() {
		return timeLeft;
	}
	/**
	 * @return the timeLeftInSec
	 */
	public int getTimeLeftInSec() {
		return timeLeftInSec;
	}
	/**
	 * @return the numConversion
	 */
	public long getNumConversion() {
		return numConversion;
	}
	/**
	 * @return the totalInMilli
	 */
	public long getTotalInMilli() {
		return totalInMilli;
	}
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(int second) {
		this.second = second;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @param timeIsEnd the timeIsEnd to set
	 */
	public void setTimeIsEnd(boolean timeIsEnd) {
		this.timeIsEnd = timeIsEnd;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	/**
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}
	/**
	 * @param timeLeft the timeLeft to set
	 */
	public void setTimeLeft(long timeLeft) {
		this.timeLeft = timeLeft;
	}
	/**
	 * @param timeLeftInSec the timeLeftInSec to set
	 */
	public void setTimeLeftInSec(int timeLeftInSec) {
		this.timeLeftInSec = timeLeftInSec;
	}
	/**
	 * @param numConversion the numConversion to set
	 */
	public void setNumConversion(long numConversion) {
		this.numConversion = numConversion;
	}
	/**
	 * @param totalInMilli the totalInMilli to set
	 */
	public void setTotalInMilli(long totalInMilli) {
		this.totalInMilli = totalInMilli;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	public static void main (String [] args) {
		User user1 = QuoridorApplication.getQuoridor().addUser("LOL");
		Player player = new Player(new Time(3000), user1, 9, Direction.Horizontal);
		Stopwatch stopwatch = new Stopwatch(player);
		stopwatch.start();
		int index = 0;
		while(!stopwatch.timeIsEnd) {
			if(stopwatch.timeLeftInSec == 7) {
					stopwatch.suspend();
				
				index++;
				System.out.println(index);

			}
			if(index==2) {
				stopwatch.resume();
				System.out.println("resume");

			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("total:"+stopwatch.timeLeftInSec);		
		}
		System.out.println(stopwatch.timeIsEnd);

		
	}

}
