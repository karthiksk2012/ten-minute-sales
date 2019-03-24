package com.kconcurrent.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.kconcurrent.dto.SalePerSecond;

public final class InitUtils {

	public static final ConcurrentHashMap<Integer, SalePerSecond> salePerSecondMap = initializeSaleMap();

	static ConcurrentHashMap<Integer, SalePerSecond> initializeSaleMap() {
		ConcurrentHashMap<Integer, SalePerSecond> saleMap = new ConcurrentHashMap<>(
				ConfigConstants.CONST_MAX_DURATION_SALE_RECORDS);
		int index = 0;
		while (index < ConfigConstants.CONST_MAX_DURATION_SALE_RECORDS) {
			saleMap.put(index++, new SalePerSecond());
		}

		return saleMap;
	}

}
