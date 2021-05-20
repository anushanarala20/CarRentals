package com.rentals.fleet.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rentals.fleet.entity.Vehicle;

import java.util.List;

public class Response {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Vehicle vehicle;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private List<Vehicle> vehicleList;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
