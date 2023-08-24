package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeesProjectsService;
import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Api("Delivery Employee Project Assignment API")
public class DeliveryEmployeeProjectController {

    DeliveryEmployeesProjectsService deliveryEmployeesProjectsService = new DeliveryEmployeesProjectsService();

    @POST
    @Path("/deliveryEmployeeProject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployeeProjectAssignment(AssignDeliveryEmployeesRequest request) {

        try {
            deliveryEmployeesProjectsService.assignDeliveryEmployees(request);
            return Response.ok().build();

        } catch (InvalidAssignDeliveryEmployeesRequestException | DeliveryEmployeeDoesNotExistException | ProjectDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (FailedToGetDeliveryEmployeeProjectException | FailedToAssignDeliveryEmployeesException | FailedToGetProjectException | FailedToGetDeliveryEmployeeException e) {
            return Response.serverError().build();
        }
    }
}
