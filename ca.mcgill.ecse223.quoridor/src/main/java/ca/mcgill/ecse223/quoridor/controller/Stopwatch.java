package ca.mcgill.ecse223.quoridor.controller;

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

	public Stopwatch(int minute, int second) {
		this.minute = minute;
		this.second = second;
		this.total = minute*60 + second;
		this.totalInMilli = total * numConversion;
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
	public static void main (String [] args) {
//		Stopwatch stopwatch = new Stopwatch(0,10);
//		stopwatch.start();
//		int index = 0;
//		while(!stopwatch.timeIsEnd) {
//			if(stopwatch.timeLeftInSec == 7) {
//					stopwatch.suspend();
//				
//				index++;
//				System.out.println(index);
//
//			}
//			if(index==2) {
//				stopwatch.resume();
//				System.out.println("resume");
//
//			}
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("total:"+stopwatch.timeLeftInSec);		
//		}
//		System.out.println(stopwatch.timeIsEnd);
//
		
	}

}
