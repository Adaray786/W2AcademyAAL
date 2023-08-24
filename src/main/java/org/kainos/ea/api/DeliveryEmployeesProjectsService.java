package org.kainos.ea.api;

import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.client.FailedToAssignDeliveryEmployeesException;
import org.kainos.ea.db.DeliveryEmployeesProjectsDao;

public class DeliveryEmployeesProjectsService {

    private DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao = new DeliveryEmployeesProjectsDao();


    public void assignDeliveryEmployees(AssignDeliveryEmployeesRequest request) throws FailedToAssignDeliveryEmployeesException {

        deliveryEmployeesProjectsDao.assignDeliveryEmployees(request);
    }
}
