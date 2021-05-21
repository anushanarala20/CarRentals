package com.rentals.fleet.service;

import com.rentals.fleet.beans.BookVehicleRequest;
import com.rentals.fleet.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleServiceInterface {

    public List<Vehicle> getAllVehiclesList();

    public void addVehicle(Vehicle vehicle);

    public Vehicle getRequestedVehicleDetails(String vehicleName);

    public Vehicle checkAvailability(BookVehicleRequest bookVehicleRequest);

    public Vehicle allocateVehiclesForCustomers(BookVehicleRequest bookVehicleRequest);

    public List<Vehicle> getAllAllocatedVehicles();

    public List<Vehicle> getAvailableVehiclesToHire();

    public List<Vehicle> getAllocatedVehiclesByCustomerId(String emailId);
}
