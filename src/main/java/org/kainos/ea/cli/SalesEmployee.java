package org.kainos.ea.cli;

public class SalesEmployee extends Employee{
    public SalesEmployee(int employeeID, double salary, String bankAccountNumber, String NINumber, float comissionRate, int salesEmployeeID) {
        super(employeeID, salary, bankAccountNumber, NINumber);
        setComissionRate(comissionRate);
        setSalesEmployeeID(salesEmployeeID);
    }
    private float comissionRate;

    private int salesEmployeeID;

    public int getSalesEmployeeID() {
        return salesEmployeeID;
    }

    public void setSalesEmployeeID(int salesEmployeeID) {
        this.salesEmployeeID = salesEmployeeID;
    }

    public float getComissionRate() {
        return comissionRate;
    }

    public void setComissionRate(float comissionRate) {
        this.comissionRate = comissionRate;
    }
}
