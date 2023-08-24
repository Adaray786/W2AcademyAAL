package org.kainos.ea.cli;

public class Employee {
    private int EmployeeID;
    private double salary;
    private String BankAccountNumber;
    private String NINumber;

    public Employee(int employeeID, double salary, String bankAccountNumber, String NINumber) {
        setEmployeeID(employeeID);
        setSalary(salary);
        setNINumber(NINumber);
        setBankAccountNumber(bankAccountNumber);
    }

    public int getEmployeeID() {return EmployeeID;}

    public void setEmployeeID(int employeeID) {EmployeeID = employeeID;}

    public double getSalary() {return salary;}

    public void setSalary(double salary) {this.salary = salary;}

    public String getBankAccountNumber() {return BankAccountNumber;}

    public void setBankAccountNumber(String bankAccountNumber) {BankAccountNumber = bankAccountNumber;}

    public String getNINumber() {return NINumber;}

    public void setNINumber(String NINumber) {this.NINumber = NINumber;}
}
