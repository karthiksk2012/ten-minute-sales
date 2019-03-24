package com.kconcurrent.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import com.kconcurrent.endpoints.AddSaleResource;
import com.kconcurrent.endpoints.GetSaleDataResource;

public class App extends Application<AppConfiguration> {

	public static void main(final String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public void run(final AppConfiguration configuration,
			final Environment environment) {

		final AddSaleResource transactionResource = new AddSaleResource();
		environment.jersey().register(transactionResource);

		final GetSaleDataResource statisticsResource = new GetSaleDataResource();
		environment.jersey().register(statisticsResource);
	}

}
