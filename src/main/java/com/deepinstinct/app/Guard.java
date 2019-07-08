package com.deepinstinct.app;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Guard {
	private String name;
	private int mintSum = 0;

	// contain the minutes of falls asleep and how many times he sleep in the this
	// minute
	private HashMap<Integer, Integer> FallsAsleepminute = new HashMap<Integer, Integer>();

	// contain how much time sleep in minutes from 0 to 59
	private int[] arrayOfMinutes = new int[60];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMintSum() {
		return mintSum;
	}

	public void setMintSum(int minSum) {
		this.mintSum = minSum;
	}

	public HashMap<Integer, Integer> getFallsAsleepminute() {
		return FallsAsleepminute;
	}

	public void setFallsAsleepminute(HashMap<Integer, Integer> fallsAsleepminute) {
		FallsAsleepminute = fallsAsleepminute;
	}

	public int[] getArrayOfMinutes() {
		return arrayOfMinutes;
	}

	public void setArrayOfMinutes(int[] arrayOfMinutes) {
		this.arrayOfMinutes = arrayOfMinutes;
	}

	/**
	 * add time of sleep minute to FallsAsleepminute (hashmap) if it does'nt exist
	 * add it
	 * 
	 * @param timeOfSleep
	 */
	public void AddTimeOfSleepMinuteToFallsAsleepMinuteHashmapInThisRound(int timeOfSleep) {
		if (!this.getFallsAsleepminute().containsKey(timeOfSleep)) {
			this.getFallsAsleepminute().put(timeOfSleep, 0);
		}
	}

	/**
	 * now loop from timeofsleep until timeofwakeup-1 in the arrayOfMinutes and add
	 * to them +1
	 * 
	 * @param timeOfSleep
	 */
	public void UpdateMinutesInArrayOfMinutesThatTheGuardSleepInThisRound(int timeOfSleep, int timeOfWakeup) {
		for (int i = timeOfSleep; i < timeOfWakeup; i++) {

			this.getArrayOfMinutes()[i] += 1;
			if (this.getFallsAsleepminute().containsKey(i)) {
				this.getFallsAsleepminute().put(i, this.getArrayOfMinutes()[i]);
			}

		}
	}

	/**
	 * this func return the minute that the guard sleep more time that the other
	 * minutes
	 * 
	 * @return
	 */
	public int WhichHourMostLikelySleep() {

		int maxMint = 0;
		int maxVal = 0;

		for (Map.Entry element : this.getFallsAsleepminute().entrySet()) {

			if ((int) element.getValue() > maxVal) {
				maxVal = (int) element.getValue();
				maxMint = (int) element.getKey();
			}

		}

		return maxMint;

	}
}
