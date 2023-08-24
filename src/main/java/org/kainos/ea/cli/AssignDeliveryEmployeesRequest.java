package org.kainos.ea.cli;

import java.util.List;

public class AssignDeliveryEmployeesRequest {

    private List<Integer> employeeIds;
    private int projectId;

    public AssignDeliveryEmployeesRequest(List<Integer> employeeId, int projectId) {
        this.employeeIds = employeeId;
        this.projectId = projectId;
    }

    public List<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Integer> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
