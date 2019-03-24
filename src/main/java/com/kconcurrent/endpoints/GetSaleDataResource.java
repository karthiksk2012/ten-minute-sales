package com.kconcurrent.endpoints;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import com.kconcurrent.dto.SaleData;
import com.kconcurrent.dto.SalePerSecond;
import com.kconcurrent.utils.ConfigConstants;
import com.kconcurrent.utils.InitUtils;
import com.kconcurrent.utils.PathConstants;
import com.kconcurrent.utils.Utils;

@Path(PathConstants.CONST_DASHBOARD_ENDPOINT)
@Produces(MediaType.APPLICATION_JSON)
public class GetSaleDataResource {

	@GET
	public Response getSalesData() {

		Long currentTimeStamp = Utils.timeToSeconds(System.currentTimeMillis());

		ConcurrentHashMap<Integer, SalePerSecond> recentSalesMap = InitUtils.salePerSecondMap;

		Double sum = 0.0;
		Integer count = 0;
		Double max = 0.0;
		Double min = Double.MAX_VALUE;

		for (Map.Entry<Integer, SalePerSecond> entry : recentSalesMap
				.entrySet()) {
			SalePerSecond transaction = entry.getValue();
			/*
			 * To prevent overlap with the update of the same transaction entry
			 * and to prevent inconsistent output of the aggregate operation,
			 * the entry is synchronized
			 */
			synchronized (transaction) {
				if (Utils
						.isTimeDifferenceValid(
								transaction.getPreviousTimeStamp(),
								currentTimeStamp,
								ConfigConstants.CONST_MAX_DURATION_SALE_RECORDS)) {
					sum += transaction.getTotalSales();
					count += transaction.getNumberOfSales();
					max = Math.max(max, transaction.getMaxSale());
					min = Math.min(min, transaction.getMinSale());
				}
			}
		}

		Double average = sum;
		if (count != 0) {
			average = sum / count;
		}
		if (min.equals(Double.MAX_VALUE)) {
			min = 0.0;
		}

		SaleData response = new SaleData(sum, average, max, min,
				count);

		return Response.status(HttpStatus.OK_200).entity(response).build();

	}

}
