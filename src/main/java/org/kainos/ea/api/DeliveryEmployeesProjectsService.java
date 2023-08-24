package org.kainos.ea.api;

import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeProjectValidator;
import org.kainos.ea.db.DeliveryEmployeesProjectsDao;

public class DeliveryEmployeesProjectsService {

    private final DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao;
    private final DeliveryEmployeeProjectValidator deliveryEmployeeProjectValidator;

    public DeliveryEmployeesProjectsService(DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao, DeliveryEmployeeProjectValidator deliveryEmployeeProjectValidator) {
        this.deliveryEmployeesProjectsDao = deliveryEmployeesProjectsDao;
        this.deliveryEmployeeProjectValidator = deliveryEmployeeProjectValidator;
    }

    public void assignDeliveryEmployees(AssignDeliveryEmployeesRequest request) throws FailedToAssignDeliveryEmployeesException, InvalidAssignDeliveryEmployeesRequestException, FailedToGetProjectException, ProjectDoesNotExistException, FailedToGetDeliveryEmployeeProjectException {

        String invalidCause = deliveryEmployeeProjectValidator.validateAssignments(request);
        if (invalidCause != null) {
            throw new InvalidAssignDeliveryEmployeesRequestException(invalidCause);
        }

        deliveryEmployeesProjectsDao.assignDeliveryEmployees(request);
    }
}
