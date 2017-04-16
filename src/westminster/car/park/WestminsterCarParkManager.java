/*
 * Name   : Benuri Alwis
 * IIT ID : 2015137
 * UoW ID : w1582944
 */
package westminster.car.park;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import static westminster.car.park.CarParkManager.vehicleArray;

public class WestminsterCarParkManager implements CarParkManager {

    static int freeLotCount = 20;
    DateTime date = new DateTime();
    static Scanner sc = new Scanner(System.in);

    @Override
    /*Add vehicle to the park*/
    public void addNewVehicle(Vehicle v) {

        if (v.type.equalsIgnoreCase("Van")) { //validate vehicle type 
            if (freeLotCount >= 2) {
                vehicleArray.add(v); //add vehicle to the array
                freeLotCount -= 2; //reduce 2 lots if its a van
                printVehicleDetails(v);
                System.out.println("Vehicle Added !!");
                writeData();
            } else {
                System.out.println("Sorry. No Space Available!");
            }

        } else if (freeLotCount > 0) {
            vehicleArray.add(v);
            freeLotCount -= 1;
            printVehicleDetails(v);
            System.out.println("Vehicle Added.");
            writeData();
        } else {
            System.out.println("Sorry. No Space Available!");
        }
        System.out.println("Available Number of Parking Spaces: " + freeLotCount);
        System.out.println("_______________________________________________________");
    }

    @Override
    /*remove vehicle from park*/
    public void deleteVehicle(String id) {
        Iterator<Vehicle> itr = vehicleArray.iterator(); //create iterator
        boolean var = false;
        while (itr.hasNext()) {
            Vehicle v = itr.next();
            if (v.idPlateNo.equals(id)) { //validate id plate no
                itr.remove();
                var = true;
                long exitMillis = date.getCurrentTime(); //get exit time
                printVehicleDetails(v);
                System.out.println("Vehicle Removed!!");

                String time[] = date.displayTime();
                System.out.println("\nExit Time: " + time[0] + " " + time[1]); //display exit time

                if (v.type.equalsIgnoreCase("Van")) {
                    freeLotCount += 2;
                } else {
                    freeLotCount += 1;
                }

                displayCharge(v, exitMillis); //display charge

            }

        }
        if (var == false) {
            System.out.println("No Vehicle under that ID!");
        }

        System.out.println("Available Parking Spaces: " + freeLotCount); //display number of free slots
        System.out.println("____________________________________________");
    }

    @Override
    /*Print parked vehicle details*/
    public void printList() {
        System.out.println("\n---Vehicle Details---");

        if (vehicleArray.isEmpty()) {
            System.out.println("No Vehicles!");
        } else {
            for (Vehicle v : vehicleArray) {
                printVehicleDetails(v);
                System.out.println("");
            }
        }
        System.out.println("\n__________________________________");
        System.out.println("");
    }

    @Override
    /*print some statistics*/
    public void printStatistics() {
        System.out.println("\n---Statistics---");
        /*display parked vehicle percentages*/
        int carCount = 0;
        int bikeCount = 0;
        int vanCount = 0;
        int totCount = 0;
        double carPercentage, bikePercentage, vanPercentage;

        for (Vehicle v : vehicleArray) { //get vehicle count
            if (v.type.equalsIgnoreCase("car")) {
                carCount++;
                totCount++;
            } else if (v.type.equalsIgnoreCase("van")) {
                vanCount++;
                totCount++;
            } else if (v.type.equalsIgnoreCase("motorbike")) {
                bikeCount++;
                totCount++;
            }
        }

        /*calculate percentages*/
        if (totCount > 0) {
            carPercentage = ((double) carCount / (double) totCount) * 100.0;
            vanPercentage = ((double) vanCount / (double) totCount) * 100.0;
            bikePercentage = ((double) bikeCount / (double) totCount) * 100.0;
            DecimalFormat pFormat = new DecimalFormat("#.00"); //define percentage format 

            /*display percentages*/
            System.out.println("\nPercentage of Cars: " + pFormat.format(carPercentage) + "%");
            System.out.println("Percentage of Vans: " + pFormat.format(vanPercentage) + "%");
            System.out.println("Percentage of MotorBikes: " + pFormat.format(bikePercentage) + "%");

            /*last Vehicle added to the park*/
            System.out.print("\nLast Vehicle: ");
            printVehicleDetails(vehicleArray.get(vehicleArray.size() - 1));
            System.out.println("");

            /*longest parked vehicle*/
            System.out.print("\nLongest Parked: ");
            printVehicleDetails(vehicleArray.get(0));
            System.out.println("\n__________________________________");
            System.out.println("");
        } else {
            System.out.println("No Vehicles!!");
        }

    }

    @Override
    /*display charge*/
    public void displayCharge(Vehicle v, long exitTime) {
        int fHrCharge = 9; //charge for first 3 hours
        int time[] = date.calculateTime(v.entryMillis, exitTime); //calculate duration

        double hrCharge, minCharge, chargePerMin;

        /*calculate charge for less than 3 hours*/
        if (time[1] < 3) {
            chargePerMin = 3 / 60d;
            hrCharge = (int) (time[1] * 3);
            minCharge = time[0] * chargePerMin;

            /*calculate charge for more than 3 hours*/
        } else {
            chargePerMin = 1 / 60d;
            int extraHrs = time[1] - 3;
            hrCharge = fHrCharge + extraHrs * 1;
            minCharge = time[0] * chargePerMin;
        }

        double totCharge = hrCharge + minCharge; //calculate total charge
        DecimalFormat timeFormat = new DecimalFormat("#.00"); //define charge display format
        System.out.println("Total Charge: " + timeFormat.format(totCharge) + "$");
    }

    /*write data into text file*/
    public void writeData() {
        try {
            File f = new File(System.getProperty("user.home") + "/Desktop");
            File file = new File(f, "WCarParkDetails.txt");//create a text file
            file.createNewFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {//write data line by line

                for (Vehicle v : vehicleArray) {
                    bw.write(v.entryDate + "-");
                    bw.write(v.idPlateNo + "-");
                    bw.write(v.entryTime + "-");
                    bw.write(v.type + " ");

                    bw.newLine();
                }
                bw.flush();
                bw.close();

            }

        } catch (Exception e) {
            System.err.println("Error !! ");
        }

    }

    @Override
    /*display parked vehicle details for a specified day*/
    public void displayDayList() {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.home") + "/Desktop/WCarParkDetails.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {//read until the last line
                fileData.add(line);
            }
            br.close();

        } catch (IOException e) {
            System.out.println(" File not Found ");
        }

        System.out.println("---Vehicle Details---");

        System.out.print("Enter Day: ");
        String day = sc.nextLine();

        System.out.print("Enter Month (Ex: Jan, Feb..): ");
        String month = sc.nextLine();

        System.out.print("Enter Year: ");
        String year = sc.nextLine();

        String format = month + " " + day + "," + year;
        boolean var = false;
        System.out.println("");
        for (String check : fileData) {
            String dArr[] = check.split("-");

            if (dArr[0].equals(format)) {
                System.out.print(dArr[1] + " ");
                System.out.print(dArr[2] + " ");
                System.out.print(dArr[3] + " ");
                System.out.println();
                var = true;

            }

        }
        if (var == false) {
            System.out.println("No Data!!");
        }
        System.out.println("__________________________");

    }

    

    /*Dislay Main Menu*/
    public void promptMainMenu() {

        System.out.println("\n1. Add New Vehicle");
        System.out.println("2. Remove Vehicle");
        System.out.println("3. Parked Vehicle Details");
        System.out.println("4. Statistics ");
        System.out.println("5. Vehicle Details of a Specified Day");
        System.out.println("6. Exit");
        System.out.print("\n>>Enter a Number to Proceed: ");

        String userInput = sc.nextLine();

        switch (userInput) {
            case "1":
                promptAddVehicle();
                break;
            case "2":
                promptRemoveVehicle();
                promptMainMenu();
                break;
            case "3":
                printList();
                promptMainMenu();
                break;
            case "4":
                printStatistics();
                promptMainMenu();
                break;
            case "5":
                displayDayList();
                promptMainMenu();
                break;
            case "6":
                System.exit(0);
                break;
            default:
                System.err.println("Invalid Input!");
                promptMainMenu();
                break;
        }
    }

    /*prompt to add vehicle*/
    public void promptAddVehicle() {
        System.out.println("\n---Add Vehicle---");
        System.out.print("Vehicle No: ");
        String idPlateNo = sc.nextLine();

        System.out.print("Brand: ");
        String brand = sc.nextLine();
        System.out.print("Type: ");
        String type = sc.nextLine().toLowerCase();

        int noOfDoors = 0;
        int cargoVolume = 0;
        int sizeOfEngine = 0;
        /*prompt for specified details for relavant type of vehicle*/
        switch (type) {
            case "car":
                try {
                    System.out.print("No of Doors: ");
                    String nd = sc.nextLine();
                    noOfDoors = Integer.parseInt(nd);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Input");
                    promptAddVehicle();
                }
                System.out.print("Color: ");
                String color = sc.nextLine();
                System.out.print("\n");

                Car objC = new Car(idPlateNo, brand, type, noOfDoors, color); //creat car object
                addNewVehicle(objC); //add car object to the array

                promptMainMenu();
                break;
            case "van":
                try {
                    System.out.print("Cargo Volume: ");
                    String cv = sc.nextLine();
                    cargoVolume = Integer.parseInt(cv);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Input");
                    promptAddVehicle();
                }
                System.out.print("\n");
                Van objV = new Van(idPlateNo, brand, type, cargoVolume); //creat van object
                addNewVehicle(objV); //add van object to the array

                promptMainMenu();
                break;
            case "motorbike":
                try {
                    System.out.print("Size of Engine: ");
                    String se = sc.nextLine();
                    sizeOfEngine = Integer.parseInt(se);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Input");
                    promptAddVehicle();
                }

                System.out.print("\n");
                MotorBike objB = new MotorBike(idPlateNo, brand, type, sizeOfEngine); //creat motorbike object
                addNewVehicle(objB); //add motorbike object to the array

                promptMainMenu();
                break;
            default:
                System.err.println("Invalid Input!");
                promptAddVehicle();
                break;

        }

    }

    /*prompt to remove vehicle*/
    private void promptRemoveVehicle() {
        System.out.println("\n---Remove Vehicle---");
        System.out.print("Vehicle No: ");
        String id = sc.nextLine();
        deleteVehicle(id);

    }
    
    /*print vehicle details*/
    public void printVehicleDetails(Vehicle v) {
        System.out.print(v.idPlateNo + "    ");
        System.out.print(v.type + "       ");
        System.out.print(v.entryTime + "    ");
        System.out.print(v.entryDate + "    ");

    }

    public static void main(String[] args) {
        WestminsterCarParkManager park = new WestminsterCarParkManager();
        System.out.println("---------- Welcome to Westminster Car Park Manager ----------\n");
        park.promptMainMenu();
    }
}
