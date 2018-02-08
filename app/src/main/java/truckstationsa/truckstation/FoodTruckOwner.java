package truckstationsa.truckstation;

import android.app.Fragment;

import java.util.EmptyStackException;

/**
 * Created by manal on 2/8/2018.
 */

public class FoodTruckOwner {

    private  String FUsername  , FPassword , FEmail , Ftype , FPreOrderStatuse , FWorkingHours , Fstatus;
    private  int FPoneNoumber ;
    private double XFLication;
    private double YFLocation;

    public FoodTruckOwner ( String FUsername  ,String FPassword ,String FEmail ,String Ftype ,String FPreOrderStatuse ,String FWorkingHours ,String Fstatus ,  int FPoneNoumber , double XFLication , double YFLocation  ){
   this.setFEmail(FEmail);

   this.setFPassword(FPassword);

   this.setFPoneNoumber(FPoneNoumber);

   this.setFPreOrderStatuse(FPreOrderStatuse);

   this.setFstatus(Fstatus);

   this.setFtype(Ftype);

   this.setFUsername(FUsername);

   this.setFWorkingHours(FWorkingHours);

   this.setXFLication(XFLication);

   this.setYFLocation(YFLocation);


    }

    public void setFEmail(String FEmail) {
        this.FEmail = FEmail;
    }

    public void setFPassword(String FPassword) {
        this.FPassword = FPassword;
    }

    public void setFPoneNoumber(int FPoneNoumber) {
        this.FPoneNoumber = FPoneNoumber;
    }

    public void setFPreOrderStatuse(String FPreOrderStatuse) {
        this.FPreOrderStatuse = FPreOrderStatuse;
    }

    public void setFstatus(String fstatus) {
        Fstatus = fstatus;
    }

    public void setFtype(String ftype) {
        Ftype = ftype;
    }

    public void setFUsername(String FUsername) {
        this.FUsername = FUsername;
    }

    public void setFWorkingHours(String FWorkingHours) {
        this.FWorkingHours = FWorkingHours;
    }

    public void setXFLication(double XFLication) {
        this.XFLication = XFLication;
    }

    public void setYFLocation(double YFLocation) {
        this.YFLocation = YFLocation;
    }

    public double getXFLication() {
        return XFLication;
    }

    public double getYFLocation() {
        return YFLocation;
    }

    public int getFPoneNoumber() {
        return FPoneNoumber;
    }

    public String getFEmail() {
        return FEmail;
    }

    public String getFPassword() {
        return FPassword;
    }

    public String getFPreOrderStatuse() {
        return FPreOrderStatuse;
    }

    public String getFstatus() {
        return Fstatus;
    }

    public String getFtype() {
        return Ftype;
    }

    public String getFUsername() {
        return FUsername;
    }

    public String getFWorkingHours() {
        return FWorkingHours;
    }


}
