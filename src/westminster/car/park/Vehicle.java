/*
 * Name   : Benuri Alwis
 * IIT ID : 2015137
 * UoW ID : w1582944
 */
package westminster.car.park;

public abstract class Vehicle {

    /*define attributes*/
    String idPlateNo;
    String brand;
    String type;
    long entryMillis;
    String entryDate;
    String entryTime;

    DateTime date = new DateTime(); //create DateTime object

    public String getIdPlateNo() {
        return idPlateNo;
    }

    public void setIdPlateNo(String idPlateNo) {
        this.idPlateNo = idPlateNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate() {
        String[] time = date.displayTime();
        this.entryDate = time[0];
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime() {
        String[] time = date.displayTime();
        this.entryTime = time[1];
    }

    public long getEntryMillis() {
        return entryMillis;
    }

    public void setEntryMillis() {
        this.entryMillis = date.getCurrentTime();
    }

    /*Constructor*/
    public Vehicle(String idPlateNo, String brand, String type) {
        this.idPlateNo = idPlateNo;
        this.brand = brand;
        this.type = type;
        setEntryTime();
        setEntryDate();
        setEntryMillis();
    }
}
