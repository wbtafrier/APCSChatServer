package com.compsci.command;

import java.util.Timer;
import java.util.TimerTask;

import com.compsci.core.ConnectionManager;
import com.compsci.core.SloverseServer;

public class CountdownThread extends Thread {

	private static boolean isCounting;
	private static final int HOURS_IN_SECONDS = 3600;
	private static final int MINUTES_IN_SECONDS = 60;
	private static final int MAX_SECONDS = 31536000;
	private static final int PAUSE = 1000;
	
	private Timer countdownTimer;
	private int countdownTime;
	
	private int timeLeftHours;
	private int timeLeftMinutes;
	private int timeLeftSeconds;
	
	private int interval;
	private int intervalCounter;
	
	public CountdownThread(int timeInSeconds) {
		super("Countdown Thread");
		
		//31536000 is the amount of seconds in a year.
		if (timeInSeconds > MAX_SECONDS) {
			ConnectionManager.sendErrorMessage("SYSTEM", "ERROR: You cannot start a countdown timer longer than 1 year!");
			return;
		}
		countdownTime = timeInSeconds;
	}
	
	private void startCountdown() {
		if (isCounting) {
			ConnectionManager.sendErrorMessage("SYSTEM", "ERROR: A countdown is already going on!");
			return;
		}
		isCounting = true;
		countdownTimer = new Timer();
		countdownTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Seconds left: " + countdownTime--);
				intervalCounter++;
				
				if (intervalCounter >= interval) {
					intervalCounter = 0;
					updateTime(countdownTime);
					ConnectionManager.sendBroadcast("BROADCAST", "Server is shutting down in " + timeLeftHours + ":" + timeLeftMinutes + ":" + timeLeftSeconds);
				}
				
				if (countdownTime < 0) {
					SloverseServer.closeServer();
					countdownTimer.cancel();
				}
			}
			
		}, 0, PAUSE);
		
		
	}

	public void stopCountdown() {
		if (!isCounting) {
			ConnectionManager.sendErrorMessage("SYSTEM", "ERROR: No countdown is running at this time.");
			return;
		}
		isCounting = false;
		countdownTimer.cancel();
	}
	
	@Override
	public void run() {
		startCountdown();
	}
	
	private void updateTime(int seconds) {
		timeLeftHours = seconds / HOURS_IN_SECONDS;
		seconds -= (HOURS_IN_SECONDS * timeLeftHours);
		
		timeLeftMinutes = seconds / MINUTES_IN_SECONDS;
		seconds -= (MINUTES_IN_SECONDS * timeLeftMinutes);
		
		timeLeftSeconds = seconds;
	}
}
