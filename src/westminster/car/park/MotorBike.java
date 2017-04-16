/*
 * Name   : Benuri Alwis
 * IIT ID : 2015137
 * UoW ID : w1582944
 */
package westminster.car.park;

public class MotorBike extends Vehicle {

    /*define attributes*/
    int sizeOfEngine;

    public int getSizeOfEngine() {
        return sizeOfEngine;
    }

    public void setSizeOfEngine(int sizeOfEngine) {
        this.sizeOfEngine = sizeOfEngine;
    }

    /*Constructor*/
    public MotorBike(String idPlateNo, String brand, String type, int sizeOfEngine) {
        super(idPlateNo, brand, type);
        this.sizeOfEngine = sizeOfEngine;

    }

}
