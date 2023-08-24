package org.kainos.ea.client;

public class FailedToAssignDeliveryEmployeeException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to assign employee to project in database";
    }
}
