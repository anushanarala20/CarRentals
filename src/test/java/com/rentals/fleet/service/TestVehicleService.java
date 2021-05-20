package com.rentals.fleet.service;

import com.rentals.fleet.beans.BookVehicleRequest;
import com.rentals.fleet.entity.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVehicleService {

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void getAllVehiclesList() {
        List<Vehicle> vehiclesList = vehicleService.getAllVehiclesList();
        assert vehiclesList.size() > 0;
    }

    @Test
    public void addVehicle() {
        vehicleService = Mockito.spy(vehicleService);
        Vehicle vehicle = new Vehicle("Toyota", 15000, 4, 5, "Car", "Comfort", true);
        vehicleService.addVehicle(vehicle);
        Mockito.verify(vehicleService, Mockito.times(1)).addVehicle(vehicle);
    }

    @Test
    public void getRequestedVehicleDetails() {
        Vehicle vehicle = vehicleService.getRequestedVehicleDetails("Toyota");
        assertNotNull(vehicle.getName());
    }

    @Test
    public void checkAvailability() {
        BookVehicleRequest bookVehicleRequest = new BookVehicleRequest("abc@gmail.com", "Car", "Comfort");
        Vehicle vehicle = vehicleService.checkAvailability(bookVehicleRequest);
        assertNotNull(vehicle.getName());
    }

    @Test
    public void allocateVehiclesForCustomers() {
        BookVehicleRequest bookVehicleRequest = new BookVehicleRequest("example@gmail.com", "Car", "Comfort");
        Vehicle vehicle = vehicleService.allocateVehiclesForCustomers(bookVehicleRequest);
        assertNotNull(vehicle.getName());
    }

    @Test
    public void vehiclesNotVailableToBook() {
        BookVehicleRequest bookVehicleRequest = new BookVehicleRequest("example@gmail.com", "Car", "Delux");
        Vehicle vehicle = vehicleService.allocateVehiclesForCustomers(bookVehicleRequest);
        assertNull(vehicle);
    }

    @Test
    public void getAllAllocatedVehicles() {
        List<Vehicle> availableVehiclesList = vehicleService.getAllAllocatedVehicles();
        assert availableVehiclesList.size() > 0;
    }

    @Test
    public void getAvailableVehiclesToHire() {
        List<Vehicle> availableVehiclesList = vehicleService.getAvailableVehiclesToHire();
        assert availableVehiclesList.size() > 0;
    }

    @Test
    public void getAllocatedVehiclesByCustomerId() {
        List<Vehicle> availableVehiclesList = vehicleService.getAllocatedVehiclesByCustomerId("example@gmail.com");
       assert availableVehiclesList.size() > 0;
    }

}
