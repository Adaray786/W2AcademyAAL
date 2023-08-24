package org.kainos.ea.db;

import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.client.FailedToAssignDeliveryEmployeesException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryEmployeesProjectsDao {

    public void assignDeliveryEmployees(AssignDeliveryEmployeesRequest request) throws FailedToAssignDeliveryEmployeesException {

        try (Connection conn = DatabaseConnector.getConnection()) {
            String insertString = "INSERT INTO DeliveryEmployees_Projects VALUES (?, ?)";

            for (Integer employeeId : request.getEmployeeIds()) {
                PreparedStatement stmt = conn.prepareStatement(insertString);
                stmt.setInt(1, employeeId);
                stmt.setInt(2, request.getProjectId());
                stmt.execute();
            }


        } catch (SQLException e) {
            throw new FailedToAssignDeliveryEmployeesException();
        }
    }
}
