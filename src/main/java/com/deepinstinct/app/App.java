package com.deepinstinct.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * finds which guard is most likely to fall asleep (the guard that has slept
 * most minutes in total) and for that guard, which minute does he most often is
 * asleep in.
 *
 */
public class App {

	final static String GUARD_STR = "Guard";
	final static String BEGINS_STR = "begins";

	public static void main(String[] args) {

		String filePath = "src\\main\\ReportFile.txt";

		Guard guardSleepMore = WhichGuardSleepMore(filePath);

		CheckHowIsMoreSleppAndWhichHour(guardSleepMore);

	}

	public static String CheckHowIsMoreSleppAndWhichHour(Guard guardSleepMore) {
		if (guardSleepMore == null) {
			System.out.print("they are equal");
			return null;
		}

		System.out.print(guardSleepMore.getName());

		System.out.print("is most likely to be asleep in 00:" + guardSleepMore.WhichHourMostLikelySleep());

		return guardSleepMore.getName() + "is most likely to be asleep in 00:"
				+ guardSleepMore.WhichHourMostLikelySleep();
	}

	public static Guard WhichGuardSleepMore(String filePath) {

		Guard guard1 = new Guard();
		Guard guard2 = new Guard();

		BufferedReader reader;

		try {

			File file = new File(filePath);
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			// get the name of the guard
			String guardName = line.substring(line.indexOf(GUARD_STR), line.indexOf(BEGINS_STR));
			guard1.setName(guardName);

			while (guardName != null) {

				// if guardName equal to guard1 so we will add the sum of the minutes to guard 1
				if (guardName.equals(guard1.getName()))
					guardName = SumAndAddSleepMinutesToGuardInOneShift(guard1, reader);
				else {
					// if guardName equal to guard2 so we will add the sum of the minutes to guard 2
					guard2.setName(guardName);
					guardName = SumAndAddSleepMinutesToGuardInOneShift(guard2, reader);
				}

			}

			// print the guard that sleep more time
			if (guard1.getMintSum() > guard2.getMintSum()) {
				return guard1;
			} else if (guard1.getMintSum() < guard2.getMintSum()) {
				return guard2;
			}

			else
				return null;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * this function sum the sleep minutes of the guard and add them to its field
	 * mintSum.
	 * 
	 * it return the name of the next guard, and null if it the end of the file.
	 * 
	 * @param guard
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static String SumAndAddSleepMinutesToGuardInOneShift(Guard guard, BufferedReader reader) throws IOException {

		String lineFallsAsleep = "";
		String lineWakesUp = "";
		int timeOfSleep;
		int timeOfWakeup;

		// will stop when we get the shift of the other guard or until null
		while (true) {

			// read the falls asleep line
			lineFallsAsleep = reader.readLine();
			// if it null or it contain guard so it mean we are in the next shift of the
			// other guards so we will return to the while
			if (lineFallsAsleep == null)
				return null;
			if (lineFallsAsleep.contains(GUARD_STR))
				return lineFallsAsleep.substring(lineFallsAsleep.indexOf(GUARD_STR),
						lineFallsAsleep.indexOf(BEGINS_STR));
			// take the minutes time of sleep
			timeOfSleep = Integer.parseInt(lineFallsAsleep.substring(15, 17));

			// read the wakes up line
			lineWakesUp = reader.readLine();
			// take the minutes time of Wake up
			timeOfWakeup = Integer.parseInt(lineWakesUp.substring(15, 17));

			// add time of sleep minute to FallsAsleepminute (hashmap) if it does'nt exist
			// add it
			guard.AddTimeOfSleepMinuteToFallsAsleepMinuteHashmapInThisRound(timeOfSleep);

			// now loop from timeofsleep until timeofwakeup-1 in the arrayOfMinutes and add
			// to them +1
			guard.UpdateMinutesInArrayOfMinutesThatTheGuardSleepInThisRound(timeOfSleep, timeOfWakeup);

			// calculate how much time sleep and add it to guard.setMintSum
			int num = timeOfWakeup - timeOfSleep;
			guard.setMintSum(guard.getMintSum() + num);

		}
	}

}
