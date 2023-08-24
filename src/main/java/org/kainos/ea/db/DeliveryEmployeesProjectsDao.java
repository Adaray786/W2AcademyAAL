package org.kainos.ea.db;

import org.kainos.ea.cli.AssignDeliveryEmployeeRequest;
import org.kainos.ea.client.FailedToAssignDeliveryEmployeeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryEmployeesProjectsDao {

    public void assignDeliveryEmployee(AssignDeliveryEmployeeRequest request) throws FailedToAssignDeliveryEmployeeException {

        try (Connection conn = DatabaseConnector.getConnection()) {
            String insertString = "INSERT INTO DeliveryEmployees_Projects VALUES (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(insertString);
            stmt.setInt(1, request.getEmployeeId());
            stmt.setInt(2, request.getProjectId());
            stmt.execute();

        } catch (SQLException e) {
            throw new FailedToAssignDeliveryEmployeeException();
        }
    }
}
