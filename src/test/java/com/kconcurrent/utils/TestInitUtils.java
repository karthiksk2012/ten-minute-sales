package com.kconcurrent.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kconcurrent.utils.ConfigConstants;
import com.kconcurrent.utils.InitUtils;

public class TestInitUtils {

	@Test
	public void testInitializeTransactionMapSize() {
		assertEquals(
				new Integer(
						ConfigConstants.CONST_MAX_DURATION_SALE_RECORDS),
				new Integer(InitUtils.initializeSaleMap().size()));
	}

}
