package org.kainos.ea.api;


import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.SalesEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private SalesEmployeeDao salesEmployeeDao = new SalesEmployeeDao();
    private SalesEmployeeValidator salesEmployeeValidator = new SalesEmployeeValidator();
    public List<SalesEmployee> getAllSalesEmployees() throws FailedToGetSalesEmployeesException {
        List<SalesEmployee> salesEmployeeList = null;
        try {
            salesEmployeeList = salesEmployeeDao.getAllSalesEmployees();
        } catch (SQLException e) {
            throw new FailedToGetSalesEmployeesException();
        }
        return salesEmployeeList;
    }

    public SalesEmployee getSalesEmployeeById(int id) throws FailedToGetSalesEmployeeException, SalesEmployeeDoesNotExistException {
        try {
            SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);

            if (salesEmployee == null) {
                throw new SalesEmployeeDoesNotExistException();
            }

            return salesEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetSalesEmployeeException();
        }
    }

    public int createSalesEmployee(SalesEmployeeRequest salesEmployeeRequest) throws FailedToCreateSalesEmployeeException, InvalidSalesEmployeeException {
        try {
            String validation = salesEmployeeValidator.isValidSalesEmployee(salesEmployeeRequest);

                if (validation != null) {
                    throw new InvalidSalesEmployeeException(validation);
                }
                int id = salesEmployeeDao.createSalesEmployee(salesEmployeeRequest);

                if (id == -1) {
                    throw new FailedToCreateSalesEmployeeException();
                }
                return id;

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateSalesEmployeeException();
        }
    }
}
