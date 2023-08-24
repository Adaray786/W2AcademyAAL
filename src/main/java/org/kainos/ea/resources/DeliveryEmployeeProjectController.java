package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeesProjectsService;
import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeProjectValidator;
import org.kainos.ea.db.DeliveryEmployeesProjectsDao;
import org.kainos.ea.db.ProjectDao;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Api("Delivery Employee Project Assignment API")
public class DeliveryEmployeeProjectController {

    ProjectDao projectDao = new ProjectDao();
    DeliveryEmployeeDao deliveryEmployeeDao = new DeliveryEmployeeDao();
    DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao = new DeliveryEmployeesProjectsDao();
    DeliveryEmployeeProjectValidator deliveryEmployeeProjectValidator = new DeliveryEmployeeProjectValidator(projectDao, deliveryEmployeeDao, deliveryEmployeesProjectsDao);
    DeliveryEmployeesProjectsService deliveryEmployeesProjectsService = new DeliveryEmployeesProjectsService(
            deliveryEmployeesProjectsDao, deliveryEmployeeProjectValidator);

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
