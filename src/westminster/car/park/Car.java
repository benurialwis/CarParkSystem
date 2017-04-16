/*
 * Name   : Benuri Alwis
 * IIT ID : 2015137
 * UoW ID : w1582944
 */
package westminster.car.park;

public class Car extends Vehicle {

    /*define attrubutes*/
    private int noOfDoors;
    private String color;

    public int getNoOfDoors() {
        return noOfDoors;
    }

    public void setNoOfDoors(int noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /*Constructor*/
    public Car(String idPlateNo, String brand, String type, int noOfDoors, String color) {
        super(idPlateNo, brand, type);
        this.noOfDoors = noOfDoors;
        this.color = color;

    }

}
