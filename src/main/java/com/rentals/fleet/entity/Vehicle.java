package com.rentals.fleet.entity;

public class Vehicle {

    private String name;
    private int price;
    private int wheels;
    private int passengersCount;
    private String type;
    private String preference;
    private boolean availability;

    public Vehicle(String name, int price, int wheels, int passengersCount, String type, String preference, boolean availability) {
        this.name = name;
        this.price = price;
        this.wheels = wheels;
        this.passengersCount = passengersCount;
        this.type = type;
        this.preference = preference;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", wheels=" + wheels +
                ", passengersCount=" + passengersCount +
                ", type='" + type + '\'' +
                ", preference='" + preference + '\'' +
                ", availability=" + availability +
                '}';
    }
}
