package com.kconcurrent.utils;

public final class Utils {

	public static Long timeToSeconds(Long timeInMilliSeconds) {
		return (timeInMilliSeconds / 1000L);
	}

	public static boolean isTimeDifferenceValid(Long timeOld, Long timeNew,
			Integer timeDiff) {
		return ((timeNew - timeOld) < timeDiff);
	}

}
