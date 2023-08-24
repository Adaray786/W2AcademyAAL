package org.kainos.ea.core;

import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.cli.DeliveryEmployeeProject;
import org.kainos.ea.client.FailedToGetDeliveryEmployeeProjectException;
import org.kainos.ea.client.FailedToGetProjectException;
import org.kainos.ea.client.ProjectDoesNotExistException;
import org.kainos.ea.db.DeliveryEmployeesProjectsDao;
import org.kainos.ea.db.ProjectDao;

public class DeliveryEmployeeProjectValidator {

    private ProjectDao projectDao;
    private DeliveryEmployeeDao deliveryEmployeeDao;
    private DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao;

    public DeliveryEmployeeProjectValidator(ProjectDao projectDao, DeliveryEmployeeDao deliveryEmployeeDao, DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao) {
        this.projectDao = projectDao;
        this.deliveryEmployeeDao = deliveryEmployeeDao;
        this.deliveryEmployeesProjectsDao = deliveryEmployeesProjectsDao;
    }

    public String validateAssignments(AssignDeliveryEmployeesRequest request) throws ProjectDoesNotExistException,
            FailedToGetProjectException, FailedToGetDeliveryEmployeeProjectException,
            FailedToGetDeliveryEmployeeException {

        if (projectDao.getProjectById(request.getProjectId()) == null) {
            throw new ProjectDoesNotExistException();
        }


        for (Integer deliveryEmployeeId : request.getEmployeeIds()) {
            if (deliveryEmployeeDao.getDeliveryEmployeeById == null) {
                throw new DeliveryEmployeeDoesNotExistException();
            }

            if (deliveryEmployeesProjectsDao.getDeliveryEmployeeProjectById(deliveryEmployeeId,
                    request.getProjectId()) != null) {
                return "One of the employees is already assigned to the project";
            }
        }



        return null;
    }
}
