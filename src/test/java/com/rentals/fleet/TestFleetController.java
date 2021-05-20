package com.rentals.fleet;

import com.rentals.fleet.FleetController;
import com.rentals.fleet.beans.BookVehicleRequest;
import com.rentals.fleet.beans.Response;
import com.rentals.fleet.entity.Vehicle;
import com.rentals.fleet.util.CarRentalsConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFleetController {

    @Autowired
    FleetController fleetController;

    @Test
    public void addVehicle() {
        assertNotNull(fleetController);
        Vehicle vehicle = new Vehicle("Toyota", 15000, 4, 5, "Car", "Comfort", true);
        Response response = fleetController.addVehicle(vehicle);
        assert response.getMessage() == "VEHICLE_ADDED";
    }

    @Test
    public void bookVehicles() {
        BookVehicleRequest bookVehicleRequest = new BookVehicleRequest("abc@gmail.com", "Car", "Comfort");
        Response response = fleetController.bookVehicles(bookVehicleRequest);
        assertNotNull(response.getVehicle().getName());
    }

    @Test
    public void vehiclesNotAvailableForBooking() {
        BookVehicleRequest bookVehicleRequest = new BookVehicleRequest("abc@gmail.com", "Car", "Delux");
        Response response = fleetController.bookVehicles(bookVehicleRequest);
        assert response.getMessage() == "VEHICLES_NOT_AVAILABLE";
    }

    @Test
    public void encounterdExceptionWhileBooking() {
        BookVehicleRequest bookVehicleRequest = null;
        Response response = fleetController.bookVehicles(bookVehicleRequest);
        assertNotNull(response.getError());
    }

    @Test
    public void getAllVehicles() {
        List<Vehicle> allVehiclesList = fleetController.getAllVehicles();
        assert allVehiclesList.size() > 0;
    }

    @Test
    public void getRequestedVehicleDetails() {
        Response response = fleetController.getRequestedVehicleDetails("Toyota");
        assertNotNull(response.getVehicle().getName());
    }

    @Test
    public void getRequestedVehicleDetailsNotAvailable() {
        Response response = fleetController.getRequestedVehicleDetails("Ford");
        assert response.getMessage() == CarRentalsConstants.VEHICLE_DETAILS_NOT_AVAILABLE;
    }

    @Test
    public void getRequestedVehicleDetailsExceptionOccrred() {
        Response response = fleetController.getRequestedVehicleDetails(null);
        assertNotNull(response.getError());
    }

    @Test
    public void getAllocatedVehiclesWhenNotAvailable() {
        Response response = fleetController.getAllocatedVehicles();
        assertNotNull(response.getMessage() == CarRentalsConstants.NO_BOOKINGS_ARE_MADE);
    }

    @Test
    public void getAvailableVehiclesForHire() {
        List<Vehicle> availableVehiclesForHire = fleetController.getAvailableVehiclesForHire();
        assert availableVehiclesForHire.size() > 0;
    }

    @Test
    public void getAllocatedVehiclesByCustomerId() {
        Response response = fleetController.getAllocatedVehiclesByCustomerId("abc@example.com");
        assertNotNull(response.getMessage() == CarRentalsConstants.NO_BOOKINGS_ARE_MADE_FOR_CUSTOMER);
    }


}
