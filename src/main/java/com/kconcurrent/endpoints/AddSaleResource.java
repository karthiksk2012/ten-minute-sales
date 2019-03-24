package com.kconcurrent.endpoints;

import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import com.kconcurrent.dto.SalePerSecond;
import com.kconcurrent.dto.SaleRequest;
import com.kconcurrent.utils.ConfigConstants;
import com.kconcurrent.utils.InitUtils;
import com.kconcurrent.utils.PathConstants;
import com.kconcurrent.utils.Utils;

@Path(PathConstants.CONST_SALE_ENDPOINT)
@Consumes(MediaType.APPLICATION_JSON)
public class AddSaleResource {

	@POST
	public Response recordSale(@Valid SaleRequest userRequest) {

		Double amount = userRequest.getAmount();
		Long timeInSeconds = Utils.timeToSeconds(userRequest.getTime());
		Long currentTimeStamp = Utils.timeToSeconds(System.currentTimeMillis());

		/*
		 * Check if transaction is older than the time period
		 */
		if (!Utils
				.isTimeDifferenceValid(
						timeInSeconds,
						currentTimeStamp,
						ConfigConstants.CONST_MAX_DURATION_SALE_RECORDS)) {
			return Response.status(HttpStatus.NO_CONTENT_204).build();
		}

		ConcurrentHashMap<Integer, SalePerSecond> recentSaleMap = InitUtils.salePerSecondMap;

		int secondsSlot = (int) (timeInSeconds % ConfigConstants.CONST_MAX_DURATION_SALE_RECORDS);

		/*
		 * Updates the sum, count and other values for the mapped bucket
		 * atomically
		 */
		recentSaleMap.compute(secondsSlot, (mapKey, mapVal) -> {
			Double totalSale = amount;
			Integer numberOfSales = 1;
			Double maxSale = amount;
			Double minSale = amount;
			/*
			 * Checks if the bucket contains expired sales. If not,
			 * updates the values
			 */
			if ((mapVal.getPreviousTimeStamp().equals(timeInSeconds))) {
				totalSale = totalSale + mapVal.getTotalSales();
				numberOfSales = numberOfSales + mapVal.getNumberOfSales();
				maxSale = Math.max(amount, mapVal.getMaxSale());
				minSale = Math.min(amount, mapVal.getMinSale());
			}

			return new SalePerSecond(totalSale, numberOfSales, maxSale,
					minSale, timeInSeconds);

		});

		return Response.status(HttpStatus.CREATED_201).build();

	}

}
