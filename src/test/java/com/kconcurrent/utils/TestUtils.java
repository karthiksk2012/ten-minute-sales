package com.kconcurrent.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.kconcurrent.utils.Utils;

public class TestUtils {

	@Test
	public void testTimeToSeconds() {
		assertEquals(new Long(1530238385L), Utils.timeToSeconds(1530238385718L));
	}

	@Test
	public void testIsTimeDifferenceValid() {
		Long currentTime = Utils.timeToSeconds(System.currentTimeMillis());
		assertTrue("Valid time difference ",
				Utils.isTimeDifferenceValid(currentTime - 59, currentTime, 60));
	}

	@Test
	public void testIsTimeDifferenceNotValid() {
		Long currentTime = Utils.timeToSeconds(System.currentTimeMillis());
		assertFalse("Valid time difference ",
				Utils.isTimeDifferenceValid(currentTime - 65, currentTime, 60));
	}

	@Test
	public void testIsTimeDifferenceValidOnBoundary() {
		Long currentTime = Utils.timeToSeconds(System.currentTimeMillis());
		assertFalse("Valid time difference on boundary ",
				Utils.isTimeDifferenceValid(currentTime - 60, currentTime, 60));
	}

}
