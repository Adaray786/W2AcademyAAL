package org.kainos.ea.cli;

public class DeliveryEmployee extends Employee{

    public DeliveryEmployee(int employeeID, double salary, String bankAccountNumber, String NINumber, int deliveryEmployeeID) {
        super(employeeID, salary, bankAccountNumber, NINumber);
        setDeliveryEmployeeID(deliveryEmployeeID);
    }
    private int deliveryEmployeeID;

    public int getDeliveryEmployeeID() {
        return deliveryEmployeeID;
    }

    public void setDeliveryEmployeeID(int deliveryEmployeeID) {
        this.deliveryEmployeeID = deliveryEmployeeID;
    }
}
