package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<DeliveryEmployee> getDeliveryEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();

        String query = "SELECT EmployeeID, Name, Salary, BankAccountNumber, NationalInsuranceNumber " +
                "FROM DeliveryEmployees " +
                "LEFT JOIN Employees USING (EmployeeID)";

        PreparedStatement st = c.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        List<DeliveryEmployee> deliveryEmployees = new ArrayList<>();

        while (rs.next()) {
            deliveryEmployees.add(new DeliveryEmployee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }

        return deliveryEmployees;
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest request) throws SQLException {
        Connection c = databaseConnector.getConnection();

        try {
            c.setAutoCommit(false);

            String insertEmployeesQuery = "INSERT INTO Employees (Name, Salary, BankAccountNumber, NationalInsuranceNumber) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement insertEmployees = c.prepareStatement(insertEmployeesQuery, Statement.RETURN_GENERATED_KEYS);

            insertEmployees.setString(1, request.getName());
            insertEmployees.setDouble(2, request.getSalary());
            insertEmployees.setString(3, request.getBankAccountNumber());
            insertEmployees.setString(4, request.getNiNumber());

            insertEmployees.executeUpdate();

            ResultSet employeeResult = insertEmployees.getGeneratedKeys();

            int insertedId;
            if (employeeResult.next()) {
                insertedId = employeeResult.getInt(1);
            } else {
                c.rollback();
                return -1;
            }

            String insertDeliveryEmployeesQuery = "INSERT INTO DeliveryEmployees (EmployeeID) " +
                    "VALUES (?)";

            PreparedStatement insertDeliveryEmployees = c.prepareStatement(insertDeliveryEmployeesQuery, Statement.RETURN_GENERATED_KEYS);

            insertDeliveryEmployees.setInt(1, insertedId);

            insertDeliveryEmployees.executeUpdate();

            ResultSet rs = insertEmployees.getGeneratedKeys();

            if (rs.next()) {
                c.commit();
                return insertedId;
            }

            c.rollback();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
        return -1;
    }
}
