package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SoniakBewWebApplicationApplication extends Application<SoniakBewWebApplicationConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SoniakBewWebApplicationApplication().run(args);
    }

    @Override
    public String getName() {
        return "SoniakBewWebApplication";
    }

    @Override
    public void initialize(final Bootstrap<SoniakBewWebApplicationConfiguration> bootstrap) {
        // TODO: application initialization

        bootstrap.addBundle(new SwaggerBundle<SoniakBewWebApplicationConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(SoniakBewWebApplicationConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final SoniakBewWebApplicationConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

    }

}