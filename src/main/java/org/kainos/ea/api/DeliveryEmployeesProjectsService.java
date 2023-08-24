package org.kainos.ea.api;

import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.client.FailedToAssignDeliveryEmployeesException;
import org.kainos.ea.client.InvalidAssignDeliveryEmployeesRequestException;
import org.kainos.ea.core.DeliveryEmployeeProjectValidator;
import org.kainos.ea.db.DeliveryEmployeesProjectsDao;

public class DeliveryEmployeesProjectsService {

    private final DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao = new DeliveryEmployeesProjectsDao();
    private final DeliveryEmployeeProjectValidator deliveryEmployeeProjectValidator = new DeliveryEmployeeProjectValidator();

    public void assignDeliveryEmployees(AssignDeliveryEmployeesRequest request) throws FailedToAssignDeliveryEmployeesException, InvalidAssignDeliveryEmployeesRequestException {

        String invalidCause = deliveryEmployeeProjectValidator.validateAssignments(request);
        if (invalidCause != null) {
            throw new InvalidAssignDeliveryEmployeesRequestException(invalidCause);
        }

        deliveryEmployeesProjectsDao.assignDeliveryEmployees(request);
    }
}
