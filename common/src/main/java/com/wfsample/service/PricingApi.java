package com.wfsample.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * API interface for beachshirt pricing service.
 *
 * @author Pontus Rydin 
 */
@Path("/pricing")
@Produces(MediaType.APPLICATION_JSON)
public interface PricingApi {

  @GET
  @Path("price/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Double getPrice(@PathParam("id") String id, @QueryParam("quantity") int quantity,
    @Context HttpHeaders httpHeaders);
}