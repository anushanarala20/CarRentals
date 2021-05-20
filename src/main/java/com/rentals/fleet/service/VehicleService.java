package com.rentals.fleet.service;

import com.rentals.fleet.beans.BookVehicleRequest;
import com.rentals.fleet.entity.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    private static ArrayList<Vehicle> totalVehicles = new ArrayList();
    private ArrayList<Vehicle> allocatedVehicles = new ArrayList();
    private Map<String, List<Vehicle>> customerBookingMap = new HashMap<>();

   @PostConstruct
    public void prepareVehiclesList() {
        totalVehicles = new ArrayList();
        totalVehicles.add(new Vehicle("Toyota", 15000,  4, 5, "Car", "Comfort", true));
        totalVehicles.add(new Vehicle("Nissan", 12000 , 4, 5, "Car", "Comfort", true));
        totalVehicles.add(new Vehicle("Murano", 20000, 4, 5, "Van", "Comfort", true));
        totalVehicles.add(new Vehicle("Ferrari", 40000, 4, 5, "Convertible Car", "Luxury", true));
        totalVehicles.add(new Vehicle("Audi",  38000, 4, 5, "Car", "Luxury", true));
        totalVehicles.add(new Vehicle("Vauxhall" , 42000, 4, 7, "Van", "ComfortXl", true));
        totalVehicles.add(new Vehicle("Volkswagen", 45000, 4, 7, "Van", "ComfortXl", true));
        totalVehicles.add(new Vehicle("Harley-Davidson", 5000, 2, 2, "MotorBike", "Sportsbike", true));
        totalVehicles.add(new Vehicle("Yamaha", 3000, 2, 2, "MotorBike", "Cruiser", true));
    }

    public List<Vehicle> getAllVehiclesList() {
        return totalVehicles;
    }

    /**
     * Add new Vehicles to the fleet
     *
     * @param vehicle
     */
    public void addVehicle(Vehicle vehicle) {
        if (totalVehicles != null) {
            totalVehicles.add(vehicle);
            logger.info("Added vehicle successfully");
        }
    }

    /**
     * get specific vehicle details
     *
     * @param vehicleName
     * @return
     */
    public Vehicle getRequestedVehicleDetails(String vehicleName) {
        Vehicle requestedVehicle = null;
        if (totalVehicles.size() > 0) {
            for (Vehicle vehicle : totalVehicles) {
                if (vehicleName.equalsIgnoreCase(vehicle.getName())) {
                    requestedVehicle = vehicle;
                    break;
                }
            }
        }
        return requestedVehicle;
    }

    /**
     * check availability for requested vehicle to book
     *
     * @param bookVehicleRequest
     * @return
     */
    public Vehicle checkAvailability(BookVehicleRequest bookVehicleRequest) {
        Vehicle requestedVehicle = null;
       for(Vehicle vehicle: totalVehicles) {
            if(vehicle.isAvailability()
                    && vehicle.getType().equalsIgnoreCase(bookVehicleRequest.getVehicleType())
                    && vehicle.getPreference().equalsIgnoreCase(bookVehicleRequest.getPreference())) {
                requestedVehicle = vehicle;
                break;
            }
        }
        logger.info("Vehicle requested by customer:" + requestedVehicle);
        return requestedVehicle;
    }

    /**
     * Book vehicles
     *
     * @param bookVehicleRequest
     * @return
     */
    public Vehicle allocateVehiclesForCustomers(BookVehicleRequest bookVehicleRequest) {
        Vehicle vehicle = checkAvailability(bookVehicleRequest);
        if (vehicle != null) {
            if (null != customerBookingMap.get(bookVehicleRequest.getEmail())) {
                customerBookingMap.get(bookVehicleRequest.getEmail()).add(vehicle);
            } else {
                ArrayList<Vehicle> vehicles = new ArrayList<>();
                vehicles.add(vehicle);
                customerBookingMap.put(bookVehicleRequest.getEmail(), vehicles);
            }
            vehicle.setAvailability(false);
            logger.info("Allocated requested vehicle for customer:"+vehicle);
        }
        return vehicle;
    }

    /**
     * get All booked vehicles
     *
     * @return
     */
    public List<Vehicle> getAllAllocatedVehicles() {
        return totalVehicles.stream().filter(vehicle -> !vehicle.isAvailability()).collect(Collectors.toList());
    }

    /**
     * get all available vehicles for hire
     *
     * @return
     */
    public List<Vehicle> getAvailableVehiclesToHire() {
        return totalVehicles.stream().filter(vehicle -> vehicle.isAvailability()).collect(Collectors.toList());
    }

    /**
     * get booked vehicles by customer emailId
     * @param emailId
     * @return
     */
    public List<Vehicle> getAllocatedVehiclesByCustomerId(String emailId) {
        List<Vehicle> bookedvehiclesListByCustomer = null;
        if (customerBookingMap.containsKey(emailId)) {
           bookedvehiclesListByCustomer = customerBookingMap.get(emailId);
        }
        return bookedvehiclesListByCustomer;
    }
}
