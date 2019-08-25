package com.wfsample.pricing;

import com.wfsample.common.B3HeadersRequestFilter;
import com.wfsample.common.DropwizardServiceConfig;
import com.wfsample.service.PricingApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.util.Random;


/**
 * Sample pricing engine
 *
 * @author Pontus Rydin
 */
public class PricingService extends Application<DropwizardServiceConfig> {
  private static Logger logger = LoggerFactory.getLogger(PricingService.class);
  private B3HeadersRequestFilter b3Filter;
  private Random rnd = new Random();

  private PricingService() {
  }

  public static void main(String[] args) throws Exception {
    new PricingService().run(args);
  }

  @Override
  public void run(DropwizardServiceConfig configuration, Environment environment) {
    environment.jersey().register(new PricingWebResource());
  }

  public class PricingWebResource implements PricingApi {
    public PricingWebResource() {
    }


    @Override
    public Double getPrice(String id, int quantity, HttpHeaders httpHeaders) {
      return (double) quantity * (20 + rnd.nextDouble() * 5);
    }
  }
}