package com.deepinstinct.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * this test check if the func WhichGuardSleepMore return Guard #10 is most
	 * likely to be asleep in
	 */
	@Test
	public void shouldAnswerTrueIfFuncReturnThatGuard10SleepMoreThanGuard99() {

		String filePath = "src\\main\\ReportFile.txt";
		final String EXPECTED_GUARD_SLEEP_MORE = "Guard #10 ";
		final String MOST_LIKLY_SLEEP_HOUR = "is most likely to be asleep in 00:24";

		assertEquals(EXPECTED_GUARD_SLEEP_MORE, App.WhichGuardSleepMore(filePath).getName());

		assertEquals(EXPECTED_GUARD_SLEEP_MORE + MOST_LIKLY_SLEEP_HOUR,
				App.CheckHowIsMoreSleppAndWhichHour(App.WhichGuardSleepMore(filePath)));

	}

	/**
	 * this test check if the func WhichGuardSleepMore return Guard #99 is most
	 * likely to be asleep in
	 */
	@Test
	public void shouldAnswerTrueIfFuncReturnThatGuard99SleepMoreThanGuard10() {

		String filePath = "src\\main\\ReportFile99.txt";
		final String EXPECTED_GUARD_SLEEP_MORE = "Guard #99 ";
		final String MOST_LIKLY_SLEEP_HOUR = "is most likely to be asleep in 00:25";

		assertEquals(EXPECTED_GUARD_SLEEP_MORE, App.WhichGuardSleepMore(filePath).getName());

		assertEquals(EXPECTED_GUARD_SLEEP_MORE + MOST_LIKLY_SLEEP_HOUR,
				App.CheckHowIsMoreSleppAndWhichHour(App.WhichGuardSleepMore(filePath)));

	}
}
