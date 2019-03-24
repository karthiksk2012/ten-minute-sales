package com.kconcurrent.app;

import static org.junit.Assert.assertEquals;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.ClassRule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kconcurrent.app.App;
import com.kconcurrent.app.AppConfiguration;
import com.kconcurrent.dto.SaleData;
import com.kconcurrent.dto.SaleRequest;
import com.kconcurrent.utils.PathConstants;

public class TestApp {

	@ClassRule
	public static final DropwizardAppRule<AppConfiguration> RULE = new DropwizardAppRule<AppConfiguration>(
			App.class, ResourceHelpers.resourceFilePath("config.yml"));

	@Test
	public void testTransactionToStats() throws IOException {

		Client client = new JerseyClientBuilder(RULE.getEnvironment())
				.build("test client");
		client.property(ClientProperties.CONNECT_TIMEOUT, 1000);
		client.property(ClientProperties.READ_TIMEOUT, 3000);
		
		
		int counter = 10;
		Long currentTimeStamp = System.currentTimeMillis();

		while (counter > 0) {

			SaleRequest request = new SaleRequest(
					Double.valueOf(counter), currentTimeStamp - counter);

			Response response = client
					.target(String.format("http://localhost:%d/"
							+ PathConstants.CONST_SALE_ENDPOINT, 8080))
					.request().post(Entity.json(request));

			request = new SaleRequest(Double.valueOf(counter * 2),
					currentTimeStamp - counter);

			response = client
					.target(String.format("http://localhost:%d/"
							+ PathConstants.CONST_SALE_ENDPOINT, 8080))
					.request().post(Entity.json(request));

			assertEquals(new Integer(HttpStatus.CREATED_201), new Integer(
					response.getStatus()));

			counter--;
		}

		Response stats = client
				.target(String.format("http://localhost:%d/"
						+ PathConstants.CONST_DASHBOARD_ENDPOINT, 8080))
				.request().get();

		ObjectMapper mapper = new ObjectMapper();
		String responseAsString = stats.readEntity(String.class);

		SaleData statsresponse = mapper.readValue(responseAsString,
				SaleData.class);
		assertEquals(Double.valueOf(165), statsresponse.getTotalSale());

	}

}
