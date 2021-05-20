# CarRentals

> Car Rentals will provide Fleet services for various vehicle types like Cars, Vans and Motor Bikes

## How to get started:

* Please ensure that JAVA 8 installed in your system
* Please fallow below steps to clone the repository
    ```
  $ git clone https://github.com/anushanarala20/CarRentals.git
  $ cd CarRentals
* Import the project into intelliJ or eclipse or any java based IDE as a gradle project.

## How to start application

1. We can run the below command by opening a terminal and selecting the CarRentals project
   ```gradle war```
   OR
2. Right click on the ```Application.java``` file in the project and select  ```Run Application.main()```

## How it works

* Start the application by using any approach which mentioned above and access the below url from browser once
  application started.
* `http://localhost:8080/swagger-ui.html` which provides all the required vehicle services.

## How controller perform different services

1. `addVehicle` - Add a new vehicle to the Fleet 
   
   **Request**
   ```{
   "availability": true,
   "name": "Ford",
   "passengersCount": 5,
   "preference": "Comfort",
   "price": 12000,
   "type": "car",
   "wheels": 4
   }```
   
  **Response:** 
   Return `VEHICLE_ADDED` if vehicle successfully added to the fleet or return 'FAILED_TO_ADD_VEHICLE' if vehicle unable to add to the fleet

2. `bookVehicles` - Allocate a vehicle to the customer based on availability.
    
   **Request**
    1. For Requesting Car
   ```{
        "email": "anushanarala20@gmail.com", // customer's email Id
        "preference": "Comfort", // Customers are required to provide their preference like Comfort, Luxury
        "vehicleType": "Car" // Customers are required to provide type of vehcile like Car, Convertible Car
       }```
   
   2. For Requesting Van
   ```{
        "email": "anushanarala20@gmail.com", // customer's email Id
        "preference": "ComfortXl", // Customers are required to provide their preference like ComfortXl
        "vehicleType": "Van" // Customers are required to provide type of vehcile like Van
       }```
   
   2. For Requesting MotorBike
   ```{
        "email": "anushanarala20@gmail.com", // customer's email Id
        "preference": "ComfortXl", // Customers are required to provide their preference like Sportsbike, Cruiser, Electric
        "vehicleType": "MotorBike" // Customers are required to provide type of vehcile like MotorBike
       }```
   
  **Response:** 

    ```{
            "vehicle": {
            "name": "Nissan",
            "price": 12000,
            "wheels": 4,
            "passengersCount": 5,
            "type": "Car",
            "preference": "Comfort",
            "availability": false
            }
       }```

**Note**: Return `VEHICLES_NOT_AVAILABLE` as a response if requested vehicle is not available for booking
    
3. `getAllVehicles` - Returns all available vehicles from the fleet.
   **Request:**

   ```
   {
        method:GET
   }
   ```

   **Response:**
   
        ```[
                {
                "name": "Toyota",
                "price": 15000,
                "wheels": 4,
                "passengersCount": 5,
                "type": "Car",
                "preference": "Comfort",
                "availability": false
                },
                {
                "name": "Nissan",
                "price": 12000,
                "wheels": 4,
                "passengersCount": 5,
                "type": "Car",
                "preference": "Comfort",
                "availability": false
                }```

4. `getRequestedVehicleDetails` - Provide specific vehicle details by accepting Vehicle name as a request
  
   **Request:**   
   name: Toyota(Ex: Car)
   name: Volkswagen (Ex:Van)
   name: Yamaha (Ex: MotorBike)

   **Response:**
   
           ```
               {
               "vehicle": {
               "name": "Yamaha",
               "price": 3000,
               "wheels": 2,
               "passengersCount": 2,
               "type": "MotorBike",
               "preference": "Cruiser",
               "availability": true
               }
               }
   ```
**Note**: Return `VEHICLE_DETAILS_NOT_AVAILABLE` as a response if requested vehicle is not available

5. `getAllocatedVehicles` - Provides the vehicles details which are already booked by customers
   
   **Request:**   
   ```
   {
    method:GET
   }
   ```
    
   **Response:**
   
       ```[
       {
       "name": "Toyota",
       "price": 15000,
       "wheels": 4,
       "passengersCount": 5,
       "type": "Car",
       "preference": "Comfort",
       "availability": false
       },
       {
       "name": "Nissan",
       "price": 12000,
       "wheels": 4,
       "passengersCount": 5,
       "type": "Car",
       "preference": "Comfort",
       "availability": false
       }```

 **Note:** : If no bookings are made, will return `NO_BOOKINGS_ARE_MADE` as a response.

5. `getAvailableVehiclesForHire` - Provides the vehicles details which are available to hire.
   
   **Request:**     
   emailId: anushanarala20@gmail.com
   
   **Response:**
   ```[
   {
   "name": "Toyota",
   "price": 15000,
   "wheels": 4,
   "passengersCount": 5,
   "type": "Car",
   "preference": "Comfort",
   "availability": true }, {
   "name": "Nissan",
   "price": 12000,
   "wheels": 4,
   "passengersCount": 5,
   "type": "Car",
   "preference": "Comfort",
   "availability": true }```
   
**Note**: If no vehicles are available under requested customer, it returns `NO_BOOKINGS_ARE_MADE_FOR_CUSTOMER` as response   

6. getAllocatedVehiclesByCustomerId - Provides the vehicles details which are booked by the customer
   **Request:**   
   NA

               **Response:**
               ```[
               {
               "name": "Toyota",
               "price": 15000,
               "wheels": 4,
               "passengersCount": 5,
               "type": "Car",
               "preference": "Comfort",
               "availability": true
               },
               {
               "name": "Nissan",
               "price": 12000,
               "wheels": 4,
               "passengersCount": 5,
               "type": "Car",
               "preference": "Comfort",
               "availability": true
               }```

### Build Details
Gradle is used as a build tool in this application.

###Note 
DB is not used in the application and when application started a static content will be loaded.


