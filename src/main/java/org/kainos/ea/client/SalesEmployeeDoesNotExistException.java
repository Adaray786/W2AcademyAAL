package org.kainos.ea.client;

public class SalesEmployeeDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "Sales employee does not exist";
    }
}
