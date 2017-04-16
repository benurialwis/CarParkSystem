/*
 * Name   : Benuri Alwis
 * IIT ID : 2015137
 * UoW ID : w1582944
 */
package westminster.car.park;

import java.util.ArrayList;

public interface CarParkManager {

    static final int CAPACITY = 20; //Car Park Capacity 
    static ArrayList<Vehicle> vehicleArray = new ArrayList<>(20); //create an ArrayList to store vehicle details

    void addNewVehicle(Vehicle v); //add vehicle to the park

    void deleteVehicle(String id); //remove vehicle from th epark

    void printList(); //print details of the parked vehicles

    void printStatistics(); //print some statistics

    void displayDayList(); //display parked vehicle details of a specified day

    void displayCharge(Vehicle v, long exitTime); //display the fee for parked vehicles
}
