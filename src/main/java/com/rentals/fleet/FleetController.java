package com.rentals.fleet;

import com.rentals.fleet.beans.BookVehicleRequest;
import com.rentals.fleet.beans.Response;
import com.rentals.fleet.entity.Vehicle;
import com.rentals.fleet.service.VehicleService;
import com.rentals.fleet.service.VehicleServiceInterface;
import com.rentals.fleet.util.CarRentalsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FleetController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    VehicleServiceInterface vehicleService;

    /*Add vehicle to the Fleet*/
    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Response addVehicle(@RequestBody Vehicle vehicle) {
        Response response = new Response();
        try {
            vehicleService.addVehicle(vehicle);
            response.setMessage(CarRentalsConstants.VEHICLE_ADDED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setMessage(CarRentalsConstants.FAILED_TO_ADD_VEHICLE);
        }
        return response;
    }

    /*Book vehicles*/
    @PostMapping(value = "/bookVehicles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response bookVehicles(@RequestBody BookVehicleRequest bookVehicleRequest) {
        Vehicle bookedVehicleDetails = null;
        Response response = new Response();
        try {
            bookedVehicleDetails = vehicleService.allocateVehiclesForCustomers(bookVehicleRequest);
            response.setVehicle(bookedVehicleDetails);
            if (null == bookedVehicleDetails) {
                response.setMessage(CarRentalsConstants.VEHICLES_NOT_AVAILABLE);
            }
        } catch (Exception e) {
            response.setError(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }

    /*All vehicles in FleetService*/
    @RequestMapping(value = "/getAllVehicleDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> allVehiclesList = null;
        try {
            allVehiclesList = vehicleService.getAllVehiclesList();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return allVehiclesList;
    }


    /*Get specific vehicle details by providing name*/
    @RequestMapping(value = "/getRequestedVehicleDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getRequestedVehicleDetails(@RequestParam(name="name", required = true) String name) {
        Response response = new Response();
        Vehicle requestedVehicleDetails = null;
        try {
            requestedVehicleDetails = vehicleService.getRequestedVehicleDetails(name);
            response.setVehicle(requestedVehicleDetails);
            if (null == requestedVehicleDetails) {
                response.setMessage(CarRentalsConstants.VEHICLE_DETAILS_NOT_AVAILABLE);
            }
        } catch (Exception e) {
            response.setError(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }


    /*Get all booked vehicle details */
    @RequestMapping(value = "/getBookedVehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllocatedVehicles() {
        Response response = new Response();
        List<Vehicle> allocatedVehicles = null;
        try {
            allocatedVehicles = vehicleService.getAllAllocatedVehicles();
            response.setVehicleList(allocatedVehicles);
            if (allocatedVehicles.size() == 0) {
                response.setVehicleList(null);
                response.setMessage(CarRentalsConstants.NO_BOOKINGS_ARE_MADE);
            }
        } catch (Exception e) {
            response.setError(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }

    /*Get all available vehicles to hire */
    @RequestMapping(value = "/getAvailableVehiclesForHire", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> getAvailableVehiclesForHire() {
        List<Vehicle> availableForHire = null;
        try {
            availableForHire = vehicleService.getAvailableVehiclesToHire();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return availableForHire;
    }

    /*Get booked vehicles by customerId */
    @RequestMapping(value = "/getAllocatedVehiclesByCustomerId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllocatedVehiclesByCustomerId(@RequestParam(name="emailId", required = true) String emailId) {
        Response response = new Response();
        List<Vehicle> bookedVehiclesBycustomer = null;
        try {
            bookedVehiclesBycustomer = vehicleService.getAllocatedVehiclesByCustomerId(emailId);
            response.setVehicleList(bookedVehiclesBycustomer);
            if (null == bookedVehiclesBycustomer) {
                response.setMessage(CarRentalsConstants.NO_BOOKINGS_ARE_MADE_FOR_CUSTOMER);
            }
        } catch (Exception e) {
            response.setError(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }


}
