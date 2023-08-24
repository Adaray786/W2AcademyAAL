package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.FailedToCreateDeliveryEmployeeException;
import org.kainos.ea.client.FailedToGetDeliveryEmployeesException;
import org.kainos.ea.client.InvalidDeliveryEmployeeException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Delivery Employee API")
@Path("/api")
public class DeliveryEmployeeController {

    private final DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();

    @GET
    @Path("/deliveryemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployee() {
        try {
            return Response.ok(deliveryEmployeeService.getDeliveryEmployees()).build();
        } catch (FailedToGetDeliveryEmployeesException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/deliveryemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(DeliveryEmployeeRequest request) {
        try {
            return Response.ok(deliveryEmployeeService.createDeliveryEmployee(request)).build();
        } catch (FailedToCreateDeliveryEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (InvalidDeliveryEmployeeException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
