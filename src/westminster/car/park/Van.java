/*
 * Name   : Benuri Alwis
 * IIT ID : 2015137
 * UoW ID : w1582944
 */
package westminster.car.park;

public class Van extends Vehicle {

    /*define attributes*/
    int cargoVolume;

    public int getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    /*Constructor*/
    public Van(String idPlateNo, String brand, String type, int cargoVolume) {
        super(idPlateNo, brand, type);
        this.cargoVolume = cargoVolume;

    }
}
