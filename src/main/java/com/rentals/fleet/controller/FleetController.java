package com.rentals.fleet.controller;

import com.rentals.fleet.beans.BookVehicleRequest;
import com.rentals.fleet.beans.Response;
import com.rentals.fleet.entity.Vehicle;
import com.rentals.fleet.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.format.DecimalStyle;
import java.util.List;

@RestController
public class FleetController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    VehicleService vehicleService;

    /*Add vehicle to the Fleet*/
    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    Response addVehicle(@RequestBody Vehicle vehicle) {
        Response response = new Response();
        try {
            vehicleService.addVehicle(vehicle);
            response.setMessage("VEHICLE_ADDED");
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setMessage("FAILED_TO_ADD_VEHICLE");
        }
        return response;
    }

    /*Book vehicles*/
    @PostMapping(value = "/bookVehicles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response bookVehicles(@RequestBody BookVehicleRequest bookVehicleRequest) {
        Vehicle bookedVehicleDetails = null;
        Response response = new Response();
        try {
            bookedVehicleDetails = vehicleService.allocateVehiclesForCustomers(bookVehicleRequest);
            if(bookedVehicleDetails!=null){
                response.setVehicle(bookedVehicleDetails);
            }else{
                response.setMessage("Requested vehicle is not available");
            }
            response.setVehicle(bookedVehicleDetails);
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
    public Response getRequestedVehicleDetails(String name) {
        Response response = new Response();
        Vehicle requestedVehicleDetails = null;
        try {
            requestedVehicleDetails = vehicleService.getRequestedVehicleDetails(name);
            if(requestedVehicleDetails!=null){
                response.setVehicle(requestedVehicleDetails);
            }else{
                response.setMessage("Requested vehicle details are not available");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response;
    }


    /*Get all booked vehicle details */
    @RequestMapping(value = "/getBookedVehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> getAllocatedVehicles() {
        List<Vehicle> allocatedVehicles = null;
        try {
            allocatedVehicles = vehicleService.getAllAllocatedVehicles();
            System.out.println("allocated vehicles" + allocatedVehicles);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return allocatedVehicles;
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
    public Response getAllocatedVehiclesByCustomerId(String emailId) {
        Response response = new Response();
        List<Vehicle> bookedVehiclesBycustomer = null;
        try {
            bookedVehiclesBycustomer = vehicleService.getAllocatedVehiclesByCustomerId(emailId);
            if(bookedVehiclesBycustomer!=null){
                response.setVehicleList(bookedVehiclesBycustomer);
            }else{
                response.setMessage("No booked vehicles for requested customer");
            }
        } catch (Exception e) {
            response.setError(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }


}
