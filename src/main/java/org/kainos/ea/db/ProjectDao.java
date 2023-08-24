package org.kainos.ea.db;

import org.kainos.ea.cli.Project;
import org.kainos.ea.client.FailedToGetProjectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDao {

    public Project getProjectById(int id) throws FailedToGetProjectException {

        String queryString = "SELECT ProjectID, Name, Value, TechLead, ClientID, StartDate, EndDate FROM Projects" +
                " WHERE ProjectID = ?";

        try (Connection conn = DatabaseConnector.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(queryString);
            stmt.setInt(1, id);

            ResultSet results = stmt.executeQuery();

            if (!results.next()) return null;

            return new Project(
                    results.getInt("ProjectID"),
                    results.getString("Name"),
                    results.getDouble("Value"),
                    results.getInt("TechLead"),
                    results.getInt("ClientID"),
                    results.getDate("StartDate"),
                    results.getDate("EndDate")
            );

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProjectException();
        }
    }
}
