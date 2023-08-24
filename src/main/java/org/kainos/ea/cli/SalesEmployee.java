package org.kainos.ea.cli;

public class SalesEmployee extends Employee{
    public SalesEmployee(int employeeID, double salary, String bankAccountNumber, String NINumber, float comissionRate) {
        super(employeeID, salary, bankAccountNumber, NINumber);
        setComissionRate(comissionRate);
    }
    private float comissionRate;

    public float getComissionRate() {
        return comissionRate;
    }

    public void setComissionRate(float comissionRate) {
        this.comissionRate = comissionRate;
    }
}
