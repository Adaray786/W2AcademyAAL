package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.DeliveryEmployeeDoesNotExistException;
import org.kainos.ea.client.FailedToCreateDeliveryEmployeeException;
import org.kainos.ea.client.FailedToGetDeliveryEmployeesException;
import org.kainos.ea.client.InvalidDeliveryEmployeeException;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {

    private final DeliveryEmployeeDao deliveryEmployeeDao = new DeliveryEmployeeDao();
    private final DeliveryEmployeeValidator deliveryEmployeeValidator = new DeliveryEmployeeValidator();

    public List<DeliveryEmployee> getDeliveryEmployees() throws FailedToGetDeliveryEmployeesException {
        try {
            return deliveryEmployeeDao.getDeliveryEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeesException();
        }
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws FailedToGetDeliveryEmployeesException, DeliveryEmployeeDoesNotExistException {
        try {
            DeliveryEmployee deliveryEmployee = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if (deliveryEmployee == null) {
                throw new DeliveryEmployeeDoesNotExistException(id);
            }

            return deliveryEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeesException();
        }
    }

    public List<DeliveryEmployee> createDeliveryEmployee(DeliveryEmployeeRequest request) throws FailedToCreateDeliveryEmployeeException, InvalidDeliveryEmployeeException {
        try {
            String validation = deliveryEmployeeValidator.isValidDeliveryEmployee(request);

            if (validation != null) {
                throw new InvalidDeliveryEmployeeException(validation);
            }

            int id = deliveryEmployeeDao.createDeliveryEmployee(request);

            if (id == -1) {
                throw new FailedToCreateDeliveryEmployeeException();
            }

            return deliveryEmployeeDao.getDeliveryEmployees();
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToCreateDeliveryEmployeeException();
        }
    }

}
