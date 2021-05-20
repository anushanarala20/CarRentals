package com.rentals.fleet.beans;

public class BookVehicleRequest {

    private String email;
    private String vehicleType;
    private String preference;

    public BookVehicleRequest(String email, String vehicleType, String preference) {
        this.email = email;
        this.vehicleType = vehicleType;
        this.preference = preference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
}
